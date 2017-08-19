package tech.tora.quaver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

import org.json.simple.parser.ParseException;

import tech.tora.quaver.notepad.Notepad;
import tech.tora.quaver.notepad.NotepadOld;
import tech.tora.tools.system.log.Logging;

public class Launcher {

	public static String projectName = "Quaver";

	public static String buildID = "0.0 r0";
	public static int buildRelease = 0;
	public static int buildMajor = 0;
	public static int buildMinor = 0;

	public static String pathSeparator = (System.getProperty("os.name").startsWith("Windows")?"\\":"/");

	public static Configuration config = null;

	/**
	 *  Gets property values from properties, checks configuration, parses values
	 */
	public Launcher() {
		System.out.print("Root: ");
		System.out.println(FileSystemView.getFileSystemView().getRoots()[0]);
		System.out.print("Home: ");
		System.out.println(FileSystemView.getFileSystemView().getHomeDirectory());
		System.out.print("Local: ");
		System.out.println(System.getProperty("user.dir"));
		
		// Read project properties file
		initProperties();

		// Console output for diagnostics
		System.out.print("Launching " + projectName);
		System.out.println(" M" + buildID);
		System.out.println("OS: " + System.getProperty("os.name"));

		// Launch Mac System Preferences
		if (System.getProperty("os.name").contains("Mac")) initMac();

		// Launch System Look and Feel
		initLookAndFeel();

		// Get config if it exists and launch the interface
		if (fileExists("res" + pathSeparator + "config.json")) {
			Configuration c = null;
			try {
				c = Configuration.readConfigJSON();
			} catch (FileNotFoundException e) {
				Logging.errorMessage(1, null, "Configuration Read Error", "Configuration file was not found", e);
			} catch (IOException e) {
				Logging.errorMessage(1, null, "Configuration Read Error", "Failed or interrupted I/O operations on configuration read", e);
			} catch (ParseException e) {
				Logging.errorMessage(1, null, "Configuration Read Error", "Failed to read config file", e);
			}
			new Notepad(c);
		} else {
			new Notepad(null);
		}

	}

	private void initProperties() {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = Launcher.class.getResourceAsStream("/quaver.properties");
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			projectName = prop.getProperty("project.name");
			buildRelease = Integer.parseInt(prop.getProperty("build.release"));
			buildMajor = Integer.parseInt(prop.getProperty("build.major"));
			buildMinor = Integer.parseInt(prop.getProperty("build.minor"));

			buildID = buildRelease + "." + buildMajor + " r" + buildMinor;

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void initLookAndFeel() {

		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}
	}

	private void initMac() {
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Quaver");
		System.setProperty("apple.awt.fileDialogForDirectories", "true");
		System.setProperty("apple.laf.useScreenMenuBar", "true");
	}

	/**
	 * Simple check if a file exists
	 * 
	 * @param location - path to file
	 * @return Returns true if the file exists
	 */
	public boolean fileExists(String location) {
		File f = new File(location);
		if(f.exists() && !f.isDirectory()) return true;
		else return false;
	}

	public static void main(String[] args) {
		new Launcher();
	}

	/**
	 * Terminate runtime
	 * @param status - ( 0 - Success, 1 - Failed )
	 * @param reason - Cause of termination
	 */
	public static void exit(int status, String reason) {
		System.err.println("Exiting: " + reason);
		System.exit(status);
	}

}
