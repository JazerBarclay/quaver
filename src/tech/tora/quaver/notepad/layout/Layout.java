package tech.tora.quaver.notepad.layout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import tech.tora.quaver.theme.Theme;

/**
 * Base class for interface layout
 * 
 * @author Nythril
 */
public abstract class Layout {
	
	private int width = -1, height = -1;
	private int defaultWidth = 0, defaultHeight = 0;
	
	protected JMenuBar topMenu;
	protected JPanel wrapper;
	
	// Creates the elements and builds the framework
	public Layout(Theme theme) {
		initFrame(theme);
		buildFrame();
		setDefaultWidth(800);
		setDefaultHeight(600);
	}
	
	/** Returns top level wrapper **/
	public abstract JPanel getWrapper();

	/** Creates all elements for frame **/
	public abstract void initFrame(Theme t);

	/** Adds elements to each other to create layout **/
	public abstract void buildFrame();
	
	/** Returns the menu bar **/
	public abstract JMenuBar getMenu();

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
	}

	/** Sets the active height of the layout **/
	public void setHeight(int h) {
		height = h;
	}

	/** Returns the active width of the layout **/
	public int getWidth() {
		return width;
	};

	/** Returns the active height of the layout **/
	public int getHeight() {
		return height;
	};
	
}
