package r2b.apps.test.taskmanager.custom;

import r2b.apps.lib.taskmanager.BaseSyncTask;
import android.util.Log;

public class CustomStringSyncTask extends BaseSyncTask<String> {
	
	private static long DEFAULT_THREAD_ID = 0;
	
	private long sleep;
	private String name;
	private long threadId;
	private boolean finished;
	
	public CustomStringSyncTask(long sleep, String name) {
		super();
		
		this.sleep = sleep;
		this.name = name;
		DEFAULT_THREAD_ID--;
		this.threadId = DEFAULT_THREAD_ID;
		
	}

	public final long getThreadId() {
		return threadId;
	}

	@Override
	protected String doInBackground() throws Exception {
		threadId = Thread.currentThread().getId();
		Log.d("execute thread (id, name) ", String.valueOf(Thread.currentThread().getId()) + ", " + name);
		Thread.sleep(sleep);
		return name + " " + String.valueOf(threadId);
	}
	
	public boolean getFinished() {
		return finished;
	}
	
	public void setFinished() {
		this.finished = true;
	}
	
	public String getName() {
		return name;
	}

}
