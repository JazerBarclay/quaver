package tech.tora.quaver.notepad.layout;

import javax.swing.JPanel;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.frame.AdvancedFrame;

public abstract class CompactLayoutTemplate extends ListLayout {
	
	public CompactLayoutTemplate(AdvancedFrame parent, Theme theme) {
		super(theme);
	}

	@Override
	public JPanel buildFrame(Theme theme) {
		JPanel wrapper = new JPanel();
		return wrapper;
	}

	@Override
	public JPanel constructFrame(JPanel wrapper) {
		return wrapper;
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
