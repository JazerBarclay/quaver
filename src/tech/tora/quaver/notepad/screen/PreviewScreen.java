package tech.tora.quaver.notepad.screen;

import tech.tora.quaver.notepad.layout.PreviewLayout;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Cell;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;

public class PreviewScreen extends PreviewLayout {

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
		return null;
	}

	@Override
	public void setActiveLibrary(Library library) {
		
	}

	@Override
	public Notebook getActiveNotebook() {
		return null;
	}

	@Override
	public void setActiveNotebook(Notebook notebook) {

	}

	@Override
	public Note getActiveNote() {
		return null;
	}

	@Override
	public void setActiveNote(Note note) {
		
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
	public void updatePreview(Cell[] cells) {
		
	}

	@Override
	public void updatePreview(String notes) {
		
	}
	
}
