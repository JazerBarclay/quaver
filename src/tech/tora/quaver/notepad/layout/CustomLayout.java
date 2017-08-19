package tech.tora.quaver.notepad.layout;

import javax.swing.JPanel;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.layout.Layout;

/**
 * Basic management for future layouts to keep things neato
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
	
	/* ------------------------------------------------------ */
	// Layout Generation
	/* ------------------------------------------------------ */
	
	/**
	 * Create panels
	 * @param theme
	 * @return
	 */
	public abstract JPanel buildFrame(Theme theme);
	
	/**
	 * Combine panels to generate layout template 
	 * @param wrapper
	 * @return
	 */
	public abstract JPanel constructFrame(JPanel wrapper);
	
	/**
	 * Create elements for layout
	 * @param theme
	 */
	public abstract void buildElements(Theme theme);
	
	/**
	 * Add elements to the layout template
	 */
	public abstract void constructElements();
	
	
	/* ------------------------------------------------------ */
	// Standard Layout Overrides
	/* ------------------------------------------------------ */
	
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
