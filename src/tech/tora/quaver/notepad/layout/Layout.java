package tech.tora.quaver.notepad.layout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

public abstract class Layout {

	private int width = -1, height = -1;
	private int defaultWidth = 0, defaultHeight = 0;
	
	public Layout() {
		buildFrame();
	}
	
	public abstract JPanel getWrapper();

	public abstract void buildFrame();
	
	public abstract JMenuBar getMenu();

	/** Default width set by the layout standard **/
	public void setDefaultWidth(int w) {
		defaultWidth = w;
	}
	
	public int getDefaultWidth() {
		return defaultWidth;
	}
	
	/** Default height set by the layout standard **/
	public void setDefaultHeight(int h) {
		defaultHeight = h;
	}
	
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
