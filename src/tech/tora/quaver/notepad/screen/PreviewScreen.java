package tech.tora.quaver.notepad.screen;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import tech.tora.quaver.Configuration;
import tech.tora.quaver.notepad.layout.PreviewLayout;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Cell;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;

public class PreviewScreen extends PreviewLayout {

	private Library activeLibrary = null;
	private Notebook activeNotebook = null;
	private Note activeNote = null;
	
	public PreviewScreen(Configuration config, Theme theme) {
		super(theme);
	}

	@Override
	public void addLibrary(Library library) {
		
	}

	@Override
	public void addNotebook(Notebook notebook) {
		
	}

	@Override
	public void addNote(Note note) {
		
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
	public void setActiveLibrary(Library library) {
		this.activeLibrary = library;
	}

	@Override
	public Notebook getActiveNotebook() {
		return activeNotebook;
	}

	@Override
	public void setActiveNotebook(Notebook notebook) {
		this.activeNotebook = notebook;
	}

	@Override
	public Note getActiveNote() {
		return activeNote;
	}

	@Override
	public void setActiveNote(Note note) {
		this.activeNote = note;
	}

	@Override
	public void setEditText(String text) {
		
	}

	@Override
	public String getEditText() {
		return "";
	}

	@Override
	public void clearEditText() {
		
	}

	@Override
	public void updatePreview(Cell[] cells) {
		
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

}
