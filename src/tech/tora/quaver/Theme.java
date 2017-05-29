package tech.tora.quaver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tech.tora.quaver.colour.ColourValue;

public class Theme {

	private static String location = "res" + Launcher.pathSeparator + "themes";
	
	public String themeName = null;
	public ColourValue fontColour = null;
	public ColourValue wrapperFillColour = null;
	public ColourValue notebookFillColour = null;
	public ColourValue notebookHoverColour = null;
	public ColourValue noteFillColour = null;
	public ColourValue noteHoverColour = null;
	public ColourValue editFontColour = null;
	public ColourValue editFillColour = null;
	public ColourValue previewFontColour = null;
	public ColourValue previewFillColour = null;
	public ColourValue borderColour = null;

	/**
	 * Writes a blank configuration file
	 * 
	 * @param config - Template configuration to be saved
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void writeThemeJSON(Theme t) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("theme_name", t.themeName);
		obj.put("font_colour", t.fontColour.getHex());
		obj.put("wrapper_fill_colour", t.wrapperFillColour.getHex());
		obj.put("notebook_fill_colour", t.notebookFillColour.getHex());
		obj.put("notebook_hover_colour", t.notebookHoverColour.getHex());
		obj.put("note_fill_colour", t.noteFillColour.getHex());
		obj.put("note_hover_colour", t.noteHoverColour.getHex());
		obj.put("edit_fill_colour", t.editFillColour.getHex());
		obj.put("preview_font_colour", t.previewFontColour.getHex());
		obj.put("preview_fill_colour", t.previewFillColour.getHex());
		obj.put("border_colour", t.borderColour.getHex());

		if (!new File(location).exists()) if (!new File(location).mkdirs()) throw new IOException("Res directory could not be created");
		
		try (FileWriter file = new FileWriter(location + Launcher.pathSeparator + t.themeName + ".json")) {
			file.write(obj.toJSONString());
			file.flush();
			System.out.println("\nSuccessfully Copied JSON Object to File...");
			System.out.println("JSON Object: " + obj + "");
		}
	}
	
	/**
	 * Reads the theme file
	 * 
	 * @return - Theme file details
	 * 
	 * @throws ParseException - Structure or variable failure
	 * @throws IOException - Read file failed
	 */
	public static Theme readThemeJSON(String themeName) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Theme t = new Theme();

		Object obj = parser.parse(new FileReader("res" + Launcher.pathSeparator + themeName + ".json"));

		JSONObject jsonObject = (JSONObject) obj;

		t.themeName = (String) jsonObject.get("theme_name");
		t.fontColour = new ColourValue((String) jsonObject.get("font_colour"));
		t.wrapperFillColour = new ColourValue((String)  jsonObject.get("wrapper_fill_colour"));
		t.notebookFillColour = new ColourValue((String) jsonObject.get("notebook_fill_colour"));
		t.notebookHoverColour = new ColourValue((String) jsonObject.get("notebook_hover_colour"));
		t.noteFillColour = new ColourValue((String) jsonObject.get("note_fill_colour"));
		t.noteHoverColour = new ColourValue((String) jsonObject.get("note_hover_colour"));
		t.editFillColour = new ColourValue((String) jsonObject.get("edit_fill_colour"));
		t.previewFontColour = new ColourValue((String) jsonObject.get("preview_font_colour"));
		t.previewFillColour = new ColourValue((String) jsonObject.get("preview_fill_colour"));
		t.borderColour = new ColourValue((String) jsonObject.get("border_colour"));
		
		return t;
	}
	
}
