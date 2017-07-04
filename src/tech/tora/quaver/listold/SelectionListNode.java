package tech.tora.quaver.listold;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class SelectionListNode extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SelectionList parentList;
	
	private int width = 300;
	private int height = 58;

	private boolean active = false;
	
	private JPanel node1padded;
	
//	private int borderCol = 200;
//	private int fillCol = 230;
//	private int hoverFillCol = 210;
//
//	private Color borderColour = new Color(borderCol, borderCol, borderCol);
//	private Color fillColour = new Color(fillCol, fillCol, fillCol);
//	private Color hoverFillColour = new Color(hoverFillCol, hoverFillCol, hoverFillCol);
	
	public SelectionListNode(SelectionList parent, String title, String subtitle) {
		this.parentList = parent;
		setLayout(new BorderLayout());
		setSize(width, height);
		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setBackground(parent.fillColour);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 210, 210)));
		
		JLabel label1 = new JLabel(title);
		label1.setFont(new Font("Helvetica", Font.BOLD, 14));
		label1.setForeground(parent.fontColor);
		
		JLabel label2 = new JLabel(subtitle);
		label2.setFont(new Font("Helvetica", Font.PLAIN, 10));
		label2.setForeground(new Color(parent.fontColor.getRed(), parent.fontColor.getBlue(), parent.fontColor.getGreen()));
		
		node1padded = new JPanel();
		node1padded.setLayout(new BorderLayout());
		node1padded.setBorder(BorderFactory.createEmptyBorder(10, 5, 8, 5));
		node1padded.setSize(width, height);
		node1padded.setPreferredSize(new Dimension(width, height));
		node1padded.setMinimumSize(new Dimension(width, height));
		node1padded.setMaximumSize(new Dimension(width, height));
		node1padded.setBackground(parent.fillColour);
		node1padded.add(label1, BorderLayout.NORTH);
		node1padded.add(label2, BorderLayout.SOUTH);
		
		add(node1padded);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				onClick();
//				parent.setColours(230, 140);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
//				parent.setColours(100, 100);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (!active) {
					node1padded.setBackground(parent.fillColour);
					setBackground(parent.fillColour);
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
	
	public abstract void onClick();
	
	public void setActive(boolean active) {
		this.active = active;
		// TODO - URGENT!!! add check for colors and only add if within limits!!!
		int mod = 10;
		if (active){
			setBackground(new Color(
					parentList.fillColour.getRed()-mod, 
					parentList.fillColour.getBlue()-mod, 
					parentList.fillColour.getGreen()-mod));
			node1padded.setBackground(new Color(
					parentList.fillColour.getRed()-mod, 
					parentList.fillColour.getBlue()-mod, 
					parentList.fillColour.getGreen()-mod));
		} else {
			node1padded.setBackground(parentList.fillColour);
			setBackground(parentList.fillColour);
		}
	}
	
}
