package tech.tora.quaver.notepad.layout;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class BasicLayout extends Layout {

	private JPanel wrapper;
	
	public BasicLayout() {
		super();
		setDefaultWidth(1600);
		setDefaultHeight(800);
	}
	
	@Override
	public JPanel getWrapper() {
		return wrapper;
	}

	@Override
	public void buildFrame() {
		wrapper = new JPanel();
	}

	@Override
	public JMenuBar getMenu() {
		return null;
	}

}
