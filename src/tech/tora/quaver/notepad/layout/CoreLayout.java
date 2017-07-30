package tech.tora.quaver.notepad.layout;

import javax.swing.JMenuBar;
import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.frame.AdvancedFrame;
import tech.tora.tools.swing.layout.Layout;

public abstract class CoreLayout extends Layout {

	public CoreLayout(AdvancedFrame parent, Theme theme) {
		super(parent, theme);
	}

	@Override
	public void constructTopBar(JMenuBar menu) {
		// Build a versatile menu which applies to *ALL* layouts
		
	}
	
}
