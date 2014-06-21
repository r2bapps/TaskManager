package r2b.apps.lib.taskmanager;

import java.util.Observable;

/**
 * 
 * Aggregates a BaseSyncTask<V> on an observable to have observable BaseSyncTasks.
 *
 * @param <V> Void or any other object.
 */
class BaseSyncTaskObservable<V> extends Observable {

	/**
	 * The observable task.
	 */
	private BaseSyncTask<V> task;
	
	/**
	 * Builder.
	 * @param task The observable task.
	 */
	public BaseSyncTaskObservable(final BaseSyncTask<V> task) {
		super();
		this.task = task;
	}

	/* (non-Javadoc)
	 * @see java.util.Observable#setChanged()
	 */
	@Override
	protected void setChanged() {
		super.setChanged();
	}

	/**
	 * Get the task.
	 * @return The task.
	 */
	public BaseSyncTask<V> getTask() {
		return task;
	}

}

