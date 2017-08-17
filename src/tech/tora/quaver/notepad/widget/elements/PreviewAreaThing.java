package tech.tora.quaver.notepad.widget.elements;

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

	protected JEditorPane preview;
	
	public PreviewAreaThing() {
		
		preview = new JEditorPane();
		preview.setMargin(new Insets(0, 0, 0, 0));
		preview.setAutoscrolls(false);
		preview.setEditable(false);
		preview.setContentType("text/html");
		
		JScrollPane previewScroll = new JScrollPane(preview, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		
		setLayout(new BorderLayout());
		setOpaque(false);
		add(previewScroll, BorderLayout.CENTER);
	}
	
//	public abstract void setHTML();
	
	public void setText(String s) {
		preview.setText(s);
	}

	@Deprecated
	public void appendText(String s) {
		preview.setText(preview.getText() + s);
	}
	
	
}
