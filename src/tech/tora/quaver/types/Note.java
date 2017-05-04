package tech.tora.quaver.types;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import tech.tora.quaver.Launcher;

public class Note {

	public String path = "";
	public String title = "";
	public String uuid = "";
	public long created_at = 0, updated_at = 0;
	public Cell[] cells = new Cell[] {};
	public String[] tags = new String[] {};

	public Note() {
		// Do nothing
	}
	
	public Note(String path, String title, String uuid, long created, long updated, String...tags) {
		this.path = path;
		this.title = title;
		this.uuid = uuid;
		this.created_at = created;
		this.updated_at = updated;
		this.tags = tags;
	}
	
	@SuppressWarnings("unchecked")
	public static void writeContentJSON(Note note) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("title", note.title);
		System.out.println("Title Added: " + note.title);

		JSONArray cells = new JSONArray();

		System.out.println("Total Cells: " + note.cells.length);

		for (Cell c2: note.cells) {
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

		if (!new File(note.path + Launcher.pathSeparator + note.uuid).exists()) {
			if (!new File(note.path + Launcher.pathSeparator + note.uuid).mkdirs()) 
				throw new IOException("Failed to create path " + note.path + Launcher.pathSeparator + note.uuid);
		}
		
		try (FileWriter file = new FileWriter(note.path + Launcher.pathSeparator + note.uuid + Launcher.pathSeparator + "content.json")) {
			file.write(obj.toJSONString());
			file.flush();
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		}
	}

	@SuppressWarnings("unchecked")
	public static void writeMetaJSON(Note note) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("title", note.title);
		obj.put("uuid", note.uuid);
		obj.put("created_at", note.created_at);
		obj.put("updated_at", note.updated_at);

		JSONArray tags = new JSONArray();

		if (note.tags.length > 0) for (String t : note.tags) tags.add(t);

		obj.put("tags", tags);

		if (!new File(note.path + Launcher.pathSeparator + note.uuid).exists()) {
			if (!new File(note.path + Launcher.pathSeparator + note.uuid).mkdirs()) 
				throw new IOException("Failed to create path " + note.path + Launcher.pathSeparator + note.uuid);
		}
		
		try (FileWriter file = new FileWriter(note.path + Launcher.pathSeparator + note.uuid + Launcher.pathSeparator + "meta.json")) {
			file.write(obj.toJSONString());
			file.flush();
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		}
	}

	public void addCell(Cell cell) {
		System.out.println("Length " + cells.length);
		Cell[] tmpArray = new Cell[cells.length+1];
		System.out.println("New Length " + tmpArray.length);
		for (int i = 0; i < cells.length; i++) tmpArray[i] = cells[i];
		tmpArray[cells.length] = cell;
		cells = tmpArray;
	}
	
}
