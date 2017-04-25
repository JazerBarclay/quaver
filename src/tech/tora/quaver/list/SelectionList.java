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

	// Grey colour fill
	private int fillCol = 230, borderCol = 140;
	
	private Color borderColour = new Color(borderCol, borderCol, borderCol);
	private Color fillColour = new Color(fillCol, fillCol, fillCol);
	
	public SelectionList(String sectionTitle) {
		
		setLayout(new BorderLayout());
		
		containerPane = new JPanel();
		containerPane.setBackground(fillColour);
		containerPane.setLayout(new BoxLayout(containerPane, BoxLayout.Y_AXIS));

		scrollPane = new JScrollPane(containerPane);
//		scrollPane = new JScrollPane(containerPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, borderColour));
		
		this.add(scrollPane);
				
	}
	
	public SelectionList() {
		this("");
	}
	
	public void addNode(SelectionListNode node) {
		containerPane.add(node);
	}
	
	public void setColours(int fillColour, int borderColour) {
		Color fColour = new Color(fillColour, fillColour, fillColour);
		Color bColour = new Color(borderColour, borderColour, borderColour);

		containerPane.setBackground(fColour);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, bColour));
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
