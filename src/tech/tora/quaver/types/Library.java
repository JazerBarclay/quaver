package tech.tora.quaver.types;

import java.io.File;
import java.util.LinkedHashMap;

import tech.tora.quaver.Launcher;

public class Library {
	
	public int notebookCount = 0;

	public String path = "";
	public String name = "";
	
	public LinkedHashMap<String, Notebook> notebookArray;
	
	public Library() {
		notebookArray = new LinkedHashMap<>();
	}
	
	/**
	 * 
	 * Check if the library exists in the filesystem
	 * 
	 * @param location
	 * @param libraryName
	 * @return
	 */
	public static boolean exists(String location, String libraryName) {
		if (new File(location + Launcher.pathSeparator + libraryName + ".qvlibrary").exists()) return true;
		return false;
	}
	
	/**
	 * 
	 * Adds a notebook to the library
	 * 
	 * @param notebook
	 * @return true if add successful
	 */
	public boolean addNotebook(Notebook n) {
		// If the key exists in the notebook map then fail it
		for (String key : notebookArray.keySet()) {
			if (notebookArray.get(key).uuid.equals(n.uuid)) return false;
		}
		// Add to map
		notebookArray.put(n.uuid, n);
		return true;
	}
	
}
