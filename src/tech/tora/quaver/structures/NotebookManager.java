package tech.tora.quaver.structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tech.tora.quaver.Launcher;

@Deprecated
public class NotebookManager {

	@SuppressWarnings("unchecked")
	public static void writeNotebookJSON(String name, String uuid, String path) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("uuid", uuid);

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
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("JSON Object: " + obj + "\n");
		}
	}

	public static NotebookMeta readConfigJSON(String path) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader(path + Launcher.pathSeparator + "meta.json"));
		JSONObject jsonNoteObject = (JSONObject) obj;

		String id = (String) jsonNoteObject.get("uuid");
		String name = (String) jsonNoteObject.get("name");

		return new NotebookMeta(name, id);
	}
	
	public static NotebookMeta[] addNotebook(NotebookMeta[] notebooks, NotebookMeta notebookMeta) {
		NotebookMeta[] tmpNotebookArray = new NotebookMeta[notebooks.length+1];
		for (int i = 0; i < notebooks.length; i++) tmpNotebookArray[i] = notebooks[i];
		tmpNotebookArray[notebooks.length] = notebookMeta;
		return tmpNotebookArray;
	}

}
