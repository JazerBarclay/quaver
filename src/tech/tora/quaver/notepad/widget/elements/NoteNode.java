package tech.tora.quaver.notepad.widget.elements;

import tech.tora.quaver.listold.SelectionList;
import tech.tora.quaver.listold.SelectionListNode;
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
