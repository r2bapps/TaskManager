/*
 * PriorityFutureTask
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

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Wraps a priority attribute on a FutureTask<V>.
 * @param <V> Void or any other object.
 */
class PriorityFutureTask<V> extends FutureTask<V> {

	/**
	 * The future task priority.
	 */
	private int priority;
	
	/**
	 * Builder.
	 * @param runnable The runnable to call.
	 * @param result The retrieved result.
	 */
	public PriorityFutureTask(Runnable runnable, V result) {
		super(runnable, result);
	}

	/**
	 * Builder.
	 * @param callable The callable to call.
	 */
	public PriorityFutureTask(Callable<V> callable) {
		super(callable);
	}
	
	/**
	 * Gets the future task priority.
	 * @return The task priority.
	 */
	public final int getPriority() {
		return priority;
	}

	/**
	 * Sets the future task priority.
	 * @param priority The task priority, a positive value..
	 */
	public void setPriority(int priority) {
		
		if (priority < 0) {
			throw new IllegalArgumentException("priority argument is negative.");
		}
		
		this.priority = priority;
	}

}

