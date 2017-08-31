package tech.tora.tools.system.log;

public class Analytic {

	long start = 0, end = 0;
	
	public Analytic() {
		
	}
	
	public long startTimer() {
		start = System.currentTimeMillis();
		return start;
	}
	
	public long lapTimer() {
		end = System.currentTimeMillis();
		long diff = end - start;
		return diff;
	}
	
}
