package tech.tora.quaver.notepad.widget;

import tech.tora.quaver.list.QuickList;
import tech.tora.quaver.list.QuickListNode;
import tech.tora.quaver.types.Notebook;

public abstract class NotebookNode extends QuickListNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Notebook notebook;
	
	public NotebookNode(QuickList parent, String image, String title, String rightSide, Notebook notebook) {
		super(parent, image, title, rightSide);
		this.notebook = notebook;
	}

}
