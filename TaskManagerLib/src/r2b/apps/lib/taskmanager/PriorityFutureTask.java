package r2b.apps.lib.taskmanager;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Wraps a priority attribute on a FutureTask<V>.
 * @param <V> Void or any other object.
 */
class PriorityFutureTask<V> extends FutureTask<V> {

	/**
	 * The future task priority.
	 */
	private int priority;
	
	/**
	 * Builder.
	 * @param runnable The runnable to call.
	 * @param result The retrieved result.
	 */
	public PriorityFutureTask(Runnable runnable, V result) {
		super(runnable, result);
	}

	/**
	 * Builder.
	 * @param callable The callable to call.
	 */
	public PriorityFutureTask(Callable<V> callable) {
		super(callable);
	}
	
	/**
	 * Gets the future task priority.
	 * @return The task priority.
	 */
	public final int getPriority() {
		return priority;
	}

	/**
	 * Sets the future task priority.
	 * @param priority The task priority, a positive value..
	 */
	public void setPriority(int priority) {
		
		if (priority < 0) {
			throw new IllegalArgumentException("priority argument is negative.");
		}
		
		this.priority = priority;
	}

}

