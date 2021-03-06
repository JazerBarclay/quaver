package tech.tora.quaver.types;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

		JSONArray cells = new JSONArray();

		for (Cell c2: note.cells) {
			JSONObject c = new JSONObject();
			c.put("type", c2.type);
			c.put("language", c2.language);
			c.put("data", c2.data);
			cells.add(c);
		}

		obj.put("cells", cells);

		if (!new File(note.path + Launcher.pathSeparator + note.uuid + ".qvnote").exists()) {
			if (!new File(note.path + Launcher.pathSeparator + note.uuid + ".qvnote").mkdirs()) 
				throw new IOException("Failed to create path " + note.path + Launcher.pathSeparator + note.uuid + ".qvnote");
		}

		try (FileWriter file = new FileWriter(note.path + Launcher.pathSeparator + note.uuid + ".qvnote" + Launcher.pathSeparator + "content.json")) {
			file.write(obj.toJSONString());
			file.flush();
			System.out.println("\nSuccessfully Copied JSON Object to File...");
			System.out.println("JSON Object: " + obj);
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

		if (!new File(note.path + Launcher.pathSeparator + note.uuid + ".qvnote").exists()) {
			if (!new File(note.path + Launcher.pathSeparator + note.uuid + ".qvnote").mkdirs()) 
				throw new IOException("Failed to create path " + note.path + Launcher.pathSeparator + note.uuid + ".qvnote");
		}

		try (FileWriter file = new FileWriter(note.path + Launcher.pathSeparator + note.uuid + ".qvnote" + Launcher.pathSeparator + "meta.json")) {
			file.write(obj.toJSONString());
			file.flush();
			System.out.println("\nSuccessfully Copied JSON Object to File...");
			System.out.println("JSON Object: " + obj);
		}
	}

	/**
	 * Checks for the contents file and reads the values
	 * @throws ParseException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Note readContentsJSON(String path) throws ParseException, IOException {
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader(path + Launcher.pathSeparator + "content.json"));
		JSONObject jsonNoteObject = (JSONObject) obj;

		String title = (String) jsonNoteObject.get("title");
		JSONArray cellsListArray = (JSONArray) jsonNoteObject.get("cells");

		Note newNote = new Note();
		newNote.path = path;
		newNote.title = title;

		Iterator<JSONObject> cellsIterator = cellsListArray.iterator();
		JSONObject nestedCellObject;
		Cell tempCellForLoop;
		while (cellsIterator.hasNext()) {
			nestedCellObject = cellsIterator.next();
			tempCellForLoop = new Cell();

			tempCellForLoop.type = (String) nestedCellObject.get("type");
			tempCellForLoop.language = (String) nestedCellObject.get("language");
			tempCellForLoop.data = (String) nestedCellObject.get("data");

			newNote.addCell(tempCellForLoop);
		}
		return newNote;
	}

	/**
	 * Checks for the meta file and reads the values
	 * @throws ParseException
	 * @throws IOException
	 */
	public static Note readMetaJSON(String path) throws ParseException, IOException {
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader(path + Launcher.pathSeparator + "meta.json"));
		JSONObject jsonNoteObject = (JSONObject) obj;

		String title = (String) jsonNoteObject.get("title");
		String id = (String) jsonNoteObject.get("uuid");
		long created = (long) jsonNoteObject.get("created_at");
		long updated = (long) jsonNoteObject.get("updated_at");
		JSONArray tagsJSONArray = (JSONArray) jsonNoteObject.get("tags");

		Note m = new Note();
		m.path = path;
		m.title = title;
		m.uuid = id;
		m.created_at = created;
		m.updated_at = updated;

		@SuppressWarnings("unchecked")
		Iterator<String> cellsIterator = tagsJSONArray.iterator();
		while (cellsIterator.hasNext()) {
			String tag = cellsIterator.next();
			m.addTag(tag);
		}
		return m;
	}

	public void addTag(String tag) {
		String[] tmp = new String[tags.length+1];
		for (int i = 0; i < tags.length; i++) tmp[i] = tags[i];
		tmp[tmp.length-1] = tag;
		tags = tmp;
	}

	public void addCell(Cell cell) {
		Cell[] tmpArray = new Cell[cells.length+1];
		for (int i = 0; i < cells.length; i++) tmpArray[i] = cells[i];
		tmpArray[cells.length] = cell;
		cells = tmpArray;
	}

}
