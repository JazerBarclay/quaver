package tech.tora.quaver.notepad.widget.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tech.tora.quaver.Launcher;
import tech.tora.tools.swing.list.BasicClickListNode;

public abstract class NotebookListNode extends BasicClickListNode {
	
	private static int height = 25;
	
	private String iconPath, count;
	
	public NotebookListNode(String iconPath, String uuid, String title, String count, 
			Color fillColour, Color hoverColour, 
			Font titleFont, Color titleColour, 
			int clickModifier) {
		super(height, uuid, title, fillColour, hoverColour, titleFont, titleColour, clickModifier);
		
		this.iconPath = iconPath;
		this.count = count;
		
	}

	@Override
	protected JPanel standardGenerate() {
		
		JLabel label1 = new JLabel(getTitle());
		label1.setFont(titleFont);
		label1.setForeground(titleColour.getAsColor());

		JLabel label2 = new JLabel(count);
		label2.setFont(titleFont);
		label2.setForeground(titleColour.getAsColor());
		
		wrapper = new JPanel();
		wrapper.setLayout(new BorderLayout());
		wrapper.setSize(getParentList().width, height);
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
		content.add(label1, BorderLayout.CENTER);
		content.add(label2, BorderLayout.EAST);
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(Launcher.class.getResourceAsStream(iconPath));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			content.add(picLabel, BorderLayout.WEST);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		wrapper.add(content);
		
		return wrapper;
	}

}
