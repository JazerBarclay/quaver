package tech.tora.tools.swing.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class PaneVertical extends JPanel {

	/** * **/
	private static final long serialVersionUID = 1L;

	/** Header border panel for header section content **/
	private JPanel headerPane;
	
	/** Center border panel for header section content **/
	private JPanel centerPane;
	
	/** Footer border panel for header section content **/
	private JPanel footerPane;
	
	
	/* ------------------------------------------------------ */
	// Constructors and Initialisation
	/* ------------------------------------------------------ */
	
	/**
	 * Initialise the vertical pane with a base colour
	 * @param colour
	 */
	public PaneVertical(Color colour) {
		this.setLayout(new BorderLayout());
		headerPane = new JPanel(new BorderLayout());
		centerPane = new JPanel(new BorderLayout());
		footerPane = new JPanel(new BorderLayout());

		if (colour != null) {
			headerPane.setBackground(colour);
			centerPane.setBackground(colour);
			footerPane.setBackground(colour);
		}
		
		add(headerPane, BorderLayout.NORTH);
		add(centerPane, BorderLayout.CENTER);
		add(footerPane, BorderLayout.SOUTH);
	}
	
	
	/* ------------------------------------------------------ */
	// Core Methods
	/* ------------------------------------------------------ */
	
	/**
	 * Sets the colour of all content panes and the wrapper pane
	 * @param colour
	 */
	public void setColour(Color colour) {
		headerPane.setBackground(colour);
		centerPane.setBackground(colour);
		footerPane.setBackground(colour);
	}
	
	
	/* ------------------------------------------------------ */
	// Getters and Setters
	/* ------------------------------------------------------ */
	
	/** Returns the header panel **/
	public JPanel getHeaderPane() {
		return headerPane;
	}

	/** Sets the header panel **/
	public void setHeaderPane(JPanel headerPane) {
		this.headerPane = headerPane;
	}

	/** Returns the center panel **/
	public JPanel getCenterPane() {
		return centerPane;
	}

	/** Sets the center panel **/
	public void setCenterPane(JPanel centerPane) {
		this.centerPane = centerPane;
	}

	/** Returns the footer panel **/
	public JPanel getFooterPane() {
		return footerPane;
	}

	/** Sets the footer panel **/
	public void setFooterPane(JPanel footerPane) {
		this.footerPane = footerPane;
	}

	
}
