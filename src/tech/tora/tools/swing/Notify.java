package tech.tora.tools.swing;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Notify {

	public static void info(JFrame parent, String title, String message) {
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void warn(JFrame parent, String title, String message) {
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.WARNING_MESSAGE);
	}

	public static void error(JFrame parent, String title, String message) {
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public static int confirm(JFrame parent, String title, String message) {
		return JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.OK_CANCEL_OPTION);
	}
	
}
