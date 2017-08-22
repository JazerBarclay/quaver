package tech.tora.quaver.notepad.widget.list;

import java.awt.Color;
import java.awt.Font;

import tech.tora.tools.swing.list.BasicList;
import tech.tora.tools.swing.list.ClickListener;

public class NotebookList extends BasicList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int fontSize = 12;

	protected Color backCol, nodeFillCol, nodeHoverCol, nodeFontCol;
	protected String font;

	public NotebookList(int width, String font, Color backgroundColour, Color nodeFillColour, Color nodeHoverColour, Color fontColour) {
		super(width);
		this.backCol = backgroundColour;
		this.nodeFillCol = nodeFillColour;
		this.nodeHoverCol = nodeHoverColour;
		this.font = font;
		this.nodeFontCol = fontColour;
	}

	public void addNotebookNode(String iconPath, String uuid, String title, String count, ClickListener clickEvent) {
		NotebookListNode node = new NotebookListNode("/icon1.png", uuid, title, count,
				nodeFillCol, nodeHoverCol,
				new Font(font, Font.BOLD, fontSize), nodeFontCol, -20) {

			@Override
			public void onClick() {
				NotebookList.this.onClick(this);
				clickEvent.onClick();
			}
		};
		addNode(node);
	}

}
