package tech.tora.quaver.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class QuickListNode extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private QuickList parentList;
	
	private int width = 200;
	private int height = 25;
	
	private boolean active = false;
	
	private JPanel node1padded;
	
	public QuickListNode(QuickList parent, String image, String title, String rightSide) {
		this.parentList = parent;
		setLayout(new BorderLayout());
		setSize(width, height);
		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setBackground(new Color(230, 230, 230));
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(200, 200, 200)));
		
		JLabel label1 = new JLabel("  " + title);
		label1.setFont(parent.label1Font);
		label1.setForeground(parent.fontColor);

		JLabel label2 = new JLabel(rightSide);
		label2.setFont(parent.label2Font);
		label2.setForeground(parent.fontColor);
		
		node1padded = new JPanel();
		node1padded.setLayout(new BorderLayout());
		node1padded.setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 10));
		node1padded.setSize(width, height);
		node1padded.setPreferredSize(new Dimension(width, height));
		node1padded.setMinimumSize(new Dimension(width, height));
		node1padded.setMaximumSize(new Dimension(width, height));
		node1padded.setBackground(parent.fillColor);
		node1padded.add(label1, BorderLayout.CENTER);
		node1padded.add(label2, BorderLayout.EAST);
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("res/icon1.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			node1padded.add(picLabel, BorderLayout.WEST);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		add(node1padded);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseClick();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (!active) {
					node1padded.setBackground(parent.fillColor);
					setBackground(parent.fillColor);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if (!active) {
					node1padded.setBackground(parent.hoverColor);
					setBackground(parent.hoverColor);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
	}
	
	public abstract void mouseClick();
	
	public void setActive(boolean active) {
		this.active = active;
		// TODO - URGENT!!! add check for colors and only add if within limits!!!
		int mod = 20;
		if (active){
			setBackground(new Color(
					parentList.fillColor.getRed()-mod, 
					parentList.fillColor.getBlue()-mod, 
					parentList.fillColor.getGreen()-mod));
			node1padded.setBackground(new Color(
					parentList.fillColor.getRed()-mod, 
					parentList.fillColor.getBlue()-mod, 
					parentList.fillColor.getGreen()-mod));
		} else {
			node1padded.setBackground(parentList.fillColor);
			setBackground(parentList.fillColor);
		}
	}
	
}
