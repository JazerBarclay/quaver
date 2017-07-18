package tech.tora.quaver.notepad;

import tech.tora.quaver.Configuration;

public class Notepad {
	
	public static Configuration config;
	
	
	public boolean newBuild = false;
	
	public Notepad(Configuration c) {
		if (c == null) {
			c = new Configuration();
			newBuild = true;
			config = new Configuration();
			config.devmode = false;
			config.name = "Default";
			config.theme = "Default";
			config.libraries = new String[]{};
			System.out.println("New Build");
		} else {
			config = c;
			System.out.println("Found Config");
		}
	}
	
}
