package tech.tora.quaver.types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tech.tora.tools.system.CustomUUID;

public class Notebook {

	private Library parent = null;
	
	/** Path to the notebook (not including the notebook folder) **/
	private String path = "";
	
	/** Name of the notebook (not including the extension) **/
	private String name = "";
	
	/** UUID of the notebook (unique identifier of notebook) **/
	private String uuid = "";

	
	/** Notebook extension (global notebook extension) **/
	private static final String extension = ".qvnotebook";

	/** Map of all notes in this notebook **/
	private LinkedHashMap<String, Note> noteArray;

	
	/* ------------------------------------------------------ */
	// Constructors and Initialisation
	/* ------------------------------------------------------ */
	
	/**
	 * Initialise the notebook without specifying the path or name
	 */
	public Notebook() {
		init();
	}
	
	/**
	 * Initialises the notebook using a UUID, name and path
	 * 
	 * @param uuid
	 * @param name
	 * @param path
	 */
	public Notebook(String uuid, String name, String path) {
		this.uuid = uuid;
		this.name = name;
		this.path = path;
		init();
	}
	
	private void init() {
		noteArray = new LinkedHashMap<>();
	}

	
	/* ------------------------------------------------------ */
	// Static Methods
	/* ------------------------------------------------------ */
	
	public static boolean isNotebook(File f) {
		if (f.isDirectory() && 
				f.getAbsolutePath().endsWith(".qvnotebook") && 
				new File(f.getAbsolutePath()+File.separator + "meta.json").exists()) {
			return true;
		}
		return false;
	}
	

	/* ------------------------------------------------------ */
	// Core Methods
	/* ------------------------------------------------------ */
	
	public static Notebook newNotebook(String name, Library parent) {
		Notebook n = new Notebook();
		n.setUUID(CustomUUID.generateTimestampUUID(name));
		n.setName(name);
		n.setPath(parent.getPath()+File.separator+parent.getName()+Library.getExtension());
		return n;
	}
	
	/**
	 * Checks if the notebook exists on the file system using 
	 * the qualified path and notebook name
	 * 
	 * @param path
	 * @param notebookName
	 * @return existence of notebook
	 */
	public static boolean exists(String path, String notebookName) {
		if (new File(path + File.separator +  "meta.json").exists()) return true;
		return false;
	}
	
	/**
	 * Adds a note to the notebook
	 * 
	 * @param note
	 * @return true if successful
	 */
	public boolean addNote(Note n) {
		// If the key exists in the notebook map then fail it
		for (String key : noteArray.keySet()) {
			if (noteArray.get(key).getUUID().equals(n.getUUID())) return false;
		}
		n.setParent(this);
		// Add to map
		noteArray.put(n.getUUID(), n);
		return true;
	}
	
	/**
	 * Writes a meta JSON formatted document containing the notebook details
	 * in the notebook's path and name specified
	 * 
	 * @param notebook
	 * @throws IOException
	 */
	public static void writeMetaJSON(Notebook notebook) throws IOException {
		writeJSON(notebook.uuid, notebook.name, notebook.path);
	}
	
	/**
	 * Writes a meta JSON formatted document containing the notebook details
	 * in the path and name specified
	 * 
	 * @param uuid
	 * @param name
	 * @param path
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void writeJSON(String uuid, String name, String path) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("uuid", uuid);
		obj.put("name", name);

		try {
			File f = new File(path + File.separator + "meta.json");
			f.getParentFile().mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot create path to file");
			return;
		}

		try (FileWriter file = new FileWriter(path + File.separator + "meta.json")) {
			file.write(obj.toJSONString());
			file.flush();
			System.out.println("\nSuccessfully Copied JSON Object to File...");
			System.out.println("JSON Object: " + obj);
		}
	}

	/**
	 * Reads in the details from a meta JSON
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static Notebook readJSON(String path) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader(path + File.separator + "meta.json"));
		JSONObject jsonNoteObject = (JSONObject) obj;

		String id = (String) jsonNoteObject.get("uuid");
		String name = (String) jsonNoteObject.get("name");
		String dir = path;

		return new Notebook(id, name, dir);
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	
	public void setParent(Library parent) {
		this.parent = parent;
	}

	public Library getParent() {
		return parent;
	}
	
	/**
	 * @return the noteArray
	 */
	public LinkedHashMap<String, Note> getNoteArrayAsMap() {
		return noteArray;
	}
	
	public Note[] getNoteAsArray() {
		Note[] nArray = new Note[getNoteCount()];
		int i = 0;
		for (String key : noteArray.keySet()) {
			nArray[i] = noteArray.get(key);
			i++;
		}
		return nArray;
	}
	
	public int getNoteCount() {
		return noteArray.size();
	}
	
	public static String getExtension() {
		return extension;
	}
	
	
}
