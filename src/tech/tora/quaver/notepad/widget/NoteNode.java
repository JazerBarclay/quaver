package tech.tora.quaver.notepad.widget;

import tech.tora.quaver.list.SelectionList;
import tech.tora.quaver.list.SelectionListNode;
import tech.tora.quaver.structures.NoteMeta;

public class NoteNode extends SelectionListNode {

	public NoteMeta note;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoteNode(SelectionList parent, String title, String subtitle, NoteMeta note) {
		super(parent, title, subtitle);
		this.note = note;
	}

}
