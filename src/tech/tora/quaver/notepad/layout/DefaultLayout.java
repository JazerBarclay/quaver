package tech.tora.quaver.notepad.layout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.theme.Theme;

public class DefaultLayout extends LayoutOld {

	public DefaultLayout(Theme t) {
		super(t);
	}

	@Override
	public void initFrame(Theme t) {
		
	}
	
	@Override
	public void buildFrame() {
		
	}

	@Override
	public JPanel getWrapper() {
		return null;
	}

	@Override
	public JMenuBar getMenu() {
		return null;
	}

	@Override
	public void windowCloseAction() {
		Launcher.exit(0, "Close Button Request");
	}

}
