package tech.tora.quaver.notepad.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.panel.PaneHorizontal;
import tech.tora.tools.swing.panel.PaneVertical;

public class TinyTemplate extends QuaverTemplate {

	private JPanel localWrapper;
	
	private PaneVertical listWrapper;
	private PaneVertical contentWrapper;
	
	private PaneHorizontal listTop;
	private PaneHorizontal listBot;
	
	private static int nodeWidth = 200;
	private static int notebooksTopHeight = 30, notebooksBotHeight = 30;
	
	public TinyTemplate(Theme theme) {
		super(theme);
	}

	@Override
	protected JPanel buildPanels(Theme theme) {
		localWrapper = new JPanel();
		localWrapper.setLayout(new BorderLayout());
		localWrapper.setBackground(new Color(100, 100, 100));
		
		listWrapper = new PaneVertical(theme.notebookFillColour.getAsColor());
		listWrapper.setPreferredSize(new Dimension(nodeWidth, 0));

		contentWrapper = new PaneVertical(theme.editFillColour.getAsColor());
		
		listWrapper.getHeaderPane().setPreferredSize(new Dimension(nodeWidth, notebooksTopHeight));
		listWrapper.getFooterPane().setPreferredSize(new Dimension(nodeWidth, notebooksBotHeight));
		
		listTop = new PaneHorizontal(theme.editFillColour.getAsColor());
		listBot = new PaneHorizontal(theme.editFillColour.getAsColor());
		
		return localWrapper;
	}

	@Override
	protected void constructPanels(JPanel wrapper) {
		localWrapper.add(listWrapper, BorderLayout.WEST);
		localWrapper.add(contentWrapper, BorderLayout.CENTER);
		
		listWrapper.getHeaderPane().add(listTop);
		listWrapper.getFooterPane().add(listBot);
	}

	@Override
	public JPanel getLibraryListPanel() {
		return listWrapper.getCenterPane();
	}

	@Override
	public JPanel getNotebookListPanel() {
		return listWrapper.getCenterPane();
	}

	@Override
	public JPanel getNoteListPanel() {
		return listWrapper.getCenterPane();
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
		return listTop.getCenterPane();
	}

	@Override
	public JPanel getEditTitlePanel() {
		return contentWrapper.getHeaderPane();
	}

	@Override
	public JPanel getEditAreaPanel() {
		return contentWrapper.getCenterPane();
	}

	@Override
	public JPanel getEditTagPanel() {
		return contentWrapper.getFooterPane();
	}

	@Override
	public JPanel getPreviewAreaPanel() {
		return new JPanel();
	}

	@Override
	public JPanel getPreviewStatusPanel() {
		return new JPanel();
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
