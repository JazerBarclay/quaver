package tech.tora.tools.swing.list;

import javax.swing.JPanel;

public abstract class AbstractListNode {
	
	public String UUID;
	public String title;
	
	private AbstractListNode parentNode;
	private AbstractList parentList;
	
	public JPanel nodeLook = null;
	
	public abstract JPanel generateNode();
	
	public AbstractListNode(String uuid, String title) {
		this.UUID = uuid;
		this.title = title;
	}
	
	protected void addNode(AbstractList list) {
		list.addNode(this);
	}
	
	protected void removeNode() {
		parentList.removeNode(this.UUID);
	}
	
	/** Set the parent - Only for DataList **/
	void setParentList(AbstractList parent) {
		this.parentList = parent;
	}
	
	public AbstractList getParentList() {
		return parentList;
	}
	
	public void setParentNode(AbstractListNode parent) {
		this.parentNode = parent;
	}
	
	public AbstractListNode getParentNode() {
		return parentNode;
	}
	
}
