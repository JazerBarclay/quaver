package tech.tora.quaver.notepad.layout;

import javax.swing.JPanel;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.layout.Layout;

/**
 * Currently just an unused abstraction layer for quick maintenance on the core Layout class
 * @author Nythril
 */
public abstract class CustomLayout extends Layout {

	private JPanel wrapper;
	protected Theme theme;
	
	public CustomLayout(Theme theme) {
		super();
		this.theme = theme;
		wrapper = buildFrame(theme);
		constructFrame(wrapper);
		buildElements(theme);
		constructElements();
	}
	
	public abstract JPanel buildFrame(Theme theme);
	public abstract JPanel constructFrame(JPanel wrapper);
	public abstract void buildElements(Theme theme);
	public abstract void constructElements();
	
	@Override
	public String getName() {
		return "Quaver";
	}

	@Override
	public String getTitle() {
		return getName() + " : M" + Launcher.buildID;
	}

	@Override
	public JPanel getWrapper() {
		return wrapper;
	}

}
