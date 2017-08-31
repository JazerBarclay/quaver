package tech.tora.quaver.notepad.screen;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import tech.tora.quaver.notepad.layout.StandardLayout;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Cell;
import tech.tora.quaver.types.CellType;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.list.BasicClickListNode;
import tech.tora.tools.swing.list.BasicListNode;
import tech.tora.tools.swing.list.ClickListener;

public class StandardScreen extends StandardLayout {

	private Library activeLibrary = null;
	private Notebook activeNotebook = null;
	private Note activeNote = null;
	
	public StandardScreen(Theme theme, String projectName, int release, int major, int minor) {
		super(theme, projectName, release, major, minor);
	}

	@Override
	public void addLibraryToList(Library library) {
		notebooksList.addNode(new BasicListNode(20, library.getPath() + library.getName(), library.getName(), 
				getTheme().notebookFillColour.addShade(-15, -15, -15), getTheme().notebookHoverColour.getAsColor(), 
				new Font("Helvetica", Font.BOLD, 10), getTheme().fontColour.getAsColor(), 0) {
		});
	}

	@Override
	public void addNotebookToList(Notebook notebook) {
		notebooksList.addNotebookNode("/icon1.png", notebook.getUUID(), "   " + notebook.getName(), 
				"" + notebook.getNoteAsArray().length, new ClickListener() {
			
			@Override
			public void onClick() {
				setActiveLibrary(notebook.getParent());
				setActiveNotebook(notebook);
				setActiveNote(null);
				
				notesList.clear();
				editArea.setText("");
				updatePreview("");
				
				for (Note n : notebook.getNoteAsArray()) {
					addNoteToList(n);
				}
			}
		});
	}

	@Override
	public void addNoteToList(Note note) {
		notesList.addNode(new BasicClickListNode(40, note.getUUID(), note.getTitle(), 
				getTheme().notebookFillColour.getAsColor(), getTheme().notebookHoverColour.getAsColor(), 
				new Font("Helvetica", Font.BOLD, 12), getTheme().fontColour.getAsColor(), -20) {
			@Override
			public void onClick() {
				notesList.onClick(this);
				
				setActiveNote(note);
				
				String text = "";
				for (Cell c : note.getCells()) {
					text+=("[~" + c.type + "~]");
					text+="\n";
					if (c.type.equals(CellType.CODE.type)) text+=("{~" + c.language + "~}");
					text+="\n";
					text+=c.data;
				}
			
				setEditText(text);
				
			}
			
		});
	}

	@Override
	public void editLibraryInList(Library library) {

	}

	@Override
	public void editNotebookInList(Notebook notebook) {

	}

	@Override
	public void editNoteInList(Note note) {

	}

	@Override
	public void removeLibraryFromList(Library library) {

	}

	@Override
	public void removeNotebookFromList(Notebook notebook) {

	}

	@Override
	public void removeNoteFromList(Note note) {

	}

	@Override
	public boolean saveLibraryToSystem(Library library) {
		return false;
	}

	@Override
	public boolean saveNotebookToSystem(Notebook notebook) {
		return false;
	}

	@Override
	public boolean saveNoteToSystem(Note note) {
		try {
			Note.writeContentJSON(note);
			Note.writeMetaJSON(note);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteLibraryFromSystem(Library library) {
		return false;
	}

	@Override
	public boolean deleteNotebookToSystem(Notebook notebook) {
		return false;
	}

	@Override
	public boolean deleteNoteToSystem(Note note) {
		return false;
	}
	

	// Actives
	
	@Override
	public Library getActiveLibrary() {
		return activeLibrary;
	}

	@Override
	public void setActiveLibrary(Library library) {
		this.activeLibrary = library;
		// go through list and run active library click method
		if (library == null) return;
		for (String key : notebooksList.getNodeKeys()) {
			if (notebooksList.getNodes().get(key).UUID.equals(library.getPath() + File.separator + library.getName())) {
				System.out.println("ACTIVE: " + library.getName());
			}
		}
	}

	@Override
	public Notebook getActiveNotebook() {
		return activeNotebook;
	}

	@Override
	public void setActiveNotebook(Notebook notebook) {
		this.activeNotebook = notebook;
		if (notebook == null) return;
		for (String key : notebooksList.getNodeKeys()) {
			if (notebooksList.getNodes().get(key).UUID.equals(notebook.getUUID())) {
				notebooksList.onClick((BasicListNode)notebooksList.getNodes().get(key));
				notesList.clear();
				editArea.setText("");
				updatePreview("");
				
				for (Note n : notebook.getNoteAsArray()) {
					addNoteToList(n);
				}
				
			}
		}
	}

	@Override
	public Note getActiveNote() {
		return activeNote;
	}

	@Override
	public void setActiveNote(Note note) {
		this.activeNote = note;
		if (note == null) return;
		// go through notes list and run active notes click method
		for (String key : notesList.getNodeKeys()) {
			if (notesList.getNodes().get(key).UUID.equals(note.getUUID())) {
				notesList.onClick((BasicListNode)notesList.getNodes().get(key));
				
				String text = "";
				for (Cell c : note.getCells()) {
					text+=("[~" + c.type + "~]");
					text+="\n";
					if (c.type.equals(CellType.CODE.type)) text+=("{~" + c.language + "~}");
					text+="\n";
					text+=c.data;
				}
				setEditText(text);

			}
		}
	}

	// Edits
	
	@Override
	public void setEditText(String text) {
		editArea.setText(text);
		editArea.setCaratPos(0);
	}

	@Override
	public String getEditText() {
		return editArea.getText();
	}

	@Override
	public void clearEditText() {
		setEditText("");
		updatePreview("");
	}

	@Override
	public void updatePreview(Cell[] cells) {
		
	}

	@Override
	public void updatePreview(String notes) {
		
	}

}
