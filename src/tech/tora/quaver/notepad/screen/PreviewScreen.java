package tech.tora.quaver.notepad.screen;

import tech.tora.quaver.notepad.layout.PreviewLayout;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Cell;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.list.AbstractListNode;

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
	public void updateLibrary(Library library) {
		
	}

	@Override
	public void updateNotebook(Notebook notebook) {
		
	}

	@Override
	public void updateNote(Note note) {
		
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
		if (note != null) updatePreview(note);
		else updatePreview("", "");
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
	public void updatePreview(Note note) {
		String text = "";
		for (Cell c : note.getCells()) {
			text += ("[~" + c.type + "~]" + "\n");
			text += c.data;
			text += "\n";
		}
		updatePreview("", text);
	}

	@Override
	public void updatePreview(String title, String notes) {
		String compiled = "";
		for (String text : notes.split("\n")) {
			compiled += text;
			compiled += "<br>";
		}
		String text = "<html>"
				+ "<head><title>" + "Quaver" + "</title></head>"
				+ "<body style=\"background-color: #393F4B; color: #f2f2f2; font: helvetica; padding: 20px; word-wrap: break-word;\">" 
				+ "<h1>" + title + "</h1>"
				+ "<hr><br/>"
				+ compiled
				+ "</body></html>";
		
		previewArea.setText(text);
	}

	@Override
	public String getEditTitle() {
		return "";
	}

	@Override
	public AbstractListNode getLibraryNodeFromList() {
		return null;
	}

	@Override
	public AbstractListNode getNotebooNodeFromList() {
		return null;
	}

	@Override
	public AbstractListNode getNoteNodeFromList() {
		return null;
	}
	
}
