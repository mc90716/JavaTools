package com.loadbalance.imp;

import com.loadbalance.abs.AbstractLoadBalance;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询
 * @param <T>
 */
public class RoundRobinLoadBalance<T> extends AbstractLoadBalance<T> {

    private static AtomicInteger idx = new AtomicInteger(0);

    @Override
    protected T doSelect(String request) {
        if(this.referList.size() == 0) {
            return null;
        } else if (this.referList.size() == 1) {
            return this.referList.get(1);
        } else {
            int id = getNextPositive();
            for(int i = 0; i < this.referList.size(); i++) {
                T node = this.referList.get((i + id) % this.referList.size());
                if(node == null) {
                    continue;
                }
                return node;
            }
        }
        return null;
    }

    private int getNextPositive() {
        return 0x7fffffff & idx.incrementAndGet();
    }
}
