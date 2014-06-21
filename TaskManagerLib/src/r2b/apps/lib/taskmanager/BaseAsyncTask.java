/*
 * BaseAsyncTask
 * 
 * 0.2
 * 
 * 2014/06/21
 * 
 * (The MIT License)
 * 
 * Copyright (c) R2B Apps <r2b.apps@gmail.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * 'Software'), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */

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

