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
	
	public String config_title;
	public String[] bookLocations;
	public boolean devmode;
	
	/**
	 * Writes a blank configuration file
	 * 
	 * @param config - Template configuration to be saved
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void writeConfigJSON(Configuration config) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("config_name", config.config_title);
		obj.put("devmode", config.devmode);

		JSONArray notebooks = new JSONArray();
		
		for (String notebook : config.bookLocations) notebooks.add(notebook);
		obj.put("notebooks", notebooks);

		if (!new File("res").exists()) new File("res").mkdirs();
		
		try (FileWriter file = new FileWriter("res/config.json")) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
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

		Object obj = parser.parse(new FileReader("res/config.json"));

		JSONObject jsonObject = (JSONObject) obj;

		String name = (String) jsonObject.get("config_name");
		boolean devmode = (boolean) jsonObject.get("devmode");
		JSONArray notebookList = (JSONArray) jsonObject.get("notebooks");
		
		config.config_title  = name;
		config.devmode  = devmode;
		config.bookLocations = new String[notebookList.size()];
		
		Iterator<String> iterator = notebookList.iterator();
		int iteratorCount = 0;
		while (iterator.hasNext()) {
			config.bookLocations[iteratorCount] = iterator.next();
			iteratorCount++;
		}
		return config;
	}
	
	
}
