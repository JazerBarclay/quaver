package tech.tora.quaver.types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tech.tora.quaver.Launcher;

public class Notebook {
	
	public int noteCount = 0;
	
	public String path = "";
	public String name = "", uuid = "";
	
	public Notebook() {
		// Do Nothing
	}
	
	public Notebook(String uuid, String name, String path) {
		this.uuid = uuid;
		this.name = name;
		this.path = path;
	}
	
	public static void writeJSON(Notebook notebook) throws IOException {
		writeJSON(notebook.name, notebook.uuid, notebook.path);
	}
	
	@SuppressWarnings("unchecked")
	public static void writeJSON(String uuid, String name, String path) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("uuid", uuid);
		obj.put("name", name);

		try {
			File f = new File(path + Launcher.pathSeparator + name + ".qvnotebook" + Launcher.pathSeparator + "meta.json");
			f.getParentFile().mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot create path to file");
			return;
		}

		try (FileWriter file = new FileWriter(path + Launcher.pathSeparator + name + ".qvnotebook" + Launcher.pathSeparator + "meta.json")) {
			file.write(obj.toJSONString());
			file.flush();
			System.out.println("\nSuccessfully Copied JSON Object to File...");
			System.out.println("JSON Object: " + obj);
		}
	}

	public static Notebook readJSON(String path) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader(path + Launcher.pathSeparator + "meta.json"));
		JSONObject jsonNoteObject = (JSONObject) obj;

		String id = (String) jsonNoteObject.get("uuid");
		String name = (String) jsonNoteObject.get("name");

		return new Notebook(path, name, id);
	}
	
}
