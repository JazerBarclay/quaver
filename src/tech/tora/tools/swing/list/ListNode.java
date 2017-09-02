package tech.tora.tools.swing.list;

public abstract class ListNode {

	private String UUID;
	private String title;
	
	public ListNode(String uuid, String title) {
		this.UUID = uuid;
		this.title = title;
	}
	
	public void setUUID(String uuid) {
		this.UUID = uuid;
	}
	
	public String getUUID() {
		return UUID;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
}
