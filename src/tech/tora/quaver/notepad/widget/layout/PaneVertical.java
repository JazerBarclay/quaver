package tech.tora.quaver.notepad.layout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class PaneVertical extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel headerPane, centerPane, footerPane;
	
	public PaneVertical(Color c) {
		this.setLayout(new BorderLayout());
		headerPane = new JPanel(new BorderLayout());
		centerPane = new JPanel(new BorderLayout());
		footerPane = new JPanel(new BorderLayout());

		if (c != null) {
			headerPane.setBackground(c);
			centerPane.setBackground(c);
			footerPane.setBackground(c);
		}
		
		add(headerPane, BorderLayout.NORTH);
		add(centerPane, BorderLayout.CENTER);
		add(footerPane, BorderLayout.SOUTH);
	}

	public JPanel getHeaderPane() {
		return headerPane;
	}

	public void setHeaderPane(JPanel headerPane) {
		this.headerPane = headerPane;
	}

	public JPanel getCenterPane() {
		return centerPane;
	}

	public void setCenterPane(JPanel centerPane) {
		this.centerPane = centerPane;
	}

	public JPanel getFooterPane() {
		return footerPane;
	}

	public void setFooterPane(JPanel footerPane) {
		this.footerPane = footerPane;
	}
	
	public void setColour(Color c) {
		headerPane.setBackground(c);
		centerPane.setBackground(c);
		footerPane.setBackground(c);
	}
	
}
