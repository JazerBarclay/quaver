package tech.tora.quaver.notepad.layout;

import javax.swing.JPanel;

import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.frame.AdvancedFrame;
import tech.tora.tools.swing.layout.Layout;

public abstract class PreviewLayoutTemplate extends Layout {

	public PreviewLayoutTemplate(AdvancedFrame parent, Theme theme) {
		super(parent, theme);
		setDefaultWidth(800);
		setDefaultHeight(600);
	}

	@Override
	public void buildFrame(Theme theme) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void constructFrame(JPanel wrapperPanel) {
		// TODO Auto-generated method stub
		
	}

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
