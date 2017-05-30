package tech.tora.quaver.notepad.layout;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class VerticalBorderPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel headerPane, centerPane, footerPane;
	
	public VerticalBorderPane() {
		this.setLayout(new BorderLayout());
		headerPane = new JPanel(new BorderLayout());
		footerPane = new JPanel(new BorderLayout());

		add(headerPane, BorderLayout.NORTH);
		add(footerPane, BorderLayout.SOUTH);
		
	}

}
