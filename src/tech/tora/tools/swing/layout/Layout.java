package tech.tora.tools.swing.layout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.frame.AdvancedFrame;

public abstract class Layout {

	private String title;
	private static int width = -1, height = -1;
	private static int defaultWidth = 800, defaultHeight = 600;

	protected AdvancedFrame parent;
	protected JMenuBar topMenu;
	protected JPanel wrapper;

	public Layout(AdvancedFrame parent, Theme theme) {
		this.parent = parent;
		initVariables();
		buildFrame(theme);
		constructFrame(wrapper);
		buildElements();
		constructElements();
		constructTopBar(topMenu);
	}

	/** Initialise variables **/
	private final void initVariables() {
		topMenu = new JMenuBar();
		wrapper = new JPanel();
	}


	/* ------------------------------------------------------ */
	// Frame Content Management
	/* ------------------------------------------------------ */
	
	public abstract void buildFrame(Theme theme);
	public abstract void constructFrame(JPanel wrapperPanel);
	public abstract void constructTopBar(JMenuBar menu);
	public abstract void buildElements();
	public abstract void constructElements();

	/* ------------------------------------------------------ */
	// Window Action Overrides
	/* ------------------------------------------------------ */

	public abstract void windowOpenAction();
	public abstract void windowCloseAction();
	public abstract void windowMinimiseAction();
	public abstract void windowMaximiseAction();
	public abstract void windowGainFocusAction();
	public abstract void windowLoseFocusAction();

	/* ------------------------------------------------------ */

	/** Returns the menu bar **/
	public final JMenuBar getMenu() {
		return topMenu;
	}

	/** Returns the content wrapper pane **/
	public final JPanel getWrapper() {
		return wrapper;
	}

	/* ------------------------------------------------------ */

	/** Default width set by the layout standard **/
	public void setDefaultWidth(int w) {
		defaultWidth = w;
	}

	/** Returns the default width of the layout **/
	public int getDefaultWidth() {
		return defaultWidth;
	}

	/** Default height set by the layout standard **/
	public void setDefaultHeight(int h) {
		defaultHeight = h;
	}

	/** Returns the default height of the layout **/
	public int getDefaultHeight() {
		return defaultHeight;
	}

	/** Sets the active width of the layout **/
	public void setWidth(int w) {
		width = w;
		parent.updateFrameSize(w, parent.getHeight(), true);
	}

	/** Sets the active height of the layout **/
	public void setHeight(int h) {
		height = h;
		parent.updateFrameSize(parent.getWidth(), h, true);
	}

	/** Returns the active width of the layout **/
	public int getWidth() {
		return width;
	};

	/** Returns the active height of the layout **/
	public int getHeight() {
		return height;
	};

	/** Sets the layout title **/
	public void setTitle(String title) {
		this.title = title;
	}

	/** Returns the layout title **/
	public String getTitle() {
		return title;
	};

}
