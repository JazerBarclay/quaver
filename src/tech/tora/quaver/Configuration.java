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
	
	private String name;
	private String theme;
	private boolean mac_menu_enabled;
	private boolean track_external;
	private int track_external_ms;
	private boolean adv_autosave;
	private boolean devmode;
	private String[] libraries;

	public Configuration() {
		this(null, null, true, true, 1000, true, false);
	}
	
	public Configuration(
			String name, 
			String theme, 
			boolean mac_menu_enabled, 
			boolean track_external, 
			int track_external_ms, 
			boolean adv_autosave, 
			boolean devmode) 
	{
		this.name = name;
		this.theme = theme;
		this.mac_menu_enabled = mac_menu_enabled;
		this.track_external = track_external;
		this.track_external_ms = track_external_ms;
		this.adv_autosave = adv_autosave;
		this.devmode = devmode;
		libraries = new String[] {};
	}
	
	@SuppressWarnings("unchecked")
	public static void writeConfigJSON(Configuration config) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("config_name", config.name);
		obj.put("devmode", config.devmode);
		obj.put("mac_menu_enabled", config.mac_menu_enabled);
		obj.put("track_external", config.track_external);
		obj.put("track_external_ms", config.track_external_ms);
		obj.put("adv_autosave", config.adv_autosave);
		obj.put("theme", config.theme);

		JSONArray librariesJSON = new JSONArray();
		
		for (String notebook : config.libraries) librariesJSON.add(notebook);
		obj.put("libraries", librariesJSON);

		if (!new File("res").exists()) if (!new File("res").mkdirs())
					throw new IOException("Res directory could not be created");
		
		try (FileWriter file = new FileWriter("res" + File.separator + "config.json")) {
			file.write(obj.toJSONString());
			file.flush();
			System.out.println("\nSuccessfully Copied JSON Object to File...");
			System.out.println("JSON Object: " + obj + "");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Configuration readConfigJSON() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Configuration config = new Configuration();

		Object obj = parser.parse(new FileReader("res" + File.separator + "config.json"));

		JSONObject jsonObject = (JSONObject) obj;

		String name = (String) jsonObject.get("config_name");
		boolean devmode = (boolean) jsonObject.get("devmode");
		boolean mac_menu_enabled = (boolean) jsonObject.get("mac_menu_enabled");
		boolean track_external = (boolean) jsonObject.get("track_external");
		int track_external_ms = (Integer) jsonObject.get("track_external_ms");
		boolean adv_autosave= (Boolean) jsonObject.get("adv_autosave");
		String theme = (String) jsonObject.get("theme");
		JSONArray libraries = (JSONArray) jsonObject.get("libraries");

		// TODO - Do checks on all values before passing to config variables
		
		config.name = name;
		config.devmode = devmode;
		config.mac_menu_enabled = mac_menu_enabled;
		config.track_external = track_external;
		config.track_external_ms = track_external_ms;
		config.adv_autosave = adv_autosave;
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

	public static Configuration generateDefault() {
		return new Configuration("Default", "Default", false, false, 1000, false, true);
	}
	
	/* ------------------------------------------------------ */
	// Getters and Setters
	/* ------------------------------------------------------ */
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public String getTheme() {
		return theme;
	}

	public void setDevmode(boolean devmode) {
		this.devmode = devmode;
	}
	
	public boolean isDevmode() {
		return devmode;
	}

	public String[] getLibraries() {
		return libraries;
	}
	
}