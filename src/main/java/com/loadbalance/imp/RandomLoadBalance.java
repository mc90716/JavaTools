package com.loadbalance.imp;

import com.loadbalance.abs.AbstractLoadBalance;

/**
 * 随机
 * @param <T>
 */
public class RandomLoadBalance<T> extends AbstractLoadBalance<T> {

    @Override
    protected T doSelect(String request) {
        int randomIdx = (int) ((Math.random()) * referList.size());
        return this.referList.get(randomIdx);
    }
}
