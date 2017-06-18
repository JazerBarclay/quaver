package tech.tora.quaver.notepad.widget.layout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class PaneHorizontal extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel leftPane, centerPane, rightPane;
	
	public PaneHorizontal(Color c) {
		this.setLayout(new BorderLayout());
		leftPane = new JPanel(new BorderLayout());
		centerPane = new JPanel(new BorderLayout());
		rightPane = new JPanel(new BorderLayout());

		if (c != null) {
			leftPane.setBackground(c);
			centerPane.setBackground(c);
			rightPane.setBackground(c);
		}
		
		add(leftPane, BorderLayout.WEST);
		add(centerPane, BorderLayout.CENTER);
		add(rightPane, BorderLayout.EAST);
	}

	public JPanel getLeftPane() {
		return leftPane;
	}

	public void setLeftPane(JPanel leftPane) {
		this.leftPane = leftPane;
	}

	public JPanel getCenterPane() {
		return centerPane;
	}

	public void setCenterPane(JPanel centerPane) {
		this.centerPane = centerPane;
	}

	public JPanel getRightPane() {
		return rightPane;
	}

	public void setRightPane(JPanel rightPane) {
		this.rightPane = rightPane;
	}
	
	public void setColour(Color c) {
		leftPane.setBackground(c);
		centerPane.setBackground(c);
		rightPane.setBackground(c);
	}
	
}
