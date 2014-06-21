package r2b.apps.test.taskmanager;

import r2b.apps.lib.taskmanager.BaseSyncTask;
import r2b.apps.lib.taskmanager.BaseSyncTaskObserver;
import r2b.apps.lib.taskmanager.TaskManager;
import r2b.apps.test.taskmanager.custom.CustomVoidSyncTask;
import android.test.AndroidTestCase;
import android.util.Log;

public class VoidSyncTaskTest extends AndroidTestCase {
	
	private TaskManager taskManager;	
	private int runningTasks;
	
	private CustomVoidSyncTask task0;
	private CustomVoidSyncTask task1;
	private CustomVoidSyncTask task2;
	private CustomVoidSyncTask task3;

	private long mainThread = Thread.currentThread().getId();
	
	class CustomObserver extends BaseSyncTaskObserver<Void> {

		@Override
		public void completed(BaseSyncTask<Void> task, Void data) {
			assertNotNull("SyncTask must be not null", task);
			assertNull("Data must be null", data);
			assertTrue("Running tasks must be more than zero", runningTasks > 0);
			
			runningTasks--;
			
			Log.d(VoidSyncTaskTest.class.getSimpleName(), 
					"Observer running tasks: " + String.valueOf(runningTasks));
			
			assertEquals("Thread ids must be the same", 
					((CustomVoidSyncTask)task).getThreadId(), 
					Thread.currentThread().getId());
			
			assertFalse("Thread ids can not be the same. Observer must run on NOT main thread", 
					mainThread == Thread.currentThread().getId());
			
		}
		
	};
	
	protected void setUp() throws Exception {
		
		taskManager = new TaskManager();
		
		runningTasks = 0;
		
		task0 = new CustomVoidSyncTask(0, "Task 0");
		task0.addObserver(new CustomObserver());
		task1 = new CustomVoidSyncTask(5*1000, "Task 1");
		task1.addObserver(new CustomObserver());
		task2 = new CustomVoidSyncTask(5*1000, "Task 2");
		task2.addObserver(new CustomObserver());
		task3 = new CustomVoidSyncTask(5*1000, "Task 3");
		task3.addObserver(new CustomObserver());
		
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
				Log.e(VoidSyncTaskTest.class.getSimpleName(), e.toString());
				fail(e.toString());
			}
		}
		
		assertTrue("Thread ids must be diferent", task0.getThreadId() != Thread.currentThread().getId());
					
		// IMPORTANT NOTE: Check all running tasks must be finished before end test method
		assertTrue("All running tasks must be finished before end test method", checkFinishAllTasks());
	}
	
	/**
	 * Execution cancelled with interrupt if running.
	 * Test running and finished tasks.
	 */
	public void testExecuteCancelledInterruptIfRunning() {
		
		// Test running task
		runningTasks++;
		taskManager.execute(task1);
		
		// This task must be not finished, this means not execution of 'runningTask--'
		assertTrue("Task should be cancelled", task1.cancel(true)); // The task will be interrupted
		
		
		// Test finished task
		runningTasks++;
		taskManager.execute(task0);
		while(runningTasks > 1) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Log.e(VoidSyncTaskTest.class.getSimpleName(), e.toString());
				fail(e.toString());
			}
		}
		
		assertTrue("Task can not be cancell because it has been finished", !task0.cancel(true));
			
		
		// IMPORTANT NOTE: Check all running tasks must be finished before end test method
		assertEquals("One running tasks must have the finished flag to false", Integer.valueOf(1), Integer.valueOf(runningTasks));
	}
	
	/**
	 * Execution cancelled.
	 * Test running and finished tasks.
	 */
	public void testExecuteCancelled() {
		
		// Test running task
		runningTasks++;
		taskManager.execute(task1);
		
		assertTrue("Task should be cancelled", task1.cancel(false));
		
		
		// Test finished task
		runningTasks++;
		taskManager.execute(task0);
		while(runningTasks > 1) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Log.e(VoidSyncTaskTest.class.getSimpleName(), e.toString());
				fail(e.toString());
			}
		}
		
		assertTrue("Task can not be cancell because it has been finished", !task0.cancel(false));
	
		
		// IMPORTANT NOTE: Check all running tasks must be finished before end test method
		assertEquals("One running tasks must have the finished flag to false", 
				Integer.valueOf(1), Integer.valueOf(runningTasks));
	}
	
}
