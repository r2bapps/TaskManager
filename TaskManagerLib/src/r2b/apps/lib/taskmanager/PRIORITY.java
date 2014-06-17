package r2b.apps.lib.taskmanager;

public enum PRIORITY {

	CRITICAL(0), VERY_HIGH(20), HIGH(40), DEFAULT(60), LOW(80), VERY_LOW(100);
	
	private int priority;
	
	PRIORITY(int priority) {
		this.priority = priority;
	}
	
	public int getValue() {
		return priority;
	}
	
}
