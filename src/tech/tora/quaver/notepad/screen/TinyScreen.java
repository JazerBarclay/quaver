package tech.tora.quaver.notepad.screen;

import java.awt.Font;
import java.io.File;

import tech.tora.quaver.notepad.layout.TinyLayout;
import tech.tora.quaver.notepad.template.TinyTemplate;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Cell;
import tech.tora.quaver.types.CellType;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.list.BasicClickListNode;
import tech.tora.tools.swing.list.BasicListNode;

public class TinyScreen extends TinyLayout {

	public TinyScreen(TinyTemplate template, Theme theme, String projectName, int release, int major, int minor) {
		super(template, theme, projectName, release, major, minor);
	}

	@Override
	public void addLibraryToList(Library library) {
		list.addNode(new BasicListNode(20, library.getPath() + library.getName(), library.getName(), 
				getTheme().notebookFillColour.addShade(-15, -15, -15), getTheme().notebookHoverColour.getAsColor(), 
				new Font("Helvetica", Font.BOLD, 10), getTheme().fontColour.getAsColor(), 0) {
		});
	}

	@Override
	public void addNotebookToList(Notebook notebook) {
		list.addNode(new BasicClickListNode(25, notebook.getUUID(), notebook.getName(), 
				getTheme().notebookFillColour.getAsColor(), getTheme().notebookHoverColour.getAsColor(), 
				new Font("Helvetica", Font.BOLD, 10), getTheme().fontColour.getAsColor(), 0) {

					@Override
					public void onClick() {
						setActiveLibrary(notebook.getParent());
						setActiveNotebook(notebook);
						setActiveNote(null);
						
						clearEditText();
						
						for (Note n : notebook.getNoteAsArray()) {
							addNoteToList(n);
						}
					}
		});
	}

	@Override
	public void addNoteToList(Note note) {
		list.addNode(new BasicClickListNode(25, note.getUUID(), note.getTitle(), 
				getTheme().noteFillColour.addShade(-10, -10, -10), getTheme().noteHoverColour.getAsColor(), 
				new Font("Helvetica", Font.BOLD, 12), getTheme().fontColour.getAsColor(), -20) {
			@Override
			public void onClick() {
				list.onClick(this);

				setActiveNote(note);

				String text = "";
				for (Cell c : note.getCells()) {
					text+=("[~" + c.type + "~]");
					text+="\n";
					if (c.type.equals(CellType.CODE.type) && c.language != null) text+=("{~" + c.language + "~}");
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
		return false;
	}

	@Override
	public boolean deleteLibraryFromSystem(Library library) {
		return false;
	}

	@Override
	public boolean deleteNotebookFromSystem(Notebook notebook) {
		return false;
	}

	@Override
	public boolean deleteNoteFromSystem(Note note) {
		return false;
	}

	@Override
	public void setActiveLibrary(Library library) {
		super.setActiveLibrary(library);
		if (library == null) return;
		for (String key : list.getNodeKeys()) {
			if (list.getNodes().get(key).UUID.equals(library.getPath() + File.separator + library.getName())) {
				System.out.println("ACTIVE: " + library.getName());
			}
		}
	}

	@Override
	public void setActiveNotebook(Notebook notebook) {
		super.setActiveNotebook(notebook);
		if (notebook == null) return;
		for (String key : list.getNodeKeys()) {
			if (list.getNodes().get(key).UUID.equals(notebook.getUUID())) {
				list.onClick((BasicListNode)list.getNodes().get(key));
				editArea.setText("");
				updatePreview("", "");
				
				for (Note n : notebook.getNoteAsArray()) {
					addNoteToList(n);
				}
				
			}
		}
	}

	@Override
	public void setActiveNote(Note note) {
		super.setActiveNote(note);
		if (note == null) return;
		// go through notes list and run active notes click method
		for (String key : list.getNodeKeys()) {
			if (list.getNodes().get(key).UUID.equals(note.getUUID())) {
				list.onClick((BasicListNode)list.getNodes().get(key));
				
				String text = "";
				for (Cell c : note.getCells()) {
					text+=("[~" + c.type + "~]");
					text+="\n";
					if (c.type.equals(CellType.CODE.type)) text+=("{~" + c.language + "~}");
					text+="\n";
					text+=c.data;
				}
				setEditText(text);
				txtNoteTitle.setText(note.getTitle());
				updatePreview(note.getTitle(), text);

			}
		}
	}

	@Override
	public void updatePreview(String title, Cell[] cells) {

	}

	@Override
	public void updatePreview(String title, String notes) {

	}

}
