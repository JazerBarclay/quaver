package tech.tora.quaver.notepad.layout;

import javax.swing.JPanel;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.frame.AdvancedFrame;
import tech.tora.tools.swing.layout.Layout;

public abstract class CompactLayoutTemplate extends Layout {
	
	public CompactLayoutTemplate(AdvancedFrame parent, Theme theme) {
		super(parent, theme);
	}

	@Override
	public void buildFrame(Theme theme) {
		
	}

	@Override
	public void constructFrame(JPanel wrapperPanel) {
		
	}
	
	
	/* ------------------------------------------------------ */
	// Frame Action Management
	/* ------------------------------------------------------ */
	
	@Override
	public void windowOpenAction() {
		System.out.println("Greetings");
	}

	@Override
	public void windowCloseAction() {
		Launcher.exit(0, "Close requested from Compact Layout Window");
	}

	@Override
	public void windowMinimiseAction() {
		System.out.println("Minimised");
	}

	@Override
	public void windowMaximiseAction() {
		System.out.println("Maximise");
	}

	@Override
	public void windowGainFocusAction() {
		System.out.println("Focused");
	}

	@Override
	public void windowLoseFocusAction() {
		System.out.println("LoS");
	}
	
}
