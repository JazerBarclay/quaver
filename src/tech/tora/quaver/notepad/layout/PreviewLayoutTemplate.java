package tech.tora.quaver.notepad.layout;

import javax.swing.JPanel;

import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.frame.AdvancedFrame;

public abstract class PreviewLayoutTemplate extends ListLayout {

	public PreviewLayoutTemplate(AdvancedFrame parent, Theme theme) {
		super(theme);
		setDefaultWidth(800);
		setDefaultHeight(600);
	}

	@Override
	public JPanel buildFrame(Theme theme) {
		return null;
	}

	@Override
	public JPanel constructFrame(JPanel wrapper) {
		// TODO Auto-generated method stub
		return wrapper;
	}

	/* ------------------------------------------------------ */
	// Frame Action Management
	/* ------------------------------------------------------ */
	
	@Override
	public void windowOpenAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowCloseAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowMinimiseAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowMaximiseAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowGainFocusAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowLoseFocusAction() {
		// TODO Auto-generated method stub
		
	}

}
