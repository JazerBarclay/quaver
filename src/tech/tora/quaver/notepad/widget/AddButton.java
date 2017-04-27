package tech.tora.quaver.notepad.widget;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public abstract class AddButton extends JPanel implements MouseInputListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddButton() {
		JLabel notesTopLeftAddNote = new JLabel("+");
		notesTopLeftAddNote.setFont(new Font("Helvetica", Font.PLAIN, 18));
		notesTopLeftAddNote.setHorizontalAlignment(JLabel.CENTER);
		notesTopLeftAddNote.setPreferredSize(new Dimension(28, 28));
		setLayout(new BorderLayout());
		setOpaque(false);
		addMouseListener(this);
		add(notesTopLeftAddNote);
	}

//	public abstract void onMouseClick();

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Do Nothing
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Do Nothing
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Do Nothing
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Do Nothing
	}

//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		onMouseClick();
//	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// Do Nothing
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// Do Nothing
	}
	
}
