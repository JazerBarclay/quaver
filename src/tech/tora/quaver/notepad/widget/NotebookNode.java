package tech.tora.quaver.notepad.widget;

import tech.tora.quaver.list.QuickList;
import tech.tora.quaver.list.QuickListNode;
import tech.tora.quaver.structures.NotebookMeta;

public class NotebookNode extends QuickListNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotebookMeta notebook;
	
	public NotebookNode(QuickList parent, String image, String title, String rightSide, NotebookMeta notebook) {
		super(parent, image, title, rightSide);
		this.notebook = notebook;
	}

}
