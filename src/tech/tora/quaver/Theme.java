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

	public String themeName;
	public ColourValue fontColour;
	public ColourValue wrapperFillColour;
	public ColourValue notebookFillColour;
	public ColourValue notebookHoverColour;
	public ColourValue noteFillColour;
	public ColourValue noteHoverColour;
	public ColourValue editFontColour;
	public ColourValue editFillColour;
	public ColourValue previewFontColour;
	public ColourValue previewFillColour;
	public ColourValue borderColour;

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
		obj.put("font_colour", t.fontColour);
		obj.put("wrapper_fill_colour", t.wrapperFillColour);
		obj.put("notebook_fill_colour", t.notebookFillColour);
		obj.put("notebook_hover_colour", t.notebookHoverColour);
		obj.put("note_fill_colour", t.noteFillColour);
		obj.put("note_hover_colour", t.noteHoverColour);
		obj.put("edit_fill_colour", t.editFillColour);
		obj.put("preview_font_colour", t.previewFontColour);
		obj.put("preview_fill_colour", t.previewFillColour);
		obj.put("border_colour", t.borderColour);

		if (!new File("res").exists()) if (!new File("res").mkdirs()) throw new IOException("Res directory could not be created");
		
		try (FileWriter file = new FileWriter("res" + Launcher.pathSeparator + t.themeName + ".json")) {
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
		t.fontColour = (ColourValue) jsonObject.get("font_colour");
		t.wrapperFillColour = (ColourValue) jsonObject.get("wrapper_fill_colour");
		t.notebookFillColour = (ColourValue) jsonObject.get("notebook_fill_colour");
		t.notebookHoverColour = (ColourValue) jsonObject.get("notebook_hover_colour");
		t.noteFillColour = (ColourValue) jsonObject.get("note_fill_colour");
		t.noteHoverColour = (ColourValue) jsonObject.get("note_hover_colour");
		t.editFillColour = (ColourValue) jsonObject.get("edit_fill_colour");
		t.previewFontColour = (ColourValue) jsonObject.get("preview_font_colour");
		t.previewFillColour = (ColourValue) jsonObject.get("preview_fill_colour");
		t.borderColour = (ColourValue) jsonObject.get("border_colour");
		
		return t;
	}
	
}
