package tech.tora.quaver.notepad.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import tech.tora.quaver.theme.Theme;

public class PreviewTemplate extends QuaverTemplate {

	protected JPanel localWrapper;
	protected JPanel splitter;
	protected JPanel editAreaPanel;
	
	public PreviewTemplate(Theme theme) {
		super(theme);
	}


	@Override
	protected JPanel buildPanels(Theme theme) {
		localWrapper = new JPanel();
		localWrapper.setLayout(new BorderLayout());
		localWrapper.setBackground(new Color(100, 100, 100));

		splitter = new JPanel(new GridLayout());
		
		editAreaPanel = new JPanel();
		editAreaPanel.setLayout(new BorderLayout());
		editAreaPanel.setBorder(BorderFactory.createEmptyBorder());
		
		return localWrapper;
	}

	@Override
	protected void constructPanels(JPanel wrapper) {
		wrapper.add(splitter, BorderLayout.CENTER);
		splitter.add(editAreaPanel);
	}

	@Override
	public JPanel getLibraryListPanel() {
		return new JPanel();
	}

	@Override
	public JPanel getNotebookListPanel() {
		return new JPanel();
	}

	@Override
	public JPanel getNoteListPanel() {
		return new JPanel();
	}

	@Override
	public JPanel getLibraryTitlePanel() {
		return new JPanel();
	}

	@Override
	public JPanel getNotebookTitlePanel() {
		return new JPanel();
	}

	@Override
	public JPanel getNoteTitlePanel() {
		return new JPanel();
	}

	@Override
	public JPanel getEditTitlePanel() {
		return new JPanel();
	}

	@Override
	public JPanel getEditAreaPanel() {
		return new JPanel();
	}

	@Override
	public JPanel getEditTagPanel() {
		return new JPanel();
	}

	@Override
	public JPanel getPreviewAreaPanel() {
		return editAreaPanel;
	}

	@Override
	public JPanel getPreviewStatusPanel() {
		return new JPanel(); // TODO - Add preview area status panel
	}

	@Override
	public JPanel getAddLibraryButtonPanel() {
		return new JPanel();
	}

	@Override
	public JPanel getAddNotebookButtonPanel() {
		return new JPanel();
	}

	@Override
	public JPanel getAddNoteButtonPanel() {
		return new JPanel();
	}

}
