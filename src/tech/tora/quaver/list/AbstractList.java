package tech.tora.quaver.list;

import javax.swing.JPanel;

public abstract class AbstractList extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ListData data;
	private int nodeWidth;
	
	public AbstractList(int width) {
		this.nodeWidth = width;
	}
	
	protected void setupDataList() {
		data = new ListData() {
			@Override
			public void manageDuplicateNode(ListDataNode original, ListDataNode newNode) {
//				// Do Nothing for now
//				if (true) renameAndSaveDuplicate(original, original.UUID + "_new", original.title = "_new");
//				else overwriteDuplicate(original, newNode);
			}
		};
	}
	
	public void addNode(AbstractListNode node) {
		
		data.addNode(node.getData());
	}
	
	public void setNodeWidth(int nodeWidth) {
		this.nodeWidth = nodeWidth;
		redraw();
	}
	
	public int getNodeWidth() {
		return nodeWidth;
	}
	
	// Revalidate all child nodes
	public void redraw() {
		this.revalidate();
	}
	
}
