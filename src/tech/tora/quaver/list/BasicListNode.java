package tech.tora.quaver.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class BasicListNode extends AbstractListNode {

	private int height;
	
	protected JPanel wrapper, content;
	
	public Font titleFont;
	public Color titleColour;
	public Color fill, hover;
	
	public BasicListNode(int height, String uuid, String title, Color fillColour, Color hoverColour, Font titleFont, Color titleColour) {
		super(uuid, title);
		this.height = height;
		this.fill = fillColour;
		this.hover = hoverColour;
		this.titleFont = titleFont;
		this.titleColour = titleColour;
	}

	
	@Override
	public JPanel generateNode() {
		standardGenerate();
		return wrapper;
	}
	
	protected JPanel standardGenerate() {
		JLabel title = new JLabel(this.title);
		title.setFont(titleFont);
		title.setForeground(titleColour);
		
		wrapper = new JPanel();
		wrapper.setLayout(new BorderLayout());
		wrapper.setSize(new Dimension(getParentList().width, height));
		wrapper.setPreferredSize(new Dimension(getParentList().width, height));
		wrapper.setMinimumSize(new Dimension(getParentList().width, height));
		wrapper.setMaximumSize(new Dimension(getParentList().width, height));
		wrapper.setBackground(fill);
		wrapper.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(200, 200, 200)));
		
		content = new JPanel();
		content.setLayout(new BorderLayout());
		content.setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 10));
		content.setSize(getParentList().width, height);
		content.setPreferredSize(new Dimension(getParentList().width, height));
		content.setMinimumSize(new Dimension(getParentList().width, height));
		content.setMaximumSize(new Dimension(getParentList().width, height));
		content.setBackground(fill);
		content.add(title, BorderLayout.CENTER);
		
		wrapper.add(content);
		
		return wrapper;
	}

}
