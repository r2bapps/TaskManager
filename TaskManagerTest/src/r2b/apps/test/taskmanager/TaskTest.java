package r2b.apps.test.taskmanager;

import java.util.Hashtable;

import r2b.apps.lib.taskmanager.PRIORITY;
import r2b.apps.lib.taskmanager.TaskManager;
import r2b.apps.test.taskmanager.custom.CustomAsyncTask;
import r2b.apps.test.taskmanager.custom.PriorityAsyncTask;
import android.test.AndroidTestCase;
import android.util.Log;

public class TaskTest extends AndroidTestCase {
	
	private TaskManager taskManager;
	
	private CustomAsyncTask task0;
	private CustomAsyncTask task1;
	private CustomAsyncTask task2;
	private CustomAsyncTask task3;
	
	protected void setUp() throws Exception {
		
		taskManager = new TaskManager();
		
		task0 = new CustomAsyncTask(0, "Task 0");
		task1 = new CustomAsyncTask(50*1000, "Task 1");
		task2 = new CustomAsyncTask(50*1000, "Task 2");
		task3 = new CustomAsyncTask(50*1000, "Task 3");
		
		super.setUp();
	}

	protected void tearDown() throws Exception {
		
		if(taskManager != null) {
			taskManager.shutdown(false);
		}

		super.tearDown();
	}
	
	private boolean checkStartAllTasks(CustomAsyncTask... tasks) {
		
	    for (CustomAsyncTask task : tasks) {
	    	if (task.getThreadId() < 0) {
	    		return false;
	    	}
	    }
	    
	    return true;
	}
	
	private boolean checkStartAllTasks(PriorityAsyncTask... tasks) {
		
	    for (PriorityAsyncTask task : tasks) {
	    	if (task.getThreadId() < 0) {
	    		return false;
	    	}
	    }

	    return true;
	}

	/**
	 * Execution on background thread.
	 */
	public void testExecuteOnBackground() {

		taskManager = new TaskManager(5);
		
		taskManager.execute(task0);
		
		assertTrue("Thread ids must be diferent", 
				task0.getThreadId() != Thread.currentThread().getId());
		
	}
	
	/**
	 * Multithread execution.
	 * IMPORTANT NOTE: TaskManager setted with 5 threads
	 */
	public void testExecuteMultithread() {
		
		// IMPORTANT NOTE: TaskManager setted with 5 threads
		taskManager = new TaskManager(5);
		
		taskManager.execute(task1);
		taskManager.execute(task2);	
		taskManager.execute(task3);
		
		// Waiting for start all tasks	
		while(!checkStartAllTasks(task1, task2, task3)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Log.e(TaskTest.class.getSimpleName(), e.toString());
				fail(e.toString());
			}
		}
		
		assertTrue("Thread ids must be diferent", 
				task1.getThreadId() != task2.getThreadId() 
				&& task1.getThreadId() != task3.getThreadId() 
				&& task3.getThreadId() != task2.getThreadId());
		
		assertTrue("Thread ids must be diferent", 
				task1.getThreadId() != Thread.currentThread().getId());
		assertTrue("Thread ids must be diferent", 
				task2.getThreadId() != Thread.currentThread().getId());
		assertTrue("Thread ids must be diferent", 
				task3.getThreadId() != Thread.currentThread().getId());
			
	}
	
	/**
	 * Multithread execution, with thread reusing.
	 * IMPORTANT NOTE: TaskManager setted with 1 thread
	 */
	public void testExecuteMultithreadReusing() {	
		
		// IMPORTANT NOTE: TaskManager setted with 1 thread
		taskManager = new TaskManager(1);
		
		CustomAsyncTask taskAux = new CustomAsyncTask(0, "Task Aux");
		
		taskManager.execute(task0);
		taskManager.execute(taskAux);
		
		// Waiting for start all tasks
		while(!checkStartAllTasks(task0, taskAux)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Log.e(TaskTest.class.getSimpleName(), e.toString());
				fail(e.toString());
			}
		}
		
		assertTrue("Thread ids must be equal", 
				taskAux.getThreadId() == task0.getThreadId());
		
		assertTrue("Thread ids must be diferent", 
				task0.getThreadId() != Thread.currentThread().getId());
		assertTrue("Thread ids must be diferent", 
				taskAux.getThreadId() != Thread.currentThread().getId());
			
	}
	
	/**
	 * Multithread execution, with thread limit.
	 * IMPORTANT NOTE: TaskManager setted with 2 thread
	 */
	public void testExecuteMultithreadLimit() {	
		
		// IMPORTANT NOTE: TaskManager setted with 2 thread
		int POOL_SIZE = 2;
		taskManager = new TaskManager(POOL_SIZE);
		
		CustomAsyncTask taskAux1 = new CustomAsyncTask(100, "Task Aux 1");
		CustomAsyncTask taskAux2 = new CustomAsyncTask(100, "Task Aux 2");
		CustomAsyncTask taskAux3 = new CustomAsyncTask(100, "Task Aux 3");
		CustomAsyncTask taskAux4 = new CustomAsyncTask(100, "Task Aux 4");
		CustomAsyncTask taskAux5 = new CustomAsyncTask(100, "Task Aux 5");
		
		taskManager.execute(task0);
		taskManager.execute(taskAux1);
		taskManager.execute(taskAux2);
		taskManager.execute(taskAux3);
		taskManager.execute(taskAux4);
		taskManager.execute(taskAux5);
		
		// Waiting for start all tasks
		while(!checkStartAllTasks(task0, taskAux1, taskAux2, taskAux3, taskAux4, taskAux5)) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Log.e(TaskTest.class.getSimpleName(), e.toString());
				fail(e.toString());
			}
		}
		
		Hashtable<Long, String> hashTest = new Hashtable<Long, String>();
		hashTest.put(Long.valueOf(task0.getThreadId()), "not used");
		hashTest.put(Long.valueOf(taskAux1.getThreadId()), "not used");
		hashTest.put(Long.valueOf(taskAux2.getThreadId()), "not used");
		hashTest.put(Long.valueOf(taskAux3.getThreadId()), "not used");
		hashTest.put(Long.valueOf(taskAux4.getThreadId()), "not used");
		hashTest.put(Long.valueOf(taskAux5.getThreadId()), "not used");
		
		assertEquals("Must be two diferent thread ids only", POOL_SIZE, hashTest.size());		
			
	}
	
	/**
	 * Execution cancelled with interrupt if running.
	 * Test running and finished tasks.
	 */
	public void testExecuteCancelledInterruptIfRunning() {
		
		// Test running task
		taskManager.execute(task1);
		
		assertTrue("Task should be cancelled", task1.cancel(true));
		
		
		// Test finished task
		taskManager.execute(task0);
		while(!checkStartAllTasks(task0)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Log.e(TaskTest.class.getSimpleName(), e.toString());
				fail(e.toString());
			}
		}
		
		assertTrue("Task can not be cancell because it has been finished", 
				!task0.cancel(true));
					
	}
	
	/**
	 * Execution cancelled.
	 * Test running and finished tasks.
	 */
	public void testExecuteCancelled() {
		
		// Test running task
		taskManager.execute(task1);
		
		assertTrue("Task should be cancelled", task1.cancel(false));
		
		
		// Test finished task
		taskManager.execute(task0);
		while(!checkStartAllTasks(task0)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Log.e(TaskTest.class.getSimpleName(), e.toString());
				fail(e.toString());
			}
		}
		
		assertTrue("Task can not be cancell because it has been finished", !task0.cancel(false));
					
	}
	
	/**
	 * Execution with task priority.
	 * IMPORTANT NOTE: TaskManager setted with 1 thread
	 */
	public void testExecuteWithPriority() {	
	
		// IMPORTANT NOTE: TaskManager setted with 1 thread
		taskManager = new TaskManager(1);
		
		PriorityAsyncTask taskCritical = new PriorityAsyncTask(1*1000, "Task Critical", PRIORITY.CRITICAL);
		PriorityAsyncTask taskLow = new PriorityAsyncTask(5*1000, "Task Low", PRIORITY.LOW);
		PriorityAsyncTask taskLow2 = new PriorityAsyncTask(1*1000, "Task Low 2", PRIORITY.LOW);
		
		taskManager.execute(taskLow);
		taskManager.execute(taskLow2);
		taskManager.execute(taskCritical);
		
		// Waiting for start all tasks
		while(!checkStartAllTasks(taskLow, taskLow2, taskCritical)) {
			try {
				Thread.sleep(1*1000);
			} catch (InterruptedException e) {
				Log.e(TaskTest.class.getSimpleName(), e.toString());
				fail(e.toString());
			}
		}
		
		assertTrue("Begin execution time must be less from critical task than low 2 task", 
				taskCritical.getBeginExecution() < taskLow2.getBeginExecution());
			
	}

}
