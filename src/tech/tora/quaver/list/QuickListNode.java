package tech.tora.quaver.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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

public class QuickListNode extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private QuickList parentList;
	
	private int width = 200;
	private int height = 25;
	
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
		label1.setFont(new Font("Helvetica", Font.BOLD, 12));
		label1.setForeground(new Color(40, 40, 40));

		JLabel label2 = new JLabel(rightSide);
		label2.setFont(new Font("Helvetica", Font.BOLD, 12));
		label2.setForeground(new Color(40, 40, 40));
		
		JPanel node1padded = new JPanel();
		node1padded.setLayout(new BorderLayout());
		node1padded.setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 10));
		node1padded.setSize(width, height);
		node1padded.setPreferredSize(new Dimension(width, height));
		node1padded.setMinimumSize(new Dimension(width, height));
		node1padded.setMaximumSize(new Dimension(width, height));
		node1padded.setBackground(new Color(230, 230, 230));
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
				// TODO Auto-generated method stub
				System.out.println("Clicked");
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				node1padded.setBackground(new Color(230, 230, 230));
				setBackground(new Color(230, 230, 230));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				node1padded.setBackground(new Color(210, 210, 210));
				setBackground(new Color(210, 210, 210));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
}
