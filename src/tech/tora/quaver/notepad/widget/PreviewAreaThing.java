package tech.tora.quaver.notepad.widget;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public abstract class PreviewAreaThing extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PreviewAreaThing() {
		
		JEditorPane preview = new JEditorPane();
		preview.setMargin(new Insets(0, 0, 0, 0));
		preview.setAutoscrolls(false);
		preview.setContentType("text/html");
		
		JScrollPane previewScroll = new JScrollPane(preview, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		
		setLayout(new BorderLayout());
		setOpaque(false);
		add(previewScroll, BorderLayout.CENTER);
	}
	
	public abstract void setHTML();
	
}
