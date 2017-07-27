package tech.tora.quaver.notepad.layout;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.theme.Theme;

public class CompactLayout extends LayoutOld {
	
	public CompactLayout(Theme t) {
		super(t);
		setDefaultWidth(1200);
		setDefaultHeight(800);
	}
	
	@Override
	public void initFrame(Theme t) {
		wrapper = new JPanel(new BorderLayout());
		
		topMenu = new JMenuBar();
	}

	@Override
	public void buildFrame() {
		
	}

	@Override
	public JPanel getWrapper() {
		return wrapper;
	}

	@Override
	public JMenuBar getMenu() {
		return topMenu;
	}

	@Override
	public void windowCloseAction() {
		Launcher.exit(0, "Close requested by window");
	}

}
