package tech.tora.tools.swing.layout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

public abstract class Layout {

	private static int defaultWidth = 800, defaultHeight = 600;
	
	public Layout() {
		// Do Nothing
	}
	
	public abstract String getName();
	public abstract String getTitle();
	public abstract JPanel getWrapper();
	public abstract JMenuBar getMenu();
	
	public abstract void windowOpenAction();
	public abstract void windowCloseAction();
	public abstract void windowMinimiseAction();
	public abstract void windowMaximiseAction();
	public abstract void windowGainFocusAction();
	public abstract void windowLoseFocusAction();
	
	public void setDefaultWidth(int w) {
		defaultWidth = w;
	}

	public int getDefaultWidth() {
		return defaultWidth;
	}

	public void setDefaultHeight(int h) {
		defaultHeight = h;
	}

	public int getDefaultHeight() {
		return defaultHeight;
	}
	
}
