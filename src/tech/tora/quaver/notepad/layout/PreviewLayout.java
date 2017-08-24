package tech.tora.quaver.notepad.layout;

import tech.tora.quaver.notepad.widget.elements.PreviewAreaThing;
import tech.tora.quaver.theme.Theme;

public abstract class PreviewLayout extends PreviewLayoutTemplate {

	public PreviewAreaThing previewArea;
	
	public PreviewLayout(Theme theme) {
		super(theme);
	}

	@Override
	public void buildElements(Theme theme) {
		previewArea = new PreviewAreaThing() {
			private static final long serialVersionUID = 1L;
		};
		
		previewArea.setText("<html><head><title>" + "Quaver" + "</title>"
				+ "</head><body style=\"background-color: #393F4B; color: #f2f2f2; font: helvetica; padding: 20px;\">" 
				+ "<h1>Welcome to Quaver</h1><hr><br/><p>This is a test preview ([insert build here])</p></body></html>");
		
	}

	@Override
	public void constructElements() {
		splitter.add(previewArea);
	}
	
}
