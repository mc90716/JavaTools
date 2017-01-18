package com.es.actionget;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public abstract class BaseFuture<V> implements Future<V>{
	
    private final Sync<V> sync = new Sync<>();

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException,
            TimeoutException, ExecutionException {
        return sync.get(unit.toNanos(timeout));
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return sync.get();
    }

    @Override
    public boolean isDone() {
        return sync.isDone();
    }

    @Override
    public boolean isCancelled() {
        return sync.isCancelled();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (!sync.cancel()) {
            return false;
        }
        done();
        if (mayInterruptIfRunning) {
            interruptTask();
        }
        return true;
    }

    protected void interruptTask() {
    }

    protected boolean set(@Nullable V value) {
        boolean result = sync.set(value);
        if (result) {
            done();
        }
        return result;
    }

    protected boolean setException(Throwable throwable) {
        boolean result = sync.setException(throwable);
        if (result) {
            done();
        }
        return result;
    }


    protected void done() {
    }
	
	
	static final class Sync<V> extends AbstractQueuedSynchronizer {

        private static final long serialVersionUID = 0L;

        /* Valid states. */
        static final int RUNNING = 0;
        static final int COMPLETING = 1;
        static final int COMPLETED = 2;
        static final int CANCELLED = 4;

        private V value;
        private Throwable exception;

        /*
        * Acquisition succeeds if the future is done, otherwise it fails.
        */
        @Override
        protected int tryAcquireShared(int ignored) {
            if (isDone()) {
                return 1;
            }
            return -1;
        }

        @Override
        protected boolean tryReleaseShared(int finalState) {
            setState(finalState);
            return true;
        }

        V get(long nanos) throws TimeoutException, CancellationException,
                ExecutionException, InterruptedException {

            // Attempt to acquire the shared lock with a timeout.
            if (!tryAcquireSharedNanos(-1, nanos)) {
                throw new TimeoutException("Timeout waiting for task.");
            }

            return getValue();
        }

        V get() throws CancellationException, ExecutionException,
                InterruptedException {

            // Acquire the shared lock allowing interruption.
            acquireSharedInterruptibly(-1);
            return getValue();
        }

        private V getValue() throws CancellationException, ExecutionException {
            int state = getState();
            switch (state) {
                case COMPLETED:
                    if (exception != null) {
                        throw new ExecutionException(exception);
                    } else {
                        return value;
                    }

                case CANCELLED:
                    throw new CancellationException("Task was cancelled.");

                default:
                    throw new IllegalStateException(
                            "Error, synchronizer in invalid state: " + state);
            }
        }

        boolean setException(Throwable t) {
            return complete(null, t, COMPLETED);
        }
        
        boolean isDone() {
            return (getState() & (COMPLETED | CANCELLED)) != 0;
        }

        boolean isCancelled() {
            return getState() == CANCELLED;
        }

        boolean set(@Nullable V v) {
            return complete(v, null, COMPLETED);
        }

        boolean cancel() {
            return complete(null, null, CANCELLED);
        }

        private boolean complete(@Nullable V v, @Nullable Throwable t,
                                 int finalState) {
            boolean doCompletion = compareAndSetState(RUNNING, COMPLETING);
            if (doCompletion) {
                // If this thread successfully transitioned to COMPLETING, set the value
                // and exception and then release to the final state.
                this.value = v;
                this.exception = t;
                releaseShared(finalState);
            } else if (getState() == COMPLETING) {
                // If some other thread is currently completing the future, block until
                // they are done so we can guarantee completion.
                acquireShared(-1);
            }
            return doCompletion;
        }
    }
	
	public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {
		final class A extends BaseFuture<String> {
			
		}
		A a = new A();
		a.set("AA");
		String s = a.get(3, TimeUnit.SECONDS);
		System.out.println("after 3 seconds ===" + s);
		
	}

}
