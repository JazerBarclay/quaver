package tech.tora.quaver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Configuration {
	
	/** Name of the configuration **/
	private String name;
	
	/** Theme name used for the interface on load **/
	private String theme;
	
	/** For programmers and debugging for clients during beta **/
	private boolean devmode;
	
	/** Array of all library locations across the file system **/
	private String[] libraries;
	
	
	/* ------------------------------------------------------ */
	// Constructors and Initialisation
	/* ------------------------------------------------------ */
	
	/**
	 * Initialise the configuration without specifying any fields
	 */
	public Configuration() {
		this(null, null, false);
	}
	
	/**
	 * Initialise the configuration with all required fields
	 * 
	 * @param name
	 * @param theme
	 * @param devmode
	 */
	public Configuration(String name, String theme, boolean devmode) {
		this.name = name;
		this.theme = theme;
		this.devmode = devmode;
		init();
	}

	/**
	 * Initialise variables and arrays
	 */
	private void init() {
		libraries = new String[] {};
	}
	
	
	/* ------------------------------------------------------ */
	// Core Methods
	/* ------------------------------------------------------ */
	
	/**
	 * Writes a blank configuration file
	 * 
	 * @param config - Template configuration to be saved
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void writeConfigJSON(Configuration config) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("config_name", config.name);
		obj.put("devmode", config.devmode);
		obj.put("theme", config.theme);

		JSONArray libraries = new JSONArray();
		
		for (String notebook : config.libraries) libraries.add(notebook);
		obj.put("libraries", libraries);

		if (!new File("res").exists()) if (!new File("res").mkdirs()) throw new IOException("Res directory could not be created");
		
		try (FileWriter file = new FileWriter("res" + Launcher.pathSeparator + "config.json")) {
			file.write(obj.toJSONString());
			file.flush();
			System.out.println("\nSuccessfully Copied JSON Object to File...");
			System.out.println("JSON Object: " + obj + "");
		}
	}
	
	/**
	 * Reads the configuration file
	 * 
	 * @return - Configuration file settings
	 * 
	 * @throws ParseException - Structure or variable failure
	 * @throws IOException - Read file failed
	 */
	@SuppressWarnings("unchecked")
	public static Configuration readConfigJSON() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Configuration config = new Configuration();

		Object obj = parser.parse(new FileReader("res" + Launcher.pathSeparator + "config.json"));

		JSONObject jsonObject = (JSONObject) obj;

		String name = (String) jsonObject.get("config_name");
		boolean devmode = (boolean) jsonObject.get("devmode");
		String theme = (String) jsonObject.get("theme");
		JSONArray libraries = (JSONArray) jsonObject.get("libraries");
		
		config.name  = name;
		config.devmode  = devmode;
		config.theme = theme;
		config.libraries = new String[libraries.size()];
		
		Iterator<String> iterator = libraries.iterator();
		int iteratorCount = 0;
		while (iterator.hasNext()) {
			config.libraries[iteratorCount] = iterator.next();
			iteratorCount++;
		}
		return config;
	}

	
	/* ------------------------------------------------------ */
	// Getters and Setters
	/* ------------------------------------------------------ */
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param devmode
	 */
	public void setDevmode(boolean devmode) {
		this.devmode = devmode;
	}

	/**
	 * @return devmode
	 */
	public boolean isDevmode() {
		return devmode;
	}

	/**
	 * @return librariesde
	 */
	public String[] getLibraries() {
		return libraries;
	}

	
}
