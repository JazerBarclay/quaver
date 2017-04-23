package tech.tora.quaver;

import java.io.File;

import javax.swing.JOptionPane;

import tech.tora.quaver.config.Settings;
import tech.tora.quaver.notepad.Interface;

public class Launcher {

	public static Settings globalSettings;
	
	public Launcher() {
		
		System.out.println("Launching");
		
		if (fileExists("res/config.json")) {
			System.out.println("Config File Found");
			
		} else {
			System.out.println("Config File Not Found");
			
			int failedConfigDialog = JOptionPane.showConfirmDialog(null, "The config file could not be found.\nA fresh config file can be created.\nCreate now?", "Config File Not Found", JOptionPane.OK_CANCEL_OPTION);

			switch (failedConfigDialog) {
				case 0:
					// create config file
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
	
	private boolean createConfigFile() {
		
		return true;
	}
	
	
	
	public static void main(String[] args) {
		new Launcher();
	}

	public static void exit(int status, String reason) {
		System.err.println("Exiting: " + reason);
		System.exit(status);
	}
	
}
