package tech.tora.tools.swing.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class PaneHorizontal extends JPanel {

	/** * **/
	private static final long serialVersionUID = 1L;
	
	/** Left border panel for left section content **/
	private JPanel leftPane;
	
	/** Center border panel for center section content **/
	private JPanel centerPane;
	
	/** Right border panel for right section content **/
	private JPanel rightPane;
	
	
	/* ------------------------------------------------------ */
	// Constructors and Initialisation
	/* ------------------------------------------------------ */
	
	/**
	 * Initialises the horizontal pane with a base colour
	 * @param colour
	 */
	public PaneHorizontal(Color colour) {
		this.setLayout(new BorderLayout());
		leftPane = new JPanel(new BorderLayout());
		centerPane = new JPanel(new BorderLayout());
		rightPane = new JPanel(new BorderLayout());

		if (colour != null) {
			leftPane.setBackground(colour);
			centerPane.setBackground(colour);
			rightPane.setBackground(colour);
		}
		
		add(leftPane, BorderLayout.WEST);
		add(centerPane, BorderLayout.CENTER);
		add(rightPane, BorderLayout.EAST);
	}
	

	/* ------------------------------------------------------ */
	// Core Methods
	/* ------------------------------------------------------ */
	
	/**
	 * Sets the colour of all content panes and the wrapper pane
	 * @param colour
	 */
	public void setColour(Color colour) {
		leftPane.setBackground(colour);
		centerPane.setBackground(colour);
		rightPane.setBackground(colour);
	}
	
	
	/* ------------------------------------------------------ */
	// Getters and Setters
	/* ------------------------------------------------------ */
	
	/** Returns the left panel **/
	public JPanel getLeftPane() {
		return leftPane;
	}
	
	/** Sets the left panel **/
	public void setLeftPane(JPanel leftPane) {
		this.leftPane = leftPane;
	}

	/** Returns the center panel **/
	public JPanel getCenterPane() {
		return centerPane;
	}

	/** Sets the center panel **/
	public void setCenterPane(JPanel centerPane) {
		this.centerPane = centerPane;
	}

	/** Returns the right panel **/
	public JPanel getRightPane() {
		return rightPane;
	}

	/** Sets the right panel **/
	public void setRightPane(JPanel rightPane) {
		this.rightPane = rightPane;
	}
	
	
}
