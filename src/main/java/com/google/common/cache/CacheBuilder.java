/*
 * Copyright (C) 2009 The Guava Authors Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */

package com.google.common.cache;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.Objects;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache.SimpleStatsCounter;
import com.google.common.cache.AbstractCache.StatsCounter;
import com.google.common.cache.LocalCache.Strength;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ConcurrentModificationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.CheckReturnValue;

/**
 *LoadingCache 和Cache实例的构造器具备以下特性的组合：
 * 1. 自动把entry加载进cache中；
 * 2. 当超过最大大小时，会使用LRU来移除一些数据；
 * 3. entry基于从上次访问或者写入之后计算的时间的过期机制；
 * 4. 缓存key自动封装在WeakReference引用内
 * 5. 缓存value自动封装在WeakReference引用内或者SoftReference引用内
 * 6. 移除entry的通知
 * 7. 缓存访问统计累计计算
 *
 * 以上这些特征是可选择的；在创建的时候可以使用所有或者一个都不要。默认使用CacheBuilder创建cache将不会执行任务类型的驱逐移除entry
 * 
 * <p>
 * Usage example:
 * 
 * <pre>
 * {
 *     &#064;code
 *     LoadingCache&lt;Key, Graph&gt; graphs = CacheBuilder.newBuilder().maximumSize(10000)
 *             .expireAfterWrite(10, TimeUnit.MINUTES).removalListener(MY_LISTENER).build(new CacheLoader&lt;Key, Graph&gt;() {
 *                 public Graph load(Key key) throws AnyException {
 *                     return createExpensiveGraph(key);
 *                 }
 *             });
 * }
 * </pre>
 * 
 * <p>
 * Or equivalently,
 * 下面这个通过一个以，分隔的字符串来创建cache的相关配置
 *
 * <pre>
 * {
 *     &#064;code
 *     // In real life this would come from a command-line flag or config file
 *     String spec = &quot;maximumSize=10000,expireAfterWrite=10m&quot;;
 * 
 *     LoadingCache&lt;Key, Graph&gt; graphs = CacheBuilder.from(spec).removalListener(MY_LISTENER)
 *             .build(new CacheLoader&lt;Key, Graph&gt;() {
 *                 public Graph load(Key key) throws AnyException {
 *                     return createExpensiveGraph(key);
 *                 }
 *             });
 * }
 * </pre>
 *
 * 实现之后返回的cache和ConcurrentHashMap的性能特性是相近的。它实现了所有Cache和LoadingCache接口的可选择操作。
 * asMap视图和他的集合试图有weakly连续的iterator。这意外着并发使用是安全的，但是如果其他线程在创建iterator之后修改了cache，
 * 这些修改将是未定义的，并且这些修改不会抛出ConcurrentModificationException异常。
 *
 * 默认，返回的cache使用Object 的相等比较器来比较keys或者values。同样的，如果指定weakvalues或者softvalues，则cache使用==比较器给这些值。
 *
 * 当 maxinumSize，maxinumWeight，expireAfterWrite，expireAfterAccess，weakKeys，weakValues，或者 softValues 被访问的时候，
 * entry会自动被移除缓存。
 *
 * 如果expireAfterWrite 和expireAfterAccess被请求，则在每次cache修改，临时的cache访问或者调用cleanuo时，entry可能会被移除。
 * 过期的entry可能会被计算在size里面，但是一定不会被读或者写操作看见。
 *
 * 某些cache配置将会导致
 * 
 * <p>
 * Certain cache configurations will result in the accrual of periodic maintenance tasks which will be performed during
 * write operations, or during occasional read operations in the absence of writes. The {@link Cache#cleanUp} method of
 * the returned cache will also perform maintenance, but calling it should not be necessary with a high throughput
 * cache. Only caches built with {@linkplain #removalListener removalListener}, {@linkplain #expireAfterWrite
 * expireAfterWrite}, {@linkplain #expireAfterAccess expireAfterAccess}, {@linkplain #weakKeys weakKeys},
 * {@linkplain #weakValues weakValues}, or {@linkplain #softValues softValues} perform periodic maintenance.
 * 
 * <p>
 * The caches produced by {@code CacheBuilder} are serializable, and the deserialized caches retain all the
 * configuration properties of the original cache. Note that the serialized form does <i>not</i> include cache contents,
 * but only configuration.
 * 
 * <p>
 * See the Guava User Guide article on <a href=
 * "http://code.google.com/p/guava-libraries/wiki/CachesExplained">caching</a> for a higher-level explanation.
 * 
 * @param <K> the base key type for all caches created by this builder
 * @param <V> the base value type for all caches created by this builder
 * @author Charles Fry
 * @author Kevin Bourrillion
 * @since 10.0
 */
@GwtCompatible(emulated = true)
public final class CacheBuilder<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16; // 默认的初始化Map大小
    private static final int DEFAULT_CONCURRENCY_LEVEL = 4; // 默认并发水平
    private static final int DEFAULT_EXPIRATION_NANOS = 0; // 默认超时
    private static final int DEFAULT_REFRESH_NANOS = 0; // 默认刷新时间

    /**
     * 默认空的缓存命中统计类
     */
    static final Supplier<? extends StatsCounter> NULL_STATS_COUNTER = Suppliers.ofInstance(new StatsCounter() {
        @Override
        public void recordHits(int count) {
        }

        @Override
        public void recordMisses(int count) {
        }

        @Override
        public void recordLoadSuccess(long loadTime) {
        }

        @Override
        public void recordLoadException(long loadTime) {
        }

        @Override
        public void recordEviction() {
        }

        @Override
        public CacheStats snapshot() {
            return EMPTY_STATS;
        }
    });
    static final CacheStats EMPTY_STATS = new CacheStats(0, 0, 0, 0, 0, 0);// 初始状态的统计对象

    /**
     * 系统实现的简单的缓存状态统计类
     */
    static final Supplier<StatsCounter> CACHE_STATS_COUNTER = new Supplier<StatsCounter>() {
        @Override
        public StatsCounter get() {
            return new SimpleStatsCounter();
        }
    };

    /**
     * 自定义的空RemovalListener，监听到移除通知后，空处理。
     */
    enum NullListener implements RemovalListener<Object, Object> {
        INSTANCE;

        @Override
        public void onRemoval(RemovalNotification<Object, Object> notification) {
        }
    }

    /**
     * 默认权重类，任何对象的权重均为1
     */
    enum OneWeigher implements Weigher<Object, Object> {
        INSTANCE;

        @Override
        public int weigh(Object key, Object value) {
            return 1;
        }
    }

    static final Ticker NULL_TICKER = new Ticker() {
        @Override
        public long read() {
            return 0;
        }
    };

    private static final Logger logger = Logger.getLogger(CacheBuilder.class.getName());

    static final int UNSET_INT = -1;

    boolean strictParsing = true;

    int initialCapacity = UNSET_INT;
    int concurrencyLevel = UNSET_INT;
    long maximumSize = UNSET_INT;
    long maximumWeight = UNSET_INT;
    Weigher<? super K, ? super V> weigher;

    Strength keyStrength;
    Strength valueStrength;

    long expireAfterWriteNanos = UNSET_INT;
    long expireAfterAccessNanos = UNSET_INT;
    long refreshNanos = UNSET_INT;

    /**
     * 判断相等的类，里面定义对象的相等，hash等方法的实现
     */
    Equivalence<Object> keyEquivalence;
    Equivalence<Object> valueEquivalence;

    RemovalListener<? super K, ? super V> removalListener;
    Ticker ticker;

    Supplier<? extends StatsCounter> statsCounterSupplier = NULL_STATS_COUNTER;

    // TODO(fry): make constructor private and update tests to use newBuilder
    CacheBuilder() {
    }

    /**
     * 使用默认的设置，包括strong 的键，strong的值，以及非自动的对象移除。 Constructs a new {@code CacheBuilder} instance with default settings,
     * including strong keys, strong values, and no automatic eviction of any kind.
     */
    public static CacheBuilder<Object, Object> newBuilder() {
        return new CacheBuilder<Object, Object>();
    }

    /**
     * Constructs a new {@code CacheBuilder} instance with the settings specified in {@code spec}.
     * 
     * @since 12.0
     */
    @Beta
    @GwtIncompatible("To be supported")
    public static CacheBuilder<Object, Object> from(CacheBuilderSpec spec) {
        return spec.toCacheBuilder().lenientParsing();
    }

    /**
     * 使用配置文件的方式来构建CacheBuilder对象
     * 
     * Constructs a new {@code CacheBuilder} instance with the settings specified in {@code spec}. This is especially
     * useful for command-line configuration of a {@code CacheBuilder}.
     * 
     * @param spec a String in the format specified by {@link CacheBuilderSpec}
     * @since 12.0
     */
    @Beta
    @GwtIncompatible("To be supported")
    public static CacheBuilder<Object, Object> from(String spec) {
        return from(CacheBuilderSpec.parse(spec));
    }

    /**
     * Enables lenient parsing. Useful for tests and spec parsing.
     */
    @GwtIncompatible("To be supported")
    CacheBuilder<K, V> lenientParsing() {
        strictParsing = false;
        return this;
    }

    /**
     * 为比较的 `key` 设置有个自定义的 `等价`策略。主要是当使用weakKey时候，需要指定相等方法来判断key是否equal
     * 
     * Sets a custom {@code Equivalence} strategy for comparing keys.
     * 
     * <p>
     * By default, the cache uses {@link Equivalence#identity} to determine key equality when {@link #weakKeys} is
     * specified, and {@link Equivalence#equals()} otherwise.
     */
    @GwtIncompatible("To be supported")
    CacheBuilder<K, V> keyEquivalence(Equivalence<Object> equivalence) {
        checkState(keyEquivalence == null, "key equivalence was already set to %s", keyEquivalence);
        keyEquivalence = checkNotNull(equivalence);
        return this;
    }

    /**
     * 默认的key等同判断
     * @return
     */
    Equivalence<Object> getKeyEquivalence() {
        return firstNonNull(keyEquivalence, getKeyStrength().defaultEquivalence());
    }

    /**
     * Sets a custom {@code Equivalence} strategy for comparing values.
     * 
     * <p>
     * By default, the cache uses {@link Equivalence#identity} to determine value equality when {@link #weakValues} or
     * {@link #softValues} is specified, and {@link Equivalence#equals()} otherwise.
     */
    @GwtIncompatible("To be supported")
    CacheBuilder<K, V> valueEquivalence(Equivalence<Object> equivalence) {
        checkState(valueEquivalence == null, "value equivalence was already set to %s", valueEquivalence);
        this.valueEquivalence = checkNotNull(equivalence);
        return this;
    }

    /**
     * 默认value的等同判断
     * @return
     */
    Equivalence<Object> getValueEquivalence() {
        return firstNonNull(valueEquivalence, getValueStrength().defaultEquivalence());
    }

    /**
     * 设置初始化hash表的最小表大小。concurrency level的值为8,表示其内部使用8个segment，也就是段。在初始化估算的时候，提供足够的大的值可以避免 后期的重量级的resize操作带来的效率损失。
     * 
     * Sets the minimum total size for the internal hash tables. For example, if the initial capacity is {@code 60}, and
     * the concurrency level is {@code 8}, then eight segments are created, each having a hash table of size eight.
     * Providing a large enough estimate at construction time avoids the need for expensive resizing operations later,
     * but setting this value unnecessarily high wastes memory.
     * 
     * @throws IllegalArgumentException if {@code initialCapacity} is negative
     * @throws IllegalStateException if an initial capacity was already set
     */
    public CacheBuilder<K, V> initialCapacity(int initialCapacity) {
        checkState(this.initialCapacity == UNSET_INT, "initial capacity was already set to %s", this.initialCapacity);
        checkArgument(initialCapacity >= 0);
        this.initialCapacity = initialCapacity;
        return this;
    }

    /**
     * 默认为8.当没有设置初始大小的时候。其实，我觉得在声明变量的时候，可以直接赋值DEFAULT_INITIAL_CAPACITY， 这里直接返回initialCapacity就可以了。
     * 
     * @return
     */
    int getInitialCapacity() {
        return (initialCapacity == UNSET_INT) ? DEFAULT_INITIAL_CAPACITY : initialCapacity;
    }

    /**
     * concurrencyLevel名为并发水平，之所以这么称呼，是因为其值牵涉到多线程环境下并发线程的数量。因为，该值是指定segment值，
     * concurrentHashMap的实现可以看到，其分段加锁机制就是对每个段单独维护一个锁，也就是段的个数，会直接影响到理想情况下最后多少个
     * 线程同时操作hash表。因此，为了避免线程冲突，尽量把该值设置大一点，当然还需要考虑太大的值会直接占用更多的空间。
     * 
     * Guides the allowed concurrency among update operations. Used as a hint for internal sizing. The table is
     * internally partitioned to try to permit the indicated number of concurrent updates without contention. Because
     * assignment of entries to these partitions is not necessarily uniform, the actual concurrency observed may vary.
     * Ideally, you should choose a value to accommodate as many threads as will ever concurrently modify the table.
     * Using a significantly higher value than you need can waste space and time, and a significantly lower value can
     * lead to thread contention. But overestimates and underestimates within an order of magnitude do not usually have
     * much noticeable impact. A value of one permits only one thread to modify the cache at a time, but since read
     * operations and cache loading computations can proceed concurrently, this still yields higher concurrency than
     * full synchronization.
     * 
     * <p>
     * Defaults to 4. <b>Note:</b>The default may change in the future. If you care about this value, you should always
     * choose it explicitly.
     * 
     * <p>
     * The current implementation uses the concurrency level to create a fixed number of hashtable segments, each
     * governed by its own write lock. The segment lock is taken once for each explicit write, and twice for each cache
     * loading computation (once prior to loading the new value, and once after loading completes). Much internal cache
     * management is performed at the segment granularity. For example, access queues and write queues are kept per
     * segment when they are required by the selected eviction algorithm. As such, when writing unit tests it is not
     * uncommon to specify {@code concurrencyLevel(1)} in order to achieve more deterministic eviction behavior.
     * 
     * <p>
     * Note that future implementations may abandon segment locking in favor of more advanced concurrency controls.
     * 
     * @throws IllegalArgumentException if {@code concurrencyLevel} is nonpositive
     * @throws IllegalStateException if a concurrency level was already set
     */
    public CacheBuilder<K, V> concurrencyLevel(int concurrencyLevel) {
        checkState(this.concurrencyLevel == UNSET_INT, "concurrency level was already set to %s", this.concurrencyLevel);
        checkArgument(concurrencyLevel > 0);
        this.concurrencyLevel = concurrencyLevel;
        return this;
    }

    int getConcurrencyLevel() {
        return (concurrencyLevel == UNSET_INT) ? DEFAULT_CONCURRENCY_LEVEL : concurrencyLevel;
    }

    /**
     * 最大的缓存大小。需要注意的是，在这个缓存到达限制之前，就会有元素移除在cache之外。至于什么时候移除元素，则依赖与weight值的设置。
     * 
     * Specifies the maximum number of entries the cache may contain. Note that the cache <b>may evict an entry before
     * this limit is exceeded</b>. As the cache size grows close to the maximum, the cache evicts entries that are less
     * likely to be used again. For example, the cache may evict an entry because it hasn't been used recently or very
     * often.
     * 
     * <p>
     * When {@code size} is zero, elements will be evicted immediately after being loaded into the cache. This can be
     * useful in testing, or to disable caching temporarily without a code change.
     * 
     * <p>
     * This feature cannot be used in conjunction with {@link #maximumWeight}.
     * 
     * @param size the maximum size of the cache
     * @throws IllegalArgumentException if {@code size} is negative
     * @throws IllegalStateException if a maximum size or weight was already set
     */
    public CacheBuilder<K, V> maximumSize(long size) {
        checkState(this.maximumSize == UNSET_INT, "maximum size was already set to %s", this.maximumSize);
        checkState(this.maximumWeight == UNSET_INT, "maximum weight was already set to %s", this.maximumWeight);
        checkState(this.weigher == null, "maximum size can not be combined with weigher");
        checkArgument(size >= 0, "maximum size must not be negative");
        this.maximumSize = size;
        return this;
    }

    /**
     * 当cache的大小慢慢接近初始化设置的max大小时，一些元素就会移除到缓存之外。当weight为0 的时候，元素在加载到缓存之后将会马上被赶出缓存。
     * 因此，weight值直接决定了当当前缓存的大小占用整个初始化最大值的比例后，元素被移除cache。至于移除什么样的元素，就是其他策略决定的。
     * 
     * Specifies the maximum weight of entries the cache may contain. Weight is determined using the {@link Weigher}
     * specified with {@link #weigher}, and use of this method requires a corresponding call to {@link #weigher} prior
     * to calling {@link #build}.
     * 
     * <p>
     * Note that the cache <b>may evict an entry before this limit is exceeded</b>. As the cache size grows close to the
     * maximum, the cache evicts entries that are less likely to be used again. For example, the cache may evict an
     * entry because it hasn't been used recently or very often.
     * 
     * <p>
     * When {@code weight} is zero, elements will be evicted immediately after being loaded into cache. This can be
     * useful in testing, or to disable caching temporarily without a code change.
     * 
     * <p>
     * Note that weight is only used to determine whether the cache is over capacity; it has no effect on selecting
     * which entry should be evicted next.
     * 
     * <p>
     * This feature cannot be used in conjunction with {@link #maximumSize}.
     * 
     * @param weight the maximum total weight of entries the cache may contain
     * @throws IllegalArgumentException if {@code weight} is negative
     * @throws IllegalStateException if a maximum weight or size was already set
     * @since 11.0
     */
    @GwtIncompatible("To be supported")
    public CacheBuilder<K, V> maximumWeight(long weight) {
        checkState(this.maximumWeight == UNSET_INT, "maximum weight was already set to %s", this.maximumWeight);
        checkState(this.maximumSize == UNSET_INT, "maximum size was already set to %s", this.maximumSize);
        this.maximumWeight = weight;
        checkArgument(weight >= 0, "maximum weight must not be negative");
        return this;
    }

    /**
     * 
     * 
     * Specifies the weigher to use in determining the weight of entries. Entry weight is taken into consideration by
     * {@link #maximumWeight(long)} when determining which entries to evict, and use of this method requires a
     * corresponding call to {@link #maximumWeight(long)} prior to calling {@link #build}. Weights are measured and
     * recorded when entries are inserted into the cache, and are thus effectively static during the lifetime of a cache
     * entry.
     * 
     * <p>
     * When the weight of an entry is zero it will not be considered for size-based eviction (though it still may be
     * evicted by other means).
     * 
     * <p>
     * <b>Important note:</b> Instead of returning <em>this</em> as a {@code CacheBuilder} instance, this method returns
     * {@code CacheBuilder<K1, V1>}. From this point on, either the original reference or the returned reference may be
     * used to complete configuration and build the cache, but only the "generic" one is type-safe. That is, it will
     * properly prevent you from building caches whose key or value types are incompatible with the types accepted by
     * the weigher already provided; the {@code CacheBuilder} type cannot do this. For best results, simply use the
     * standard method-chaining idiom, as illustrated in the documentation at top, configuring a {@code CacheBuilder}
     * and building your {@link Cache} all in a single statement.
     * 
     * <p>
     * <b>Warning:</b> if you ignore the above advice, and use this {@code CacheBuilder} to build a cache whose key or
     * value type is incompatible with the weigher, you will likely experience a {@link ClassCastException} at some
     * <i>undefined</i> point in the future.
     * 
     * @param weigher the weigher to use in calculating the weight of cache entries
     * @throws IllegalArgumentException if {@code size} is negative
     * @throws IllegalStateException if a maximum size was already set
     * @since 11.0
     */
    @GwtIncompatible("To be supported")
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> weigher(Weigher<? super K1, ? super V1> weigher) {
        checkState(this.weigher == null);
        if (strictParsing) {
            checkState(this.maximumSize == UNSET_INT, "weigher can not be combined with maximum size", this.maximumSize);
        }

        // safely limiting the kinds of caches this can produce
        @SuppressWarnings("unchecked")
        CacheBuilder<K1, V1> me = (CacheBuilder<K1, V1>) this;
        me.weigher = checkNotNull(weigher);
        return me;
    }

    long getMaximumWeight() {
        if (expireAfterWriteNanos == 0 || expireAfterAccessNanos == 0) {
            return 0;
        }
        return (weigher == null) ? maximumSize : maximumWeight;
    }

    // Make a safe contravariant cast now so we don't have to do it over and over.
    @SuppressWarnings("unchecked")
    <K1 extends K, V1 extends V> Weigher<K1, V1> getWeigher() {
        return (Weigher<K1, V1>) Objects.firstNonNull(weigher, OneWeigher.INSTANCE);
    }

    /**
     * Specifies that each key (not value) stored in the cache should be wrapped in a {@link WeakReference} (by default,
     * strong references are used).
     * 
     * <p>
     * <b>Warning:</b> when this method is used, the resulting cache will use identity ({@code ==}) comparison to
     * determine equality of keys.
     * 
     * <p>
     * Entries with keys that have been garbage collected may be counted in {@link Cache#size}, but will never be
     * visible to read or write operations; such entries are cleaned up as part of the routine maintenance described in
     * the class javadoc.
     * 
     * @throws IllegalStateException if the key strength was already set
     */
    @GwtIncompatible("java.lang.ref.WeakReference")
    public CacheBuilder<K, V> weakKeys() {
        return setKeyStrength(Strength.WEAK);
    }

    CacheBuilder<K, V> setKeyStrength(Strength strength) {
        checkState(keyStrength == null, "Key strength was already set to %s", keyStrength);
        keyStrength = checkNotNull(strength);
        return this;
    }

    /**
     * 默认的key引用
     * @return
     */
    Strength getKeyStrength() {
        return firstNonNull(keyStrength, Strength.STRONG);
    }

    /**
     * Specifies that each value (not key) stored in the cache should be wrapped in a {@link WeakReference} (by default,
     * strong references are used).
     * 
     * <p>
     * Weak values will be garbage collected once they are weakly reachable. This makes them a poor candidate for
     * caching; consider {@link #softValues} instead.
     * 
     * <p>
     * <b>Note:</b> when this method is used, the resulting cache will use identity ({@code ==}) comparison to determine
     * equality of values.
     * 
     * <p>
     * Entries with values that have been garbage collected may be counted in {@link Cache#size}, but will never be
     * visible to read or write operations; such entries are cleaned up as part of the routine maintenance described in
     * the class javadoc.
     * 
     * @throws IllegalStateException if the value strength was already set
     */
    @GwtIncompatible("java.lang.ref.WeakReference")
    public CacheBuilder<K, V> weakValues() {
        return setValueStrength(Strength.WEAK);
    }

    /**
     * Specifies that each value (not key) stored in the cache should be wrapped in a {@link SoftReference} (by default,
     * strong references are used). Softly-referenced objects will be garbage-collected in a <i>globally</i>
     * least-recently-used manner, in response to memory demand.
     * 
     * <p>
     * <b>Warning:</b> in most circumstances it is better to set a per-cache {@linkplain #maximumSize(long) maximum
     * size} instead of using soft references. You should only use this method if you are well familiar with the
     * practical consequences of soft references.
     * 
     * <p>
     * <b>Note:</b> when this method is used, the resulting cache will use identity ({@code ==}) comparison to determine
     * equality of values.
     * 
     * <p>
     * Entries with values that have been garbage collected may be counted in {@link Cache#size}, but will never be
     * visible to read or write operations; such entries are cleaned up as part of the routine maintenance described in
     * the class javadoc.
     * 
     * @throws IllegalStateException if the value strength was already set
     */
    @GwtIncompatible("java.lang.ref.SoftReference")
    public CacheBuilder<K, V> softValues() {
        return setValueStrength(Strength.SOFT);
    }

    CacheBuilder<K, V> setValueStrength(Strength strength) {
        checkState(valueStrength == null, "Value strength was already set to %s", valueStrength);
        valueStrength = checkNotNull(strength);
        return this;
    }

    /**
     * 默认为Strong 属性的引用
     * 
     * @return
     */
    Strength getValueStrength() {
        return firstNonNull(valueStrength, Strength.STRONG);
    }

    /**
     * 指定在固定时间过后，会自定移除该元素。
     * 
     * Specifies that each entry should be automatically removed from the cache once a fixed duration has elapsed after
     * the entry's creation, or the most recent replacement of its value.
     * 
     * <p>
     * When {@code duration} is zero, this method hands off to {@link #maximumSize(long) maximumSize}{@code (0)},
     * ignoring any otherwise-specificed maximum size or weight. This can be useful in testing, or to disable caching
     * temporarily without a code change.
     * 
     * <p>
     * Expired entries may be counted in {@link Cache#size}, but will never be visible to read or write operations.
     * Expired entries are cleaned up as part of the routine maintenance described in the class javadoc.
     * 
     * @param duration the length of time after an entry is created that it should be automatically removed
     * @param unit the unit that {@code duration} is expressed in
     * @throws IllegalArgumentException if {@code duration} is negative
     * @throws IllegalStateException if the time to live or time to idle was already set
     */
    public CacheBuilder<K, V> expireAfterWrite(long duration, TimeUnit unit) {
        checkState(expireAfterWriteNanos == UNSET_INT, "expireAfterWrite was already set to %s ns",
                expireAfterWriteNanos);
        checkArgument(duration >= 0, "duration cannot be negative: %s %s", duration, unit);
        this.expireAfterWriteNanos = unit.toNanos(duration);
        return this;
    }

    long getExpireAfterWriteNanos() {
        return (expireAfterWriteNanos == UNSET_INT) ? DEFAULT_EXPIRATION_NANOS : expireAfterWriteNanos;
    }

    /**
     * Specifies that each entry should be automatically removed from the cache once a fixed duration has elapsed after
     * the entry's creation, the most recent replacement of its value, or its last access. Access time is reset by all
     * cache read and write operations (including {@code Cache.asMap().get(Object)} and {@code Cache.asMap().put(K, V)}
     * ), but not by operations on the collection-views of {@link Cache#asMap}.
     * 
     * <p>
     * When {@code duration} is zero, this method hands off to {@link #maximumSize(long) maximumSize}{@code (0)},
     * ignoring any otherwise-specificed maximum size or weight. This can be useful in testing, or to disable caching
     * temporarily without a code change.
     * 
     * <p>
     * Expired entries may be counted in {@link Cache#size}, but will never be visible to read or write operations.
     * Expired entries are cleaned up as part of the routine maintenance described in the class javadoc.
     * 
     * @param duration the length of time after an entry is last accessed that it should be automatically removed
     * @param unit the unit that {@code duration} is expressed in
     * @throws IllegalArgumentException if {@code duration} is negative
     * @throws IllegalStateException if the time to idle or time to live was already set
     */
    public CacheBuilder<K, V> expireAfterAccess(long duration, TimeUnit unit) {
        checkState(expireAfterAccessNanos == UNSET_INT, "expireAfterAccess was already set to %s ns",
                expireAfterAccessNanos);
        checkArgument(duration >= 0, "duration cannot be negative: %s %s", duration, unit);
        this.expireAfterAccessNanos = unit.toNanos(duration);
        return this;
    }

    long getExpireAfterAccessNanos() {
        return (expireAfterAccessNanos == UNSET_INT) ? DEFAULT_EXPIRATION_NANOS : expireAfterAccessNanos;
    }

    /**
     * 在元素新建或者他的值被覆盖的固定时间之后刷新活跃的元素。关于如何refresh，则是refresh定义，在reload调用。
     * 
     * 默认的reload是串行的。推荐使用异步的实现来重写该reload方法。
     * 
     * Specifies that active entries are eligible for automatic refresh once a fixed duration has elapsed after the
     * entry's creation, or the most recent replacement of its value. The semantics of refreshes are specified in
     * {@link LoadingCache#refresh}, and are performed by calling {@link CacheLoader#reload}.
     * 
     * <p>
     * As the default implementation of {@link CacheLoader#reload} is synchronous, it is recommended that users of this
     * method override {@link CacheLoader#reload} with an asynchronous implementation; otherwise refreshes will be
     * performed during unrelated cache read and write operations.
     * 
     * <p>
     * Currently automatic refreshes are performed when the first stale request for an entry occurs. The request
     * triggering refresh will make a blocking call to {@link CacheLoader#reload} and immediately return the new value
     * if the returned future is complete, and the old value otherwise.
     * 
     * <p>
     * <b>Note:</b> <i>all exceptions thrown during refresh will be logged and then swallowed</i>.
     * 
     * @param duration the length of time after an entry is created that it should be considered stale, and thus
     *            eligible for refresh
     * @param unit the unit that {@code duration} is expressed in
     * @throws IllegalArgumentException if {@code duration} is negative
     * @throws IllegalStateException if the refresh interval was already set
     * @since 11.0
     */
    @Beta
    @GwtIncompatible("To be supported (synchronously).")
    public CacheBuilder<K, V> refreshAfterWrite(long duration, TimeUnit unit) {
        checkNotNull(unit);
        checkState(refreshNanos == UNSET_INT, "refresh was already set to %s ns", refreshNanos);
        checkArgument(duration > 0, "duration must be positive: %s %s", duration, unit);
        this.refreshNanos = unit.toNanos(duration);
        return this;
    }

    long getRefreshNanos() {
        return (refreshNanos == UNSET_INT) ? DEFAULT_REFRESH_NANOS : refreshNanos;
    }

    /**
     * 自定义时间源，默认使用System获取时间。 Specifies a nanosecond-precision time source for use in determining when entries should be
     * expired. By default, {@link System#nanoTime} is used.
     * 
     * <p>
     * The primary intent of this method is to facilitate testing of caches which have been configured with
     * {@link #expireAfterWrite} or {@link #expireAfterAccess}.
     * 
     * @throws IllegalStateException if a ticker was already set
     */
    public CacheBuilder<K, V> ticker(Ticker ticker) {
        checkState(this.ticker == null);
        this.ticker = checkNotNull(ticker);
        return this;
    }

    Ticker getTicker(boolean recordsTime) {
        if (ticker != null) {
            return ticker;
        }
        return recordsTime ? Ticker.systemTicker() : NULL_TICKER;
    }

    /**
     * 配置一个监听器，然后根据RemovalCause来通知缓存移除元素。这个builder每创建一个cache时将会调用监听器。但是需要注意的是，当调用这个 方法之后，不能在用该缓存builder引用，而需要使用返回的cache
     * builder。
     * 
     * Specifies a listener instance that caches should notify each time an entry is removed for any
     * {@linkplain RemovalCause reason}. Each cache created by this builder will invoke this listener as part of the
     * routine maintenance described in the class documentation above.
     * 
     * <p>
     * <b>Warning:</b> after invoking this method, do not continue to use <i>this</i> cache builder reference; instead
     * use the reference this method <i>returns</i>. At runtime, these point to the same instance, but only the returned
     * reference has the correct generic type information so as to ensure type safety. For best results, use the
     * standard method-chaining idiom illustrated in the class documentation above, configuring a builder and building
     * your cache in a single statement. Failure to heed this advice can result in a {@link ClassCastException} being
     * thrown by a cache operation at some <i>undefined</i> point in the future.
     * 
     * <p>
     * <b>Warning:</b> any exception thrown by {@code listener} will <i>not</i> be propagated to the {@code Cache} user,
     * only logged via a {@link Logger}.
     * 
     * @return the cache builder reference that should be used instead of {@code this} for any remaining configuration
     *         and cache building
     * @throws IllegalStateException if a removal listener was already set
     */
    @CheckReturnValue
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> removalListener(
            RemovalListener<? super K1, ? super V1> listener) {
        checkState(this.removalListener == null);

        // safely limiting the kinds of caches this can produce
        @SuppressWarnings("unchecked")
        CacheBuilder<K1, V1> me = (CacheBuilder<K1, V1>) this;
        me.removalListener = checkNotNull(listener);
        return me;
    }

    // Make a safe contravariant cast now so we don't have to do it over and over.
    @SuppressWarnings("unchecked")
    <K1 extends K, V1 extends V> RemovalListener<K1, V1> getRemovalListener() {
        return (RemovalListener<K1, V1>) Objects.firstNonNull(removalListener, NullListener.INSTANCE);
    }

    /**
     * 打开cache操作时候的缓存统计功能。
     * 
     * Enable the accumulation of {@link CacheStats} during the operation of the cache. Without this {@link Cache#stats}
     * will return zero for all statistics. Note that recording stats requires bookkeeping to be performed with each
     * operation, and thus imposes a performance penalty on cache operation.
     * 
     * @since 12.0 (previously, stats collection was automatic)
     */
    public CacheBuilder<K, V> recordStats() {
        statsCounterSupplier = CACHE_STATS_COUNTER;
        return this;
    }

    boolean isRecordingStats() {
        return statsCounterSupplier == CACHE_STATS_COUNTER;
    }

    Supplier<? extends StatsCounter> getStatsCounterSupplier() {
        return statsCounterSupplier;
    }

    /**
     * 新建一个cache，返回一个给定key已经加载的值，或者自动计算。如果并发加载load该键的值，则等待线程完成并且返回它加载的值。
     * 
     * Builds a cache, which either returns an already-loaded value for a given key or atomically computes or retrieves
     * it using the supplied {@code CacheLoader}. If another thread is currently loading the value for this key, simply
     * waits for that thread to finish and returns its loaded value. Note that multiple threads can concurrently load
     * values for distinct keys.
     * 
     * <p>
     * This method does not alter the state of this {@code CacheBuilder} instance, so it can be invoked again to create
     * multiple independent caches.
     * 
     * @param loader the cache loader used to obtain new values
     * @return a cache having the requested features
     */
    public <K1 extends K, V1 extends V> LoadingCache<K1, V1> build(CacheLoader<? super K1, V1> loader) {
        checkWeightWithWeigher();
        return new LocalCache.LocalLoadingCache<K1, V1>(this, loader);
    }

    /**
     * 新建一个缓存，不会自动加载值直到key被请求。
     * 
     * Builds a cache which does not automatically load values when keys are requested.
     * 
     * <p>
     * Consider {@link #build(CacheLoader)} instead, if it is feasible to implement a {@code CacheLoader}.
     * 
     * <p>
     * This method does not alter the state of this {@code CacheBuilder} instance, so it can be invoked again to create
     * multiple independent caches.
     * 
     * @return a cache having the requested features
     * @since 11.0
     */
    public <K1 extends K, V1 extends V> Cache<K1, V1> build() {
        checkWeightWithWeigher();
        checkNonLoadingCache();
        return new LocalCache.LocalManualCache<K1, V1>(this);
    }

    /**
     * 非加载cache不能设置自动刷新时间
     */
    private void checkNonLoadingCache() {
        checkState(refreshNanos == UNSET_INT, "refreshAfterWrite requires a LoadingCache");
    }

    /**
     * 检查权重值设置
     */
    private void checkWeightWithWeigher() {
        if (weigher == null) {
            checkState(maximumWeight == UNSET_INT, "maximumWeight requires weigher");
        } else {
            if (strictParsing) {
                checkState(maximumWeight != UNSET_INT, "weigher requires maximumWeight");
            } else {
                if (maximumWeight == UNSET_INT) {
                    logger.log(Level.WARNING, "ignoring weigher specified without maximumWeight");
                }
            }
        }
    }

    /**
     * Returns a string representation for this CacheBuilder instance. The exact form of the returned string is not
     * specified.
     */
    @Override
    public String toString() {
        Objects.ToStringHelper s = Objects.toStringHelper(this);
        if (initialCapacity != UNSET_INT) {
            s.add("initialCapacity", initialCapacity);
        }
        if (concurrencyLevel != UNSET_INT) {
            s.add("concurrencyLevel", concurrencyLevel);
        }
        if (maximumSize != UNSET_INT) {
            s.add("maximumSize", maximumSize);
        }
        if (maximumWeight != UNSET_INT) {
            s.add("maximumWeight", maximumWeight);
        }
        if (expireAfterWriteNanos != UNSET_INT) {
            s.add("expireAfterWrite", expireAfterWriteNanos + "ns");
        }
        if (expireAfterAccessNanos != UNSET_INT) {
            s.add("expireAfterAccess", expireAfterAccessNanos + "ns");
        }
        if (keyStrength != null) {
            s.add("keyStrength", Ascii.toLowerCase(keyStrength.toString()));
        }
        if (valueStrength != null) {
            s.add("valueStrength", Ascii.toLowerCase(valueStrength.toString()));
        }
        if (keyEquivalence != null) {
            s.addValue("keyEquivalence");
        }
        if (valueEquivalence != null) {
            s.addValue("valueEquivalence");
        }
        if (removalListener != null) {
            s.addValue("removalListener");
        }
        return s.toString();
    }
}
