package r2b.apps.lib.taskmanager;

import java.util.Observable;
import java.util.Observer;

/**
 * Wraps an Observer to have a more suitable update interface.
 * 
 * WARNING: The notify is made in the same thread as the task is running, 
 * you can not make direct gui calls on the "completed" method.
 *
 * @param <V> Void or any other object.
 */
public abstract class BaseSyncTaskObserver<V> implements Observer {

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public final void update(Observable observable, Object data) {
		completed(((BaseSyncTaskObservable<V>) observable).getTask(), (V) data);
	}

	/**
	 * Notify when a task has finished.
	 * @param task The completed task.
	 * @param data The task retrieved data. 
	 */
	public abstract void completed(BaseSyncTask<V> task, V data);
	
}

