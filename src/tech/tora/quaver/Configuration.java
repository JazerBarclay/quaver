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
	private boolean devmode;
	private String[] libraries;

	public Configuration() {
		this(null, null, false);
	}
	
	public Configuration(String name, String theme, boolean devmode) {
		this.name = name;
		this.theme = theme;
		this.devmode = devmode;
		libraries = new String[] {};
	}
	
	@SuppressWarnings("unchecked")
	public static void writeConfigJSON(Configuration config) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("config_name", config.name);
		obj.put("devmode", config.devmode);
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