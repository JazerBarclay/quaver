package tech.tora.quaver.notepad.layout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.theme.Theme;

public class PreviewLayout extends LayoutOld {
	
	public PreviewLayout(Theme t) {
		super(t);
		setDefaultWidth(800);
		setDefaultHeight(1000);
	}

	@Override
	public void initFrame(Theme t) {
		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		}
		
		topMenu = new JMenuBar();
		
		wrapper = new JPanel(new BorderLayout());
		wrapper.setBackground(new Color(100, 100, 100));
		
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
