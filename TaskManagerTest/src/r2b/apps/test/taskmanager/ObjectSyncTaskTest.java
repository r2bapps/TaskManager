package r2b.apps.test.taskmanager;

import r2b.apps.lib.taskmanager.BaseSyncTask;
import r2b.apps.lib.taskmanager.BaseSyncTaskObserver;
import r2b.apps.lib.taskmanager.TaskManager;
import r2b.apps.test.taskmanager.custom.CustomStringSyncTask;
import android.test.AndroidTestCase;
import android.util.Log;

public class ObjectSyncTaskTest extends AndroidTestCase {
	
	private TaskManager taskManager;	
	private int runningTasks;
	
	private CustomStringSyncTask task0;

	private long mainThread = Thread.currentThread().getId();
	
	class CustomObserver extends BaseSyncTaskObserver<String> {

		@Override
		public void completed(BaseSyncTask<String> task, String data) {	
			assertNotNull("SyncTask must be not null", task);		
			assertNotNull("Data must be not null", data);
			
			// With this we know if a returned String info is the retrieved from the task.
			// We do not want one task with other task retrieved info.
			assertEquals("Data must be a string and be the same as its task name", 
					((CustomStringSyncTask)task).getName() + " " + String.valueOf(((CustomStringSyncTask)task).getThreadId()), 
					((String)data));
			
			
			assertTrue("Running tasks must be more than zero", runningTasks > 0);

			
			runningTasks--;
			
			Log.d(ObjectSyncTaskTest.class.getSimpleName(), 
					"Observer running tasks: " + String.valueOf(runningTasks));
			
			assertFalse("Thread ids can not be the same. Observer must run on NOT main thread", 
					mainThread == Thread.currentThread().getId());
		}
		
	};
	
	protected void setUp() throws Exception {
		
		taskManager = new TaskManager();
		
		runningTasks = 0;
		
		task0 = new CustomStringSyncTask(0, "Task 0");
		task0.addObserver(new CustomObserver());
		
		super.setUp();
	}

	protected void tearDown() throws Exception {
		
		if(taskManager != null) {
			taskManager.shutdown(false);
		}

		super.tearDown();
	}
	
	private boolean checkFinishAllTasks() {
	
    	if (runningTasks > 0) {
    		return false;
    	}
	    
	    return true;
	}

	/**
	 * Execution on background thread.
	 * IMPORTANT NOTE: Check all running tasks must be finished before end test method
	 */
	public void testExecuteOnBackground() {
		
		runningTasks++;
		taskManager.execute(task0);
		
		// Waiting for end all tasks
		while(!checkFinishAllTasks()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Log.e(ObjectSyncTaskTest.class.getSimpleName(), e.toString());
				fail(e.toString());
			}
		}
		
		assertTrue("Thread ids must be diferent", task0.getThreadId() != Thread.currentThread().getId());
					
		// IMPORTANT NOTE: Check all running tasks must be finished before end test method
		assertTrue("All running tasks must be finished before end test method", checkFinishAllTasks());
	}
	
}

