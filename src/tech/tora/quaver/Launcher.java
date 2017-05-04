package tech.tora.quaver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;

import tech.tora.quaver.notepad.Interface;
import tech.tora.quaver.types.Cell;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;

public class Launcher {

	public static Configuration config;
	
	public static Interface iFace;

	public static String pathSeparator = (System.getProperty("os.name").startsWith("Windows")?"\\":"/");

	public Launcher() {

		System.out.println("Launching Quaver");
		System.out.println("OS: " + System.getProperty("os.name"));

		// If configuration file cannot be found, generate new one
		if (!fileExists("res" + pathSeparator + "config.json")) {
			System.out.println("Config File Not Found");
			int cancel = 1;
			while (cancel == 1) {
				int failedConfigDialog = JOptionPane.showConfirmDialog(null, "The config file could not be found.\nA new config file can be created.\nCreate now?", "Config File Not Found", JOptionPane.OK_CANCEL_OPTION);
				switch (failedConfigDialog) {
				case 0:
					System.out.println("Creating New Config File");
					createConfigFile();
					createFirstLibrary();

					int wiki = JOptionPane.showConfirmDialog(null, "Would you like a local copy of the Quaver wiki as a notebook?", "Add local wiki of Quaver", JOptionPane.YES_NO_OPTION);
					if (wiki == 0) createWikiGuide();

					cancel = 0;
					break;
				case 2:
					cancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit setup", "Quit Setup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (cancel == 0) Launcher.exit(0, "Closing as no config file found and no new one created");
					break;
				default:
					Launcher.exit(0, "Closing as no config file found and no new one created");
					break;
				}
			}
		}

		// Read the configuration file in
		try {
			config = Configuration.readConfigJSON();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Launcher.exit(1, "Configuration file was not found");
		} catch (IOException e) {
			e.printStackTrace();
			Launcher.exit(1, "Failed or interrupted I/O operations on configuration read");
		} catch (ParseException e) {
			e.printStackTrace();
			Launcher.exit(1, "Failed to read config file");
		}

		// Print configuration
		System.out.println("");
		System.out.println("Name: " + config.name);
		System.out.println("Devmode: " + config.devmode);
		System.out.println("Libraries:");
		for (String s : config.libraries) System.out.println("  " + s);

		if (config.libraries.length < 1) {
			int r = JOptionPane.showConfirmDialog(null, "No libraries found in the configuration file\nWould you like to add one now?", "Add first library", JOptionPane.YES_NO_OPTION);
			if (r == 0) {
				createFirstLibrary();
				int wiki = JOptionPane.showConfirmDialog(null, "Would you like a local copy of the Quaver wiki as a notebook?", "Add local wiki of Quaver", JOptionPane.YES_NO_OPTION);
				if (wiki == 0) createWikiGuide();
			}
			else {
				JOptionPane.showMessageDialog(null, "Tough shit, you need it to pass", "YOU SHALL NOT PASS!", JOptionPane.ERROR_MESSAGE);
				Launcher.exit(1, "No Library");
			}

		}

		// Launcher Interface
		iFace = new Interface();

	}

	/**
	 * Simple check if a file exists
	 * 
	 * @param location - path to file
	 * @return Returns true if the file exists
	 */
	public boolean fileExists(String location) {
		File f = new File(location);
		if(f.exists() && !f.isDirectory()) return true;
		else return false;
	}


	private void createConfigFile() {

		config = new Configuration();
		config.name = getTextInputPopup("Configuration Settings", "Name of this configuration", "Default");
		config.devmode = false;
		config.libraries = new String[]{};

		try {
			Configuration.writeConfigJSON(config);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to create the configuration\n" + e, "Failed configuration", JOptionPane.ERROR_MESSAGE);
			Launcher.exit(1, "Failed to create configuration file");
		}

	}

	private void createFirstLibrary() {

		String firstLibraryName = getTextInputPopup("Library Name", "Name your first library", "Quaver");

		String firstNotebookLocation = null;
		int cancel = 1;
		while (cancel == 1) {
			firstNotebookLocation = getPathInputPopup("Select new library location", new java.io.File(System.getProperty("user.home")));
			if (firstNotebookLocation == null) cancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit setup", "Quit Setup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			else cancel = 2;
			if (cancel == 0) Launcher.exit(0, "Closing as no location was specified for library");
		}
		String fullPath = firstNotebookLocation + Launcher.pathSeparator + firstLibraryName + ".qvlibrary";
		config.libraries = new String[] {fullPath};

		if (!new File(config.libraries[0]).mkdirs()) {
			Launcher.exit(1, "Failed to create new library directory");
		}

		try {
			Configuration.writeConfigJSON(config);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to update the configuration\n" + e, "Failed configuration", JOptionPane.ERROR_MESSAGE);
			Launcher.exit(1, "Failed to update configuration file");
		}

	}

	public void createWikiGuide() {
		String notebookName = "Getting Started";
		String library = config.libraries[0];
		boolean failed = false;
		Notebook notebook = new Notebook(library, notebookName, UUID.nameUUIDFromBytes(notebookName.getBytes()).toString());
		try {
			Notebook.writeJSON(notebook);
		} catch (IOException e) {
			e.printStackTrace();
			failed = true;
			JOptionPane.showMessageDialog(null, "Failed to write notebook "+notebookName+"\n" + e, "Failed notebook write", JOptionPane.ERROR_MESSAGE);
		}
		if (failed) return;
		Note note = new Note(notebook.path + Launcher.pathSeparator + notebook.name + ".qvnotebook", 
				"Welcome to Quaver", UUID.nameUUIDFromBytes("Welcome to Quaver".getBytes()).toString(), System.currentTimeMillis() / 1000L, System.currentTimeMillis() / 1000L);
		note.addCell(new Cell("markdown", null, "## Testing\nHopefully this works when its written into the note"));
		try {
			Note.writeContentJSON(note);
		} catch (IOException e) {
			e.printStackTrace();
			failed = true;
			JOptionPane.showMessageDialog(null, "Failed to write note content "+note.title+"\n" + e, "Failed note write", JOptionPane.ERROR_MESSAGE);
		}
		if (failed) return;
		try {
			Note.writeMetaJSON(note);
		} catch (IOException e) {
			e.printStackTrace();
			failed = true;
			JOptionPane.showMessageDialog(null, "Failed to write note meta "+note.title+"\n" + e, "Failed note write", JOptionPane.ERROR_MESSAGE);
		}
		if (failed) return;

	}

	private String getTextInputPopup(String title, String message, String defaultValue) {
		String value = (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.NO_OPTION, null, null, defaultValue);
		if (value == null) Launcher.exit(1, "Cancelled input: " + title + " : " + message);
		else if (value.equals("")) value = getTextInputPopup(title, message, defaultValue);
		return value;
	}

	private String getPathInputPopup(String title, File defaultPath) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(defaultPath);
		chooser.setDialogTitle(title);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().toString();
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		
		new Launcher();
	}

	public static void exit(int status, String reason) {
		System.err.println("Exiting: " + reason);
		System.exit(status);
	}

}
