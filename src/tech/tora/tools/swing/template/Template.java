package tech.tora.tools.swing.template;

import javax.swing.JPanel;

import tech.tora.quaver.theme.Theme;

public abstract class Template {

	private JPanel wrapperPane;
	
	public Template(Theme theme) {
		wrapperPane = buildPanels(theme);
		constructPanels(wrapperPane);
	}
	
	public JPanel getWrapperPanel() {
		return wrapperPane;
	}
	
	protected abstract JPanel buildPanels(Theme theme);
	protected abstract void constructPanels(JPanel wrapper);
	
}
