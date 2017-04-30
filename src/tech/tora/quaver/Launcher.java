package tech.tora.quaver;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import tech.tora.quaver.config.Settings;
import tech.tora.quaver.config.SettingsManager;
import tech.tora.quaver.notepad.Interface;
import tech.tora.quaver.structures.NotebookManager;

public class Launcher {

	public static Settings globalSettings;
	
	public Launcher() {
		
		System.out.println("Launching");
		System.out.println(System.getProperty("os.name"));
		
		if (fileExists("res/config.json")) {
			System.out.println("Config File Found");
			
			try {
				globalSettings = SettingsManager.readConfigJSON();
			} catch (IOException | ParseException e) {
				e.printStackTrace();
				Launcher.exit(1, "Failed to read config file");
			}
			
			new Interface();
			
		} else {
			System.out.println("Config File Not Found");
			
			int failedConfigDialog = JOptionPane.showConfirmDialog(null, "The config file could not be found.\nA fresh config file can be created.\nCreate now?", "Config File Not Found", JOptionPane.OK_CANCEL_OPTION);

			switch (failedConfigDialog) {
				case 0:
					System.out.println("Creating New Config File");
					if (createConfigFile()) {
						new Interface();
					} else {
						Launcher.exit(0, "Failed to create config file");
					}
					break;
				case 2:
					Launcher.exit(0, "Closing as no config file found and no new one created");
					break;
				default:
					System.out.println("Trancendence");
					Launcher.exit(0, "Somehow entered default state in Launcher Switch Case");
					break;
			}
			
		} // End If
		
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
	
	/**
	 * Simple check if a directory exists
	 * 
	 * @param location - path to file
	 * @return Returns true if the file exists
	 */
	public boolean dirExists(String location) {
		File f = new File(location);
		if(f.exists() && f.isDirectory()) return true;
		else return false;
	}
	
	private boolean createConfigFile() {
		
		Settings tmpSettings = new Settings();
		
		String configName = generateConfigName();
		String firstNotebookName = generateNotebookName();
		String firstNotebookLocation = generateNotebookLocation();

		// Drop the last character if its a / or a \\
		if (firstNotebookLocation.substring(firstNotebookLocation.length()-1, firstNotebookLocation.length()).equals("/") || 
				firstNotebookLocation.substring(firstNotebookLocation.length()-1, firstNotebookLocation.length()).equals("\\")) {
			firstNotebookLocation = firstNotebookLocation.substring(0, firstNotebookLocation.length()-1);
		}
		
		tmpSettings.settings_title= configName;
		tmpSettings.notebooks = new String[] {firstNotebookLocation + "/" + firstNotebookName + ".qvnotebook"};
		
		// write config file using what we have here
		tmpSettings.devmode = false;
		try {
			SettingsManager.writeConfigJSON(tmpSettings);
		} catch (IOException e) {
			e.printStackTrace();
			Launcher.exit(1, "Failed to write config file");
		}
		
		// write first notebook folder and files
		try {
			NotebookManager.writeNotebookJSON(firstNotebookName, UUID.nameUUIDFromBytes(firstNotebookName.getBytes()).toString(), tmpSettings.notebooks[0]);
		} catch (IOException e) {
			e.printStackTrace();
			Launcher.exit(1, "Failed to write first notebook");
		}
		
		globalSettings = tmpSettings;
		
		return true;
	}
	
	private String generateConfigName() {
		String configName;
		
		configName = JOptionPane.showInputDialog(null, 
				"Please enter the name of this configuration", "Configuration Settings", JOptionPane.NO_OPTION);
		
		if (configName == null) {
			Launcher.exit(1, "Cancelled creation of new config");
		} else if (configName.equals("")) { 
			configName = generateConfigName();
		}
		
		return configName;
	}
	
	private String generateNotebookName() {
		String notebookName;
		
		notebookName = JOptionPane.showInputDialog(null, 
				"Please enter the name of your first notebook", "Create your first notebook", JOptionPane.NO_OPTION);
		
		if (notebookName == null) {
			Launcher.exit(1, "Cancelled creation of new notebook");
		} else if (notebookName.equals("")) {
			notebookName = generateNotebookName();
		}
		
		return notebookName;
	}
	
	private String generateNotebookLocation() {
		String notebookLocation;
		
		notebookLocation = JOptionPane.showInputDialog(null, 
				"Please enter the location of your notebook", "Create your first notebook", JOptionPane.NO_OPTION);
		
		if (notebookLocation == null) {
			Launcher.exit(1, "Cancelled creation of new notebook");
		} else if (notebookLocation.equals("")) {
			notebookLocation = generateNotebookLocation();
		}
		
		return notebookLocation;
	}
	
	public static void main(String[] args) {
		new Launcher();
	}

	public static void exit(int status, String reason) {
		System.err.println("Exiting: " + reason);
		System.exit(status);
	}
	
}
