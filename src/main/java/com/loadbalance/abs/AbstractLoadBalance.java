package com.loadbalance.abs;

import com.loadbalance.itf.LoadBalance;

import java.util.List;

public abstract class AbstractLoadBalance<T> implements LoadBalance<T> {

    protected List<T> referList = null;

    @Override
    public void onRefresh(List<T> list) {
        this.referList = list;
    }

    @Override
    public T select(String request) {
        if(this.referList.size() == 0) {
            return null;
        }  else if(this.referList.size() == 1) {
            return this.referList.get(1);
        } else {
            return doSelect(request);
        }
    }

    protected abstract T doSelect(String request);

    @Override
    public void setWeightString(String weight){
        //set weight string
    }

}
