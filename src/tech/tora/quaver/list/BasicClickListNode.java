package tech.tora.quaver.list;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public abstract class BasicClickListNode extends BasicListNode {

	public BasicClickListNode(int height, String uuid, String title, Color fillColour, Color hoverColour,
			Font titleFont, Color titleColour) {
		super(height, uuid, title, fillColour, hoverColour, titleFont, titleColour);
	}
	
	public abstract void onClick();

	
	@Override
	public JPanel generateNode() {
		standardGenerate();
		setupClick();
		return wrapper;
	}
	
	private void setupClick() {
		
		wrapper.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// Do Nothing
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// Do Nothing
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				wrapper.setBackground(fill);
				content.setBackground(fill);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if (true) {
					wrapper.setBackground(hover);
					content.setBackground(hover);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				onClick();
			}
		});
		
	}
	

}
