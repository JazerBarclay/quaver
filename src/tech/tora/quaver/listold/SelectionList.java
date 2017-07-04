package tech.tora.quaver.listold;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

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

	public SelectionListNode[] nodes = new SelectionListNode[] {};
	protected SelectionListNode selected = null;
	
	// Grey colour fill

	protected Color fontColor, fillColour, hoverColor, borderColour;
	protected Font label1Font, label2Font;
	
	
	public SelectionList(String sectionTitle, Color fontColor, Color fillColor, Color hoverColor, Color borderColor, Font label1Font, Font label2Font) {
		
		this.fontColor = fontColor;
		this.fillColour = fillColor;
		this.hoverColor = hoverColor;
		this.borderColour = borderColor;
		
		this.label1Font = label1Font;
		this.label2Font = label2Font;
		
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
	
	public SelectionList(Color fontColor, Color fillColor, Color hoverColor, Color borderColor, Font label1Font, Font label2Font) {
		this("", fontColor, fillColor, hoverColor, borderColor, label1Font, label2Font);
	}
	
	public void addNode(SelectionListNode node) {
		containerPane.add(node);
		SelectionListNode[] tmp = new SelectionListNode[nodes.length+1];
		for (int i = 0; i < nodes.length; i++) tmp[i] = nodes[i];
		tmp[nodes.length] = node;
		nodes = tmp;
	}
	
	public void setColours(int fillColour, int borderColour) {
		Color fColour = new Color(fillColour, fillColour, fillColour);
		Color bColour = new Color(borderColour, borderColour, borderColour);

		containerPane.setBackground(fColour);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, bColour));
	}
	
	public void setActiveNode(SelectionListNode n) {
		for (SelectionListNode node : nodes) {
			if (node == n) node.setActive(true);
			else node.setActive(false);
		}
		selected = n;
	}
	
	
//	
//	private void generateUI() {
//		containerPane = new JPanel();
//		containerPane.setLayout(new BoxLayout(containerPane, BoxLayout.Y_AXIS));
//		
//		scrollPane = new JScrollPane(containerPane);
//	}
//	
	public void clearList() {
		nodes = new SelectionListNode[] {};
		containerPane.removeAll();
		containerPane.revalidate();
		containerPane.repaint();
		
	}
	
}
