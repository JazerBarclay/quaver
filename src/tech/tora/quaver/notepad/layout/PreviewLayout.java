package tech.tora.quaver.notepad.layout;

import javax.swing.JMenuBar;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.notepad.template.PreviewTemplate;
import tech.tora.quaver.notepad.template.QuaverTemplate;
import tech.tora.quaver.notepad.widget.elements.PreviewAreaThing;
import tech.tora.quaver.theme.Theme;

public abstract class PreviewLayout extends QuaverLayout {

	protected JMenuBar menu;
	protected PreviewAreaThing previewArea;
	
	public PreviewLayout(Theme theme, String projectName, int release, int major, int minor) {
		super(new PreviewTemplate(theme), theme, projectName, release, major, minor);
		setDefaultWidth(800);
		setDefaultHeight(1000);
	}

	@Override
	public void windowCloseAction() {
		Launcher.exit(1, "Close action requested from Preview Layout");
	}

	@Override
	public JMenuBar getMenu() {
		return menu;
	}
	
	@Override
	public void constructElements(Theme theme) {
		menu = new JMenuBar();
		
		previewArea = new PreviewAreaThing() {
			private static final long serialVersionUID = 1L;
		};
		
		previewArea.setText(
				"<html>"
				+ "<head><title>" + "Quaver" + "</title></head>"
				+ "<body style=\"background-color: #393F4B; color: #f2f2f2; font: helvetica; padding: 20px;\">" 
				+ "<h1>Welcome to Quaver</h1><hr><br/><p>This is a test preview ([insert build value here])</p>"
				+ "</body></html>");
		
	}

	@Override
	public void buildElements(QuaverTemplate template) {
		template.getPreviewAreaPanel().add(previewArea);
	}

}
