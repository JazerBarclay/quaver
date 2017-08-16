package tech.tora.quaver.notepad.layout;

import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.frame.AdvancedFrame;
import tech.tora.tools.swing.list.ClickListener;

public abstract class CompactLayout extends CompactLayoutTemplate {

	public CompactLayout(AdvancedFrame parent, Theme theme) {
		super(parent, theme);
	}

	@Override
	public void buildElements(Theme theme) {
		
	}

	@Override
	public void constructElements() {
		
	}
	
	@Override
	public void addLibraryNodeToList(Library lib) {
		// Do Nothing (for now)
	}
	
	@Override
	public void addNotebookNodeToList(Notebook notebook, ClickListener clickEvent) {
		// Do Nothing (for now)
	}
	
	@Override
	public void addNoteNodeToList(Note noteb) {
		// Do Nothing (for now)
	}
	
}
