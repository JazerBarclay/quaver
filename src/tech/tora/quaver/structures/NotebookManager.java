package tech.tora.quaver.structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NotebookManager {

	@SuppressWarnings("unchecked")
	public static void writeNotebookJSON(String name, String uuid, String path) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("uuid", uuid);

		try {
			File f = new File(path + "/meta.json");
			f.getParentFile().mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot create path to file");
			return;
		}

		try (FileWriter file = new FileWriter(path + "/meta.json")) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		}
	}

	public static NotebookMeta readConfigJSON(String path) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader(path + "/meta.json"));
		JSONObject jsonNoteObject = (JSONObject) obj;

		String id = (String) jsonNoteObject.get("uuid");
		String name = (String) jsonNoteObject.get("name");

		return new NotebookMeta(id, name);
	}

}
