package tech.tora.quaver.notepad.screen;

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
	
	public PreviewScreen(Theme theme, String projectName, int release, int major, int minor) {
		super(theme, projectName, release, major, minor);
	}

	@Override
	public void addLibraryToList(Library library) {

	}

	@Override
	public void addNotebookToList(Notebook notebook) {

	}

	@Override
	public void addNoteToList(Note note) {

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
		updatePreview("", note.getCells());
	}

	// Edits
	
	@Override
	public void setEditText(String text) {
		
	}

	@Override
	public String getEditText() {
		return null;
	}

	@Override
	public void clearEditText() {
		
	}

	@Override
	public void updatePreview(String title, Cell[] cells) {
		String text = "";
		for (Cell c : cells) {
			text += ("[~" + c.type + "~]" + "\n");
			text += c.data;
			text += "\n";
		}
		updatePreview("", text);
	}

	@Override
	public void updatePreview(String title, String notes) {
		if (getActiveNote() != null) title = getActiveNote().getTitle();
		String text = "<html>"
				+ "<head><title>" + "Quaver" + "</title></head>"
				+ "<body style=\"background-color: #393F4B; color: #f2f2f2; font: helvetica; padding: 20px;\">" 
				+ "<h1>" + title + "</h1>"
				+ "<hr><br/>"
				+ notes
				+ "</body></html>";
		
		previewArea.setText(text);
	}
	
}
