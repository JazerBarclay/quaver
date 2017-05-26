package tech.tora.quaver;

import javax.swing.JFrame;

public class Logging {

	public static void info(JFrame parent, int status, String title, String text) {
		
	}

	public static void info(int status, String title, String text) {
		info(null, status, title, text);
	}
	
	public static void warn(JFrame parent, int status, String title, String text) {
		
	}
	
	public static void warn(int status, String title, String text) {
		warn(null, status, title, text);
	}
	
	public static void error(JFrame parent, int status, String title, String text) {
		
	}
	
	public static void error(int status, String title, String text) {
		error(null, status, title, text);
	}
	
}
