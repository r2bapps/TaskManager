package r2b.apps.lib.taskmanager;

/**
 * This class is useful for packaging any task implementation and 
 * run on a task pool with multithreading and priority support.
 * 
 * Run a task with an asynchronous functionality. 
 */
public abstract class BaseAsyncTask extends Task<Void> {
	
	/**
	 * Builder.
	 * @param id The task identifier.
	 * @param priority The task priority.
	 */
	public BaseAsyncTask(long id, PRIORITY priority) {
		super(id);		
		init(this, priority);
	}
	
	/**
	 * Builder, with the task default priority and identifier value zero.
	 */
	public BaseAsyncTask() {
		super(0);		
		init(this, PRIORITY.DEFAULT);
	}
	
	/* (non-Javadoc)
	 * @see r2b.apps.lib.taskmanager.Task#call()
	 */
	@Override
	public final Void call() throws Exception {
		return super.call();
	}
	
}

