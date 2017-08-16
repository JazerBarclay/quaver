package tech.tora.tools.swing.layout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 * Standard template for standard JFrame building
 * @author Nythril
 */
public abstract class Layout {

	private static int defaultWidth = 800, defaultHeight = 600;
	// Positioning to be managed later

	public Layout() {
		
	}

	/**
	 * Returns the name of the program used in the menu and as part of the title
	 * @return String - name
	 */
	public abstract String getName();

	/* ------------------------------------------------------ */
	// Frame Content Management
	/* ------------------------------------------------------ */
	
	/**
	 * Returns the menu used in the frame
	 * @return JMenuBar - menu
	 */
	public abstract JMenuBar getMenu();
	
	/**
	 * Returns the most top level wrapper of the frame layout
	 * @return JPanel - wrapper
	 */
	public abstract JPanel getWrapper();
	
	/**
	 * Returns the title used in the frame heading
	 * @return String - title
	 */
	public abstract String getTitle();
	
	/* ------------------------------------------------------ */
	// Window Action Overrides
	/* ------------------------------------------------------ */

	/**
	 * Window Opening Action
	 */
	public abstract void windowOpenAction();
	
	/**
	 * Window Closing Action
	 */
	public abstract void windowCloseAction();
	
	/**
	 * Window Minimise Action
	 */
	public abstract void windowMinimiseAction();
	
	/**
	 * Window Maximise Action
	 */
	public abstract void windowMaximiseAction();
	
	/**
	 * Window Focused Action
	 */
	public abstract void windowGainFocusAction();
	
	/**
	 * Window LoS Action
	 */
	public abstract void windowLoseFocusAction();
	

	/* ------------------------------------------------------ */
	// Frame Defaults
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

}
