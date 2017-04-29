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
	
	protected String title;
	protected Color fontColor;
	protected Color fillColor;
	protected Color hoverColor;
	protected Font titleFont, label1Font, label2Font;
	
	protected QuickListNode selected = null;
	
	public QuickList(String sectionTitle, Color fontColor, Color fillColor, Color hoverColor, Font titleFont, Font label1Font, Font label2Font) {
		
		this.title = sectionTitle;
		this.fontColor = fontColor;
		this.fillColor = fillColor;
		this.hoverColor = hoverColor;
		this.titleFont = titleFont;
		this.label1Font = label1Font;
		this.label2Font = label2Font;
		
		setLayout(new BorderLayout());
//		setBackground(fillColor);
//		setOpaque(false);
		
		containerPane = new JPanel();
		containerPane.setLayout(new BoxLayout(containerPane, BoxLayout.Y_AXIS));
		containerPane.setBackground(fillColor);

		scrollPane = new JScrollPane(containerPane);
//		scrollPane = new JScrollPane(containerPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		this.add(scrollPane);
		
		int width=200, height=30;
		
		JLabel label1 = new JLabel(sectionTitle);
		label1.setFont(titleFont);
		label1.setForeground(fontColor);
		
		JPanel titleSectionPadded = new JPanel();
		titleSectionPadded.setLayout(new BorderLayout());
		titleSectionPadded.setBorder(BorderFactory.createEmptyBorder(10, 5, 8, 5));
		titleSectionPadded.setSize(width, height);
		titleSectionPadded.setPreferredSize(new Dimension(width, height));
		titleSectionPadded.setMinimumSize(new Dimension(width, height));
		titleSectionPadded.setMaximumSize(new Dimension(width, height));
		titleSectionPadded.setOpaque(false);
		titleSectionPadded.add(label1, BorderLayout.NORTH);
		JPanel titleSection = new JPanel();
		titleSection.setLayout(new BorderLayout());
		titleSection.setSize(width, height);
		titleSection.setPreferredSize(new Dimension(width, height));
		titleSection.setMinimumSize(new Dimension(width, height));
		titleSection.setMaximumSize(new Dimension(width, height));
		titleSection.setOpaque(false);
		titleSection.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(200, 200, 200)));
		titleSection.add(titleSectionPadded);
		
		containerPane.add(titleSection);
		
	}
	
	public QuickList(Color fontColor, Color fillColor, Color hoverColor,Font titleFont, Font label1Font, Font label2Font) {
		this("", fontColor, fillColor, hoverColor, titleFont, label1Font, label2Font);
	}
	
	public void addNode(QuickListNode node) {
		containerPane.add(node);
	}

	public void setFillColor(Color fillColor) {
		containerPane.setBackground(fillColor);
	}
	
	public void setHoverColor(Color fillColor) {
		containerPane.setBackground(fillColor);
	}
	
}
