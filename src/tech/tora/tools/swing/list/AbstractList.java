package tech.tora.tools.swing.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public abstract class AbstractList extends JPanel {

	/***/
	private static final long serialVersionUID = 1L;
	
	public int width;
	protected LinkedHashMap<String, AbstractListNode> nodes = new LinkedHashMap<>();
	
	private JPanel containerPane = null;
	private JScrollPane scrollPane;
	
	public abstract boolean manageDuplicateNode(AbstractListNode original, AbstractListNode newNode);
	
	public AbstractList(int width) {
		this.width = width;
		generateLayout();
	}
	
	public void generateLayout() {
		setLayout(new BorderLayout());
		setOpaque(false);
		
		containerPane = new JPanel();
		containerPane.setLayout(new BoxLayout(containerPane, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane(containerPane);
//		scrollPane = new JScrollPane(containerPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		this.add(scrollPane);
	}
	
	private void buildNodes() {
		containerPane.removeAll();
		for (String k : nodes.keySet()) {
			containerPane.add(nodes.get(k).generateNode());
		}
		containerPane.revalidate();
		containerPane.repaint();
	}

	public void addNode(AbstractListNode node) {
		addNodeToList(node);
		buildNodes();
	}
	
	private void addNodeToList(AbstractListNode node) {
		AbstractListNode dupe = null;
		for (String key : nodes.keySet()) {
			if (nodes.get(key).getUUID().equals(node.getUUID())) {
				dupe = nodes.get(key);
			}
		}
		if (dupe != null) {
			System.err.println("Duplicate Found");
			manageDuplicateNode(dupe, node);
		} else {
			node.setParentList(this);
			nodes.put(node.getUUID(), node);
		}
	}

	protected void removeNode(AbstractListNode node) {
		removeNode(node.getUUID());
	}

	protected void removeNode(String key) {
		nodes.remove(key);
		buildNodes();
	}

	protected boolean renameAndSaveDuplicate(AbstractListNode newNode, String newuuid, String newTitle) {
		newNode.setUUID(newuuid);
		newNode.setTitle(newTitle);
		addNode(newNode);
		return true;
	}

	protected boolean overwriteDuplicate(AbstractListNode originalNode, AbstractListNode newNode) {
		nodes.replace(originalNode.getUUID(), newNode);
		return true;
	}
	
	public LinkedHashMap<String, AbstractListNode> getNodes() {
		return nodes;
	}
	
	public Set<String> getNodeKeys() {
		return nodes.keySet();
	}

	@Override
	public void setBackground(Color c) {
		if (containerPane != null) containerPane.setBackground(c);
	}
	
	public void clear() {
		nodes = new LinkedHashMap<>();
		buildNodes();
	}

}
