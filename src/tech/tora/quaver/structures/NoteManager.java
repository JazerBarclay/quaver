package tech.tora.quaver.structures;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Deprecated
public class NoteManager {

	@SuppressWarnings("unchecked")
	public static void writeContentJSON(NoteMeta note) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("title", note.title);
		System.out.println("Title Added: " + note.title);

		JSONArray cells = new JSONArray();

		System.out.println("Total Cells: " + note.cells.length);
		
		for (CellMeta c2: note.cells) {
			JSONObject c = new JSONObject();
			c.put("type", c2.type);
			System.out.println("Cell Type: " + c2.type);
			c.put("language", c2.language);
			System.out.println("Cell Lang: " + c2.language);
			c.put("data", c2.data);
			System.out.println("Cell Data: Just Trust");
			cells.add(c);
		}

		System.out.println("Added");
		obj.put("cells", cells);

		System.out.println("Testing Path Here: " + note.path);
		
		try (FileWriter file = new FileWriter(note.path)) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeMetaJSON(NoteMeta note) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("title", note.title);
		obj.put("uuid", note.uuid);
		obj.put("created_at", note.created_at);
		obj.put("updated_at", note.updated_at);

		JSONArray tags = new JSONArray();
		
		if (note.tags.length > 0) for (String t : note.tags) tags.add(t);

		obj.put("tags", tags);

		try (FileWriter file = new FileWriter(note.path)) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		}
}
	
}
