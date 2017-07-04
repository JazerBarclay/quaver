package tech.tora.quaver.list;


public class ListDataNode {

	public String UUID;
	public String title;
	
	private ListDataNode parentNode;
	private ListData parentList;
	
	public ListDataNode(String uuid, String title) {
		this.UUID = uuid;
		this.title = title;
	}
	
	protected void addNode(ListData list) {
		list.addNode(this);
	}
	
	protected void removeNode() {
		parentList.removeNode(this.UUID);
	}
	
	/** Set the parent - Only for DataList **/
	void setParentList(ListData parent) {
		this.parentList = parent;
	}
	
	public ListData getParentList() {
		return parentList;
	}
	
	public void setParentNode(ListDataNode parent) {
		this.parentNode = parent;
	}
	
	public ListDataNode getParentNode() {
		return parentNode;
	}
	
}
