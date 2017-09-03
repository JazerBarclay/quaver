package tech.tora.tools.swing.list;

import javax.swing.JPanel;

public abstract class AbstractListNode extends ListNode {
	
	private AbstractListNode parentNode;
	private AbstractList parentList;
	
	public abstract JPanel generateNode();
	
	public AbstractListNode(String uuid, String title) {
		super(uuid, title);
	}
	
	protected void addNode(AbstractList list) {
		list.addNode(this);
	}
	
	protected void removeNode() {
		parentList.removeNode(this.getUUID());
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
