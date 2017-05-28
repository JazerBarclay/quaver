package tech.tora.quaver.notepad.layout;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PaneHorizontal extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel leftPane, centerPane, rightPane;
	
	public PaneHorizontal() {
		this.setLayout(new BorderLayout());
		leftPane = new JPanel(new BorderLayout());
		centerPane = new JPanel(new BorderLayout());
		rightPane = new JPanel(new BorderLayout());

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
	
}
