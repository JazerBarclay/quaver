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
	
	public String name;
	public String[] libraries;
	public boolean devmode;
	public String theme;
	
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
	
}
