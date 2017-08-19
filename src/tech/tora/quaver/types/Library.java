package tech.tora.quaver.types;

import java.io.File;
import java.util.LinkedHashMap;

import tech.tora.quaver.Launcher;

public class Library {
	
	/** Path to the library (not including the library folder) **/
	private String path = "";
	
	/** Name of the library (not including the extension) **/
	private String name = "";

	
	/** Library extension (global library extension) **/
	private static final String extension = ".qvlibrary";

//	/** Map of all notebooks in this library **/
//	private LinkedHashMap<String, Notebook> notebookArray;
	
	
	/* ------------------------------------------------------ */
	// Constructors and Initialisation
	/* ------------------------------------------------------ */
	
	/**
	 * Initialise the library without specifying the path or name
	 */
	public Library() {
		init();
	}
	
	/**
	 * Initialise the library with a path and name
	 * 
	 * @param path
	 * @param name
	 */
	public Library(String path, String name) {
		this.path = path;
		this.name = name;
		init();
	}
	
	private void init() {
//		notebookArray = new LinkedHashMap<>();
	}
	

	/* ------------------------------------------------------ */
	// Core Methods
	/* ------------------------------------------------------ */
	
	/**
	 * Check if the library exists in the filesystem
	 * 
	 * @param path
	 * @param libraryName
	 * @return
	 */
	public static boolean exists(String path, String libraryName) {
		if (new File(path + Launcher.pathSeparator + libraryName + extension).exists()) return true;
		return false;
	}
	
//	/**
//	 * Adds a notebook to the library
//	 * 
//	 * @param notebook
//	 * @return true if add successful
//	 */
//	public boolean addNotebook(Notebook n) {
//		// If the key exists in the notebook map then fail it
//		for (String key : notebookArray.keySet()) {
//			if (notebookArray.get(key).getUUID().equals(n.getUUID())) return false;
//		}
//		// Add to map
//		notebookArray.put(n.getUUID(), n);
//		return true;
//	}
//
//	/**
//	 * Returns the notebooks as a Notebook array
//	 * 
//	 * @return notebookArray
//	 */
//	public Notebook[] getNotebookAsArray() {
//		Notebook[] nbArray = new Notebook[getNotebookCount()];
//		int i = 0;
//		for (String key : notebookArray.keySet()) {
//			nbArray[i] = notebookArray.get(key);
//			i++;
//		}
//		return nbArray;
//	}
	
	
	/* ------------------------------------------------------ */
	// Getters and Setters
	/* ------------------------------------------------------ */
	
	/**
	 * Sets the path of the library
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * Return the path of the library
	 * 
	 * @return path
	 */
	public String getPath() {
		return this.path;
	}
	
	/**
	 * Sets the name of the library
	 * 
	 * @param path
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Return the name of the library
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
//	/**
//	 * Returns the number of notebooks stored in this library set
//	 * 
//	 * @return notebookCount
//	 */
//	public int getNotebookCount() {
//		return notebookArray.size();
//	}
	
	public static String getExtension() {
		return extension;
	}
	
}
