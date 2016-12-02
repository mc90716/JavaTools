package com.loadbalance.itf;

import java.util.List;

public interface LoadBalance<T> {

    T select(String request);

    void setWeightString(String weight);

    void onRefresh(List<T> referList);
}
