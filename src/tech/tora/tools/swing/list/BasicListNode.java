package tech.tora.tools.swing.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tech.tora.tools.swing.colour.ColourValue;

public abstract class BasicListNode extends AbstractListNode {

	private int height;
	
	public JPanel wrapper = new JPanel();
	
	public boolean active = false;
	
	protected Font titleFont;
	protected ColourValue titleColour;
	protected ColourValue fill, hover;
	protected int clickMod;
	
	public BasicListNode(int height, String uuid, String title, Color fillColour, Color hoverColour, Font titleFont, Color titleColour, int clickModifier) {
		super(uuid, title);
		this.height = height;
		this.fill = new ColourValue(fillColour.getRed(), fillColour.getGreen(), fillColour.getBlue());
		this.hover = new ColourValue(hoverColour.getRed(), hoverColour.getGreen(), hoverColour.getBlue());
		this.titleFont = titleFont;
		this.titleColour = new ColourValue(titleColour.getRed(), titleColour.getGreen(), titleColour.getBlue());
		this.clickMod = clickModifier;
	}

	
	@Override
	public JPanel generateNode() {
		return standardGenerate();
	}
	
	protected JPanel standardGenerate() {
		JLabel title = new JLabel(this.title);
		title.setFont(titleFont);
		title.setForeground(titleColour.getAsColor());
		
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BorderLayout());
		wrapper.setSize(new Dimension(getParentList().width, height));
		wrapper.setPreferredSize(new Dimension(getParentList().width, height));
		wrapper.setMinimumSize(new Dimension(getParentList().width, height));
		wrapper.setMaximumSize(new Dimension(getParentList().width, height));
		wrapper.setBackground(fill.getAsColor());
		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 10));
		content.setSize(getParentList().width, height);
		content.setPreferredSize(new Dimension(getParentList().width, height));
		content.setMinimumSize(new Dimension(getParentList().width, height));
		content.setMaximumSize(new Dimension(getParentList().width, height));
		content.setOpaque(false);
		content.add(title, BorderLayout.CENTER);
		
		wrapper.add(content);
		
		return wrapper;
	}
	
	public void setFillColour(Color c) {
		wrapper.setBackground(c);
	}

}
