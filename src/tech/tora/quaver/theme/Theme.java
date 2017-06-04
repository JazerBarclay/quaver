package tech.tora.quaver.theme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tech.tora.quaver.Launcher;

public class Theme {

	public String name = "";
	public String fillColour= "";
	
	public Theme() {
		// Do Nothing
	}
	
	/**
	 * Writes a theme to filesystem
	 * 
	 * @param config - Template configuration to be saved
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void writeThemeJSON(Theme t) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("theme_name", t.name);
		obj.put("fill_colour", t.fillColour);
		
		if (!new File("res"+ Launcher.pathSeparator +"themes").exists()) if (!new File("res"+ Launcher.pathSeparator +"themes").mkdirs()) throw new IOException("Themes directory could not be created");
		
		try (FileWriter file = new FileWriter("res" + Launcher.pathSeparator + "themes" + Launcher.pathSeparator +t.name + ".json")) {
			file.write(obj.toJSONString());
			file.flush();
			System.out.println("\nSuccessfully Copied JSON Object to File...");
			System.out.println("JSON Object: " + obj + "");
		}
	}
	
	/**
	 * Reads the theme file
	 * 
	 * @return - Configuration file settings
	 * 
	 * @throws ParseException - Structure or variable failure
	 * @throws IOException - Read file failed
	 */
	public static Theme readConfigJSON(String themeName) throws IOException, ParseException, FileNotFoundException {
		JSONParser parser = new JSONParser();
		Theme t = new Theme();

		if (!new File("res"+Launcher.pathSeparator+"themes"+Launcher.pathSeparator+themeName+".json").exists()) throw new FileNotFoundException("Unable to locate " + themeName+".json");
		
		Object obj = parser.parse(new FileReader("res" + Launcher.pathSeparator + themeName + Launcher.pathSeparator +"config.json"));

		JSONObject jsonObject = (JSONObject) obj;
		
		t.name  = (String) jsonObject.get("theme_name");
		t.fillColour  = (String) jsonObject.get("fill_colour");
		
		return t;
	}
	
}
