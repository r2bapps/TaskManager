package r2b.apps.test.taskmanager.custom;

import r2b.apps.lib.taskmanager.BaseAsyncTask;
import r2b.apps.lib.taskmanager.PRIORITY;
import android.util.Log;

public class CustomAsyncTask extends BaseAsyncTask {

	private static long DEFAULT_ID = 0;
	private static long DEFAULT_THREAD_ID = 0;
	
	private long sleep;
	private String name;
	private long threadId;
	
	public CustomAsyncTask(long sleep, String name) {
		super(DEFAULT_ID, PRIORITY.DEFAULT);
		
		DEFAULT_ID++;
		
		this.sleep = sleep;
		this.name = name;
		DEFAULT_THREAD_ID--;
		this.threadId = DEFAULT_THREAD_ID;
		
	}

	public final long getThreadId() {
		return threadId;
	}

	@Override
	protected Void doInBackground() throws Exception {
		threadId = Thread.currentThread().getId();
		Thread.currentThread().setName(name);
		Log.d("execute thread (id, name) ", String.valueOf(Thread.currentThread().getId()) + ", " + name);
		Thread.sleep(sleep);
		return null;
	}

	public String getName() {
		return name;
	}

}
