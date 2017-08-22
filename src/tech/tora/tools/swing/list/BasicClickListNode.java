package tech.tora.tools.swing.list;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public abstract class BasicClickListNode extends BasicListNode {

	public BasicClickListNode(int height, String uuid, String title, Color fillColour, Color hoverColour,
			Font titleFont, Color titleColour, int clickModifier) {
		super(height, uuid, title, fillColour, hoverColour, titleFont, titleColour, clickModifier);
	}
	
	public abstract void onClick();

	
	@Override
	public JPanel generateNode() {
		wrapper = standardGenerate();
		setupClick(wrapper);
		return wrapper;
	}
	
	private void setupClick(JPanel wrap) {
		
		wrap.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// Do Nothing
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				onClick();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (!active) setFillColour(fill.getAsColor());
				else setFillColour(fill.addShade(clickMod, clickMod, clickMod));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if (true) {
					if (!active) setFillColour(hover.getAsColor());
					else setFillColour(hover.addShade(clickMod, clickMod, clickMod));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
//				onClick();
			}
		});
		
	}
	

}
