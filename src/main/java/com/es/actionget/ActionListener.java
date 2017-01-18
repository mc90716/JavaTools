package com.es.actionget;

public interface ActionListener<Response> {
	
    void onResponse(Response response);

    void onFailure(Throwable e);
}
