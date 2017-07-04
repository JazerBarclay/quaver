package tech.tora.quaver.list;

import java.util.LinkedHashMap;

public abstract class ListData {

	private LinkedHashMap<String, ListDataNode> nodes = new LinkedHashMap<>();
	
	public ListData() {
		
	}
	
	public abstract void manageDuplicateNode(ListDataNode original, ListDataNode newNode);
	
	protected void addNode(ListDataNode node) {
		ListDataNode dupe = null;
		for (String key : nodes.keySet()) {
			if (nodes.get(key).UUID.equals(node.UUID)) {
				dupe = nodes.get(key);
			}
		}
		if (dupe != null) {
			System.err.println("Duplicate Found");
			manageDuplicateNode(dupe, node);
		} else {
			node.setParentList(this);
			nodes.put(node.UUID, node);
		}
	}
	
	protected void removeNode(ListDataNode node) {
		removeNode(node.UUID);
	}
	
	protected void removeNode(String key) {
		nodes.remove(key);
	}
	
	protected void renameAndSaveDuplicate(ListDataNode newNode, String newuuid, String newTitle) {
		newNode.UUID = newuuid;
		newNode.title = newTitle;
		addNode(newNode);
	}
	
	protected void overwriteDuplicate(ListDataNode originalNode, ListDataNode newNode) {
		nodes.replace(originalNode.UUID, newNode);
	}
	
}
