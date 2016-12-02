package com.loadbalance.imp;


import com.loadbalance.abs.AbstractLoadBalance;

import java.util.Collections;

/**
 * 一致性Hash
 */
public class ConsistentHashLoadBalance<T> extends AbstractLoadBalance<T> {
    @Override
    protected T doSelect(String request) {
        int hash = getHash(request);
        Collections.shuffle(this.referList);
        for(int i = 0; i < this.referList.size(); i++) {
            T ref = this.referList.get((hash + i) % this.referList.size());
            return ref;
        }
        return null;
    }

    private int getHash(String request) {
        return 0x7fffffff & request.hashCode();
    }
}
