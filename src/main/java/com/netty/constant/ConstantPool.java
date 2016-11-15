package com.netty.constant;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.collections.Maps;
import static com.utils.Preconditions.*;

public abstract class ConstantPool<T extends Constant<T>> {
	
	private final Map<String, T> constants = Maps.newHashMap();
	private final AtomicInteger nextId = new AtomicInteger();
	
	/**
	 * Check The Name exists or not in pool
	 * @param name
	 * @return
	 */
	public boolean exists(String name) {
		checkNotNull(name);
		checkArgument(!name.isEmpty(), "empty name");
		synchronized (constants) {
			return constants.containsKey(name);
		}
	}
	
	 /**
     * Returns the {@link Constant} which is assigned to the specified {@code name}.
     * If there's no such {@link Constant}, a new one will be created and returned.
     * Once created, the subsequent calls with the same {@code name} will always return the previously created one
     * (i.e. singleton.)
     *
     * @param name the name of the {@link Constant}
     */
	public T valueOf(String name) {
		T t;
		synchronized (constants) {
			if(exists(name)) {
				t = constants.get(name);
			} else {
				t = newInstance0(name);
			}
		}
		return t;
	}
	
	public T valueOf(Class<?> firstNameComponent, String secondNameComponent) {
        checkNotNull(firstNameComponent, "firstNameComponent");
        checkNotNull(secondNameComponent, "secondNameComponent");

        return valueOf(firstNameComponent.getName() + '#' + secondNameComponent);
    }
	
	/**
     * Creates a new {@link Constant} for the given {@param name} or fail with an
     * {@link IllegalArgumentException} if a {@link Constant} for the given {@param name} exists.
     */
	public T newInstance(String name) {
		if(exists(name)) {
			throw new IllegalArgumentException(String.format("'%s' is already in use", name));
		}
		return newInstance0(name);
	}
	
	private T newInstance0(String name) {
		synchronized (constants) {
			T t = newConstant(nextId.getAndIncrement(), name);
			constants.put(name, t);
			return t;
		}
	}
	
	protected abstract T newConstant(int id, String name);
}
