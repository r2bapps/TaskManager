/*
 * PriorityFutureTaskComparator
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

import java.util.Comparator;

/**
 * Compares the priority of PriorityFutureTask<V> classes.
 * It is useful for priority collections.
 */
class PriorityFutureTaskComparator implements Comparator<Runnable> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Runnable lhs, Runnable rhs)  {
		
		if (lhs == null) {
			throw new IllegalArgumentException("lhs argument is null.");
		}
		
		if (rhs == null) {
			throw new IllegalArgumentException("rhs argument is null.");
		}
		
		if ( !(lhs instanceof PriorityFutureTask<?>) ) {
			throw new ClassCastException("lhs argument must be PriorityFutureTask<?> class.");
		}
		
		if ( !(rhs instanceof PriorityFutureTask<?>) ) {
			throw new ClassCastException("rhs argument must be PriorityFutureTask<?> class.");
		}
		
		return Integer.valueOf(((PriorityFutureTask<?>)lhs).getPriority()).
				compareTo(Integer.valueOf(((PriorityFutureTask<?>)rhs).getPriority()));
	}

}
