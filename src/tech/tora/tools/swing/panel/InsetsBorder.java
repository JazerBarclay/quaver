package tech.tora.tools.swing.panel;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

public class InsetsBorder implements javax.swing.border.Border {

	private Insets insets;
	
	public InsetsBorder(Insets insets) {
		this.insets = insets;
	}
	
	@Override
	public Insets getBorderInsets(Component arg0) {
		return insets;
	}

	@Override
	public boolean isBorderOpaque() {
		return false;
	}

	@Override
	public void paintBorder(Component arg0, Graphics arg1, int arg2, int arg3, int arg4, int arg5) {
		// Do Nothing
	}

}
