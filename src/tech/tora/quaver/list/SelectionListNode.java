package tech.tora.quaver.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectionListNode extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private SelectionList parentList;
	
	private int width = 300;
	private int height = 58;
	
	private int borderCol = 200;
	private int fillCol = 230;
	private int hoverFillCol = 210;

	private Color borderColour = new Color(borderCol, borderCol, borderCol);
	private Color fillColour = new Color(fillCol, fillCol, fillCol);
	private Color hoverFillColour = new Color(hoverFillCol, hoverFillCol, hoverFillCol);
	
	public SelectionListNode(SelectionList parent, String title, String subtitle) {
		this.parentList = parent;
		setLayout(new BorderLayout());
		setSize(width, height);
		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setBackground(fillColour);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColour));
		
		JLabel label1 = new JLabel(title);
		label1.setFont(new Font("Helvetica", Font.BOLD, 14));
		label1.setForeground(new Color(40, 40, 40));
		
		JLabel label2 = new JLabel(subtitle);
		label2.setFont(new Font("Helvetica", Font.PLAIN, 10));
		label2.setForeground(new Color(70, 70, 70));
		
		JPanel node1padded = new JPanel();
		node1padded.setLayout(new BorderLayout());
		node1padded.setBorder(BorderFactory.createEmptyBorder(10, 5, 8, 5));
		node1padded.setSize(width, height);
		node1padded.setPreferredSize(new Dimension(width, height));
		node1padded.setMinimumSize(new Dimension(width, height));
		node1padded.setMaximumSize(new Dimension(width, height));
		node1padded.setBackground(fillColour);
		node1padded.add(label1, BorderLayout.NORTH);
		node1padded.add(label2, BorderLayout.SOUTH);
		
		add(node1padded);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Clicked");
				parent.setColours(230, 140);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				parent.setColours(100, 100);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				node1padded.setBackground(fillColour);
				setBackground(fillColour);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				node1padded.setBackground(hoverFillColour);
				setBackground(hoverFillColour);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	
}
