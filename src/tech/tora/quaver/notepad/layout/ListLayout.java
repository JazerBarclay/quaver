package tech.tora.quaver.notepad.layout;

import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.list.ClickListener;

/**
 * Manages a layout that displays a menu that takes a data set
 * @author Nythril
 */
public abstract class ListLayout extends CustomLayout {
	
	public ListLayout(Theme theme) {
		super(theme);
	}

	public abstract void addLibraryNodeToList(Library library);
	public abstract void addNotebookNodeToList(Notebook notebook, ClickListener clickEvent);
	public abstract void addNoteNodeToList(Note note);
	
}
