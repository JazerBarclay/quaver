package tech.tora.quaver.notepad.layout;

import javax.swing.JLabel;

import tech.tora.quaver.notepad.widget.elements.AddButton;
import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.frame.AdvancedFrame;
import tech.tora.tools.swing.list.BasicList;

public abstract class StandardLayout extends StandardLayoutTemplate {
	
	public JLabel notebookTitle;

	public BasicList notebooksList;
	public BasicList notesList;
	
	public AddButton btnAddNotebook;
	public AddButton btnAddNote;
	
	public StandardLayout(AdvancedFrame parent, Theme theme) {
		super(parent, theme);
	}

	@Override
	public void buildElements() {
		
	}

	@Override
	public void constructElements() {
		
	}

}
