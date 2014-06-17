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
