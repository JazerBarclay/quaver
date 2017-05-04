package tech.tora.quaver.notepad.widget;

import tech.tora.quaver.list.SelectionList;
import tech.tora.quaver.list.SelectionListNode;
import tech.tora.quaver.types.Note;

public abstract class NoteNode extends SelectionListNode {

	public Note note;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoteNode(SelectionList parent, String title, String subtitle, Note note) {
		super(parent, title, subtitle);
		this.note = note;
	}

}
