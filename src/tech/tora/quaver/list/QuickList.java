package tech.tora.quaver.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class QuickList extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel containerPane;
	private JScrollPane scrollPane;

	public QuickList(String sectionTitle) {
		
		setLayout(new BorderLayout());
		
		containerPane = new JPanel();
		containerPane.setBackground(new Color(230, 230, 230));
		containerPane.setLayout(new BoxLayout(containerPane, BoxLayout.Y_AXIS));

		scrollPane = new JScrollPane(containerPane);
//		scrollPane = new JScrollPane(containerPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		this.add(scrollPane);


		
		int width=200, height=30;
		
		JLabel label1 = new JLabel(sectionTitle);
		label1.setFont(new Font("Helvetica", Font.BOLD, 14));
		label1.setForeground(new Color(40, 40, 40));
		
		JPanel titleSectionPadded = new JPanel();
		titleSectionPadded.setLayout(new BorderLayout());
		titleSectionPadded.setBorder(BorderFactory.createEmptyBorder(10, 5, 8, 5));
		titleSectionPadded.setSize(width, height);
		titleSectionPadded.setPreferredSize(new Dimension(width, height));
		titleSectionPadded.setMinimumSize(new Dimension(width, height));
		titleSectionPadded.setMaximumSize(new Dimension(width, height));
		titleSectionPadded.setBackground(new Color(230, 230, 230));
		titleSectionPadded.add(label1, BorderLayout.NORTH);
		JPanel titleSection = new JPanel();
		titleSection.setLayout(new BorderLayout());
		titleSection.setSize(width, height);
		titleSection.setPreferredSize(new Dimension(width, height));
		titleSection.setMinimumSize(new Dimension(width, height));
		titleSection.setMaximumSize(new Dimension(width, height));
		titleSection.setBackground(new Color(230, 230, 230));
		titleSection.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(200, 200, 200)));
		titleSection.add(titleSectionPadded);
		
		containerPane.add(titleSection);
		
	}
	
	public QuickList() {
		this("");
	}
	
	public void addNode(QuickListNode node) {
		containerPane.add(node);
	}
	
}
