package tech.tora.quaver.notepad.screen;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import tech.tora.quaver.Configuration;
import tech.tora.quaver.Launcher;
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
	
	public StandardScreen(Configuration config, Theme theme) {
		super(theme);
	}

	@Override
	public void addLibrary(Library library) {
		notebooksList.addNode(new BasicListNode(20, library.getPath() + library.getName(), library.getName(), 
				theme.notebookFillColour.addShade(-15, -15, -15), theme.notebookHoverColour.getAsColor(), 
				new Font("Helvetica", Font.BOLD, 10), theme.fontColour.getAsColor(), 0) {
		});
	}

	@Override
	public void addNotebook(Notebook notebook) {
		
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
					addNote(n);
				}
			}
		});
		
	}

	@Override
	public void addNote(Note note) {
		notesList.addNode(new BasicClickListNode(40, note.getUUID(), note.getTitle(), 
				theme.notebookFillColour.getAsColor(), theme.notebookHoverColour.getAsColor(), 
				new Font("Helvetica", Font.BOLD, 12), theme.fontColour.getAsColor(), -20) {
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
	public void editLibrary(Library library) {
		
	}

	@Override
	public void editNotebook(Notebook notebook) {
		
	}

	@Override
	public void editNote(Note note) {

	}

	@Override
	public void removeLibrary(Library library) {

	}

	@Override
	public void removeNotebook(Notebook notebook) {

	}

	@Override
	public void removeNote(Note note) {

	}

	@Override
	public boolean saveLibrary(Library library) {
		return false;
	}

	@Override
	public boolean saveNotebook(Notebook notebook) {
		return false;
	}

	@Override
	public boolean saveNote(Note note) {
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
	public boolean deleteLibrary(Library library) {
		return false;
	}

	@Override
	public boolean deleteNotebook(Notebook notebook) {
		return false;
	}

	@Override
	public boolean deleteNote(Note note) {
		return false;
	}

	@Override
	public Library getActiveLibrary() {
		return activeLibrary;
	}

	@Override
	public Notebook getActiveNotebook() {
		return activeNotebook;
	}

	@Override
	public Note getActiveNote() {
		return activeNote;
	}

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
	@Deprecated
	public void updatePreview(Cell[] cells) {
		// Do Nothing
	}

	@Override
	public void updatePreview(String notes) {
		previewArea.setText(notes);
	}

	@Override
	public JMenuBar getMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu menuItem = new JMenu("File");
		menu.add(menuItem);
		return menu;
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
					addNote(n);
				}
				
			}
		}
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
	
}
