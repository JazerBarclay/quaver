package tech.tora.quaver.notepad.layout;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import tech.tora.quaver.theme.Theme;

public class CompactLayout extends Layout {
	
	public JMenuBar topMenu;

	private JPanel wrapper;
	
	private JPanel leftWrapper;
	private JPanel rightWrapper;
	
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

}
