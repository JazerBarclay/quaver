package tech.tora.quaver.log;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Logging {

	private static void log(boolean popup, LogLevel level, JFrame parent, int status, String title, String text, Exception e) {
		String message;
		if (e == null) message = text;
		else message = text + "\n" + e;
		
		switch (level) {
			case INFO:
				if (popup) JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
				System.out.println(status + ": " + level + " - " + title + "\n" + message);
				break;
			case WARN:
				if (popup) JOptionPane.showMessageDialog(parent, message, title, JOptionPane.WARNING_MESSAGE);
				System.out.println(status + ": " + level + " - " + title + "\n" + message);
				break;
			case ERROR:
				if (popup) JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
				System.err.println(status + ": " + level + " - " + title + "\n" + message);
				break;
			default:
				break;

		}

	}
	
	// INFO
	public static void info(String title, String text) {
		log(false, LogLevel.INFO, null, 0, title, text, null);
	}
	
	public static void info(String title, String text, Exception e) {
		log(false, LogLevel.INFO, null, 0, title, text, e);
	}
	
	public static void infoMessage(JFrame parent, String title, String text) {
		log(true, LogLevel.INFO, parent, 0, title, text, null);
	}
	
	public static void infoMessage(JFrame parent, String title, String text, Exception e) {
		log(true, LogLevel.INFO, parent, 0, title, text, e);
	}
	
	
	// WARNING
	public static void warn(String title, String text) {
		log(false, LogLevel.WARN, null, 0, title, text, null);
	}
	
	public static void warn(String title, String text, Exception e) {
		log(false, LogLevel.WARN, null, 0, title, text, e);
	}
	
	public static void warnMessage(JFrame parent, String title, String text) {
		log(true, LogLevel.WARN, parent, 0, title, text, null);
	}
	
	public static void warnMessage(JFrame parent, String title, String text, Exception e) {
		log(true, LogLevel.WARN, parent, 0, title, text, e);
	}


	// ERROR
	public static void error(int status, String title, String text) {
		log(false, LogLevel.ERROR, null, status, title, text, null);
	}
	
	public static void error(int status, String title, String text, Exception e) {
		log(false, LogLevel.ERROR, null, status, title, text, e);
	}
	
	public static void errorMessage(int status, JFrame parent, String title, String text) {
		log(true, LogLevel.ERROR, parent, status, title, text, null);
	}
	
	public static void errorMessage(int status, JFrame parent, String title, String text, Exception e) {
		log(true, LogLevel.ERROR, parent, status, title, text, e);
	}
	

}
