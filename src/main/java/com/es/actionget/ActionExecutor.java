package com.es.actionget;

/**
 * 行为执行器，用于执行一个请求，并且返回一个可等待的结果，子类直接继承该类即可
 *
 */
public abstract class ActionExecutor<T> {
	
	public T get(long timeout) {
		return execute().actionGet(timeout);
	}
	
	public PlainListenableActionFuture<T> execute() {
        PlainListenableActionFuture<T> future = new PlainListenableActionFuture<>();
        execute(future);
        return future;
    }
	
	public void execute(ActionListener<T> listener) {
        doExecute(listener);
    }
	
	protected abstract void doExecute(ActionListener<T> listener);
}
