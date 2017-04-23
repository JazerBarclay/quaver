package tech.tora.quaver.config;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SettingsManager {

	/**
	 * Writes a blank config file
	 * 
	 * @param s - Template config to be saved
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void writeConfigJSON(Settings s) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("config_name", s.settings_title);
		obj.put("devmode", s.devmode);

		JSONArray notebooks = new JSONArray();
		
		for (String notebook : s.notebooks) notebooks.add(notebook);

		obj.put("notebooks", notebooks);

		try (FileWriter file = new FileWriter("res/config.json")) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		}
	}
	
	/**
	 * Reads the config file
	 * 
	 * @return - Config file settings
	 * 
	 * @throws ParseException - Structure or variable failure
	 * @throws IOException - Read file failed
	 * @throws org.json.simple.parser.ParseException 
	 */
	@SuppressWarnings("unchecked")
	public static Settings readConfigJSON() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Settings config = new Settings();

		Object obj = parser.parse(new FileReader("res/config.json"));

		JSONObject jsonObject = (JSONObject) obj;

		String name = (String) jsonObject.get("config_name");
		boolean devmode = (boolean) jsonObject.get("devmode");
		JSONArray notebookList = (JSONArray) jsonObject.get("notebooks");
		

		config.settings_title  = name;
		config.devmode  = devmode;
		config.notebooks = new String[notebookList.size()];
		
		Iterator<String> iterator = notebookList.iterator();
		int iteratorCount = 0;
		while (iterator.hasNext()) {
			config.notebooks[iteratorCount] = iterator.next();
			iteratorCount++;
		}

		return config;

	}
	
}
