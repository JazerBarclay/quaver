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
import tech.tora.tools.system.CustomUUID;

public class Note {
	
	private Notebook parent = null;

	/** Path to the note (not including the note folder itself) **/
	private String path = "";
	
	/** Title of the note (not including the extension) **/
	private String title = "";
	
	/** Unique Identifier of the note **/
	private String uuid = "";
	
	/** Time stamp of creation and last modified **/
	private long created_at = 0, updated_at = 0;
	
	/** All cells that are contained in the note **/
	private Cell[] cells = new Cell[] {};
	
	/** Tags that are used to identify the note **/
	private String[] tags = new String[] {};
	

	/** Note extension (global note extension) **/
	private static final String extension = ".qvnote";

	/* ------------------------------------------------------ */
	// Constructors and Initialisation
	/* ------------------------------------------------------ */
	
	/**
	 * Initialise the note without specifying the path, title or UUID
	 */
	public Note() {
		init();
	}

	/**
	 * Initialise the notebook with all required details
	 * 
	 * @param path
	 * @param title
	 * @param uuid
	 * @param created
	 * @param updated
	 * @param tags
	 */
	public Note(String uuid, String title, long created, long updated, String path, String...tags) {
		this.path = path;
		this.title = title;
		this.uuid = uuid;
		this.created_at = created;
		this.updated_at = updated;
		this.tags = tags;
		init();
	}
	
	/**
	 * Place holder for global initialisation of class for all constrctors
	 */
	private void init() {
		
	}
	
	
	/* ------------------------------------------------------ */
	// Static Methods
	/* ------------------------------------------------------ */

	public static boolean isNote(File f) {
		if (f.isDirectory() && 
				f.getAbsolutePath().endsWith(extension) && 
				new File(f.getAbsolutePath()+Launcher.pathSeparator+"content.json").exists() &&
				new File(f.getAbsolutePath()+Launcher.pathSeparator+"meta.json").exists()) {
			return true;
		}
		return false;
	}
	
	
	/* ------------------------------------------------------ */
	// Core Methods
	/* ------------------------------------------------------ */

	/**
	 * Generates a new note with automatic UUID v2 generation,
	 * time stamps and path generated fom parent
	 * 
	 * @param name
	 * @param parent
	 * @return
	 */
	public static Note newNote(String name, Notebook parent) {
		Note n = new Note();
		n.setUUID(CustomUUID.generateTimestampUUID(name));
		n.setTitle(name);
		n.setCreatedAt(System.currentTimeMillis() / 1000L);
		n.setUpdatedAt(System.currentTimeMillis() / 1000L);
		n.setPath(parent.getPath() + Launcher.pathSeparator + parent.getName() + Notebook.getExtension());
		return n;
	}
	
	/**
	 * Writes a note content JSON file to the file system using the note parameter specified
	 * 
	 * @param note
	 * @throws IOException Fails to write
	 */
	@SuppressWarnings("unchecked")
	public static void writeContentJSON(Note note) throws IOException {
		// TODO - Check if note contains all required fields!
		// TODO - Check if folder exists and is writable
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
		
		if (!new File(note.path).exists()) {
			if (!new File(note.path).mkdirs()) 
				throw new IOException("Failed to create path " + note.path);
		}

		try (FileWriter file = new FileWriter(note.path + Launcher.pathSeparator + "content.json")) {
			file.write(obj.toJSONString());
			file.flush();
			System.out.println("\nSuccessfully Copied JSON Object to File...");
			System.out.println("JSON Object: " + obj);
		}
	}

	/**
	 * Writes a note meta JSON file to the file system using the note parameter specified
	 * 
	 * @param note
	 * @throws IOException Fails to write
	 */
	@SuppressWarnings("unchecked")
	public static void writeMetaJSON(Note note) throws IOException {
		// TODO - Check if note contains all required fields!
		// TODO - Check if folder exists and is writable
		JSONObject obj = new JSONObject();
		obj.put("title", note.title);
		obj.put("uuid", note.uuid);
		obj.put("created_at", note.created_at);
		obj.put("updated_at", note.updated_at);

		JSONArray tags = new JSONArray();

		if (note.tags.length > 0) for (String t : note.tags) tags.add(t);

		obj.put("tags", tags);

		if (!new File(note.path).exists()) {
			if (!new File(note.path).mkdirs()) 
				throw new IOException("Failed to create path " + note.path);
		}

		try (FileWriter file = new FileWriter(note.path + Launcher.pathSeparator + "meta.json")) {
			file.write(obj.toJSONString());
			file.flush();
			System.out.println("\nSuccessfully Copied JSON Object to File...");
			System.out.println("JSON Object: " + obj);
		}
	}

	/**
	 * Checks for the contents file and reads the values
	 * 
	 * @param path - Content file's fully qualified path
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

	
	/* ------------------------------------------------------ */
	// Getters and Setters
	/* ------------------------------------------------------ */

	/**
	 * @param uuid the uuid to set
	 */
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the uuid
	 */
	public String getUUID() {
		return uuid;
	}
	
	/**
	 * @param path path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param created_at the created_at to set
	 */
	public void setCreatedAt(long createdAt) {
		this.created_at = createdAt;
	}

	/**
	 * @return the created_at
	 */
	public long getCreatedAt() {
		return created_at;
	}

	/**
	 * @param updated_at the updated_at to set
	 */
	public void setUpdatedAt(long updatedAt) {
		this.updated_at = updatedAt;
	}

	/**
	 * @return the updated_at
	 */
	public long getUpdatedAt() {
		return updated_at;
	}

	/**
	 * @return the cells
	 */
	public Cell[] getCells() {
		return cells;
	}

	/**
	 * @return the tags
	 */
	public String[] getTags() {
		return tags;
	}

	public static String getExtension() {
		return extension;
	}
	
	public void setParent(Notebook parent) {
		this.parent = parent;
	}
	
	public Notebook getParent() {
		return parent;
	}
	
}
