package r2b.apps.test.taskmanager.custom;

import r2b.apps.lib.taskmanager.BaseAsyncTask;
import r2b.apps.lib.taskmanager.PRIORITY;
import android.util.Log;

public class PriorityAsyncTask extends BaseAsyncTask {
	
	private static long DEFAULT_THREAD_ID = 0;
	
	private long sleep;
	private String name;
	private long threadId;
	private long beginExecution = -1;
	
	public PriorityAsyncTask(long sleep, String name, PRIORITY priority) {
		super(DEFAULT_THREAD_ID, priority);
		
		this.sleep = sleep;
		this.name = name;
		DEFAULT_THREAD_ID--;
		this.threadId = DEFAULT_THREAD_ID;
		
	}

	public final long getThreadId() {
		return threadId;
	}

	public long getBeginExecution() {
		return beginExecution;
	}

	public synchronized void setBeginExecution(long beginExecution) {
		this.beginExecution = beginExecution;
	}

	@Override
	protected Void doInBackground() throws Exception {
		setBeginExecution(System.currentTimeMillis());			
		threadId = Thread.currentThread().getId();
		Log.d("execute thread (id, name) ", String.valueOf(Thread.currentThread().getId()) + ", " + name);
		Thread.sleep(sleep);
		return null;
	}

}
