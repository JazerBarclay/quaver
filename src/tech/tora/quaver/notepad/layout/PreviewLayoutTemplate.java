package tech.tora.quaver.notepad.layout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.frame.AdvancedFrame;

public abstract class PreviewLayoutTemplate extends QuaverLayout {

	protected JPanel splitter;
	
	public PreviewLayoutTemplate(AdvancedFrame parent, Theme theme) {
		super(theme);
		setDefaultWidth(800);
		setDefaultHeight(600);
	}

	@Override
	public JPanel buildFrame(Theme theme) {
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BorderLayout());
		wrapper.setBackground(new Color(100, 100, 100));
		return wrapper;
	}

	@Override
	public JPanel constructFrame(JPanel wrapper) {
		wrapper.add(splitter, BorderLayout.CENTER);
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
		Launcher.exit(0, "Close requested from Preview Layout Window");
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
