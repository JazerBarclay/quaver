package tech.tora.quaver.list;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class SelectionList extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel containerPane;
	private JScrollPane scrollPane;
	
	public SelectionList(String sectionTitle) {
		
		setLayout(new BorderLayout());
		
		containerPane = new JPanel();
		containerPane.setBackground(new Color(230, 230, 230));
		containerPane.setLayout(new BoxLayout(containerPane, BoxLayout.Y_AXIS));

		scrollPane = new JScrollPane(containerPane);
//		scrollPane = new JScrollPane(containerPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(140, 140, 140)));
		
		this.add(scrollPane);
		
//		containerPane.add(new ListNode(this, "Javascript to JSON Test", "10 Jan, 2017"));
		
	}
	
	public SelectionList() {
		this("");
	}
	
	public void addNode(SelectionListNode node) {
		containerPane.add(node);
	}
//	
//	private void generateUI() {
//		containerPane = new JPanel();
//		containerPane.setLayout(new BoxLayout(containerPane, BoxLayout.Y_AXIS));
//		
//		scrollPane = new JScrollPane(containerPane);
//	}
//	
//	public void clearList() {
//		containerPane = new JPanel();
//	}
	
}
