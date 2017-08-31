package tech.tora.quaver.notepad.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.panel.PaneHorizontal;
import tech.tora.tools.swing.panel.PaneVertical;

public class StandardTemplate extends QuaverTemplate {

	private JPanel localWrapper;
	
	private JPanel leftWrapper;
	private JPanel rightWrapper;
	
	private PaneVertical notebooksWrapper;
	private PaneVertical notesWrapper;
	private PaneVertical contentWrapper;
	private JPanel splitter;
	
	private JPanel notebooksTop;
	private JPanel notebooksListContainer;
	private PaneHorizontal notebooksBot;

	private PaneHorizontal notesTop;
	private JPanel notesListContainer;
	private PaneHorizontal notesBot;

	private PaneVertical editAreaPane;
	private JPanel previewAreaPane;
	
	private static int notebooksWidth = 200, notesWidth = 300;
	private static int notebooksTopHeight = 30, notebooksBotHeight = 30;
	private static int notesTopHeight = 30, notesBotHeight = 30;
	
	public StandardTemplate(Theme theme) {
		super(theme);
	}
	
	@Override
	protected JPanel buildPanels(Theme theme) {
		localWrapper = new JPanel();
		
		localWrapper.setLayout(new BorderLayout());
		localWrapper.setBackground(new Color(100, 100, 100));
		
		leftWrapper = new JPanel(new BorderLayout());
		leftWrapper.setBackground(new Color(150, 150, 150));

		rightWrapper = new JPanel(new BorderLayout());
		rightWrapper.setBackground(new Color(150, 150, 150));

		// Notebooks
		notebooksWrapper = new PaneVertical(theme.notebookFillColour.getAsColor());
		
		notesWrapper = new PaneVertical(theme.noteFillColour.getAsColor());
		notesWrapper.setPreferredSize(new Dimension(300, 0));

		notebooksTop = new JPanel(new BorderLayout());
//		notebooksTop.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, theme.borderColour.getAsColor()));
		notebooksTop.setPreferredSize(new Dimension(notebooksWidth, notebooksTopHeight));
		notebooksTop.setBackground(theme.notebookFillColour.getAsColor());
		
		notebooksListContainer = notebooksWrapper.getCenterPane();
		
		notebooksBot = new PaneHorizontal(theme.notebookFillColour.getAsColor());
		notebooksBot.setPreferredSize(new Dimension(notebooksWidth, notebooksBotHeight));
		
		
		// Notes
		notesTop = new PaneHorizontal(theme.noteFillColour.getAsColor());
		notesTop.setPreferredSize(new Dimension(notesWidth, notesTopHeight));
		notesTop.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, theme.borderColour.getAsColor()));
		
		notesListContainer = notesWrapper.getCenterPane();
		notesListContainer.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, theme.borderColour.getAsColor()));
		
		notesBot = new PaneHorizontal(theme.noteFillColour.getAsColor());
		notesBot.setPreferredSize(new Dimension(notesWidth, notesBotHeight));
		notesBot.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, theme.borderColour.getAsColor()));

		// Content
		contentWrapper = new PaneVertical(new Color(210, 210, 210));

//		contentWrapper.getHeaderPane().setPreferredSize(new Dimension(0, 30));
//		contentWrapper.getHeaderPane().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, theme.borderColour.getAsColor()));
//		contentWrapper.getFooterPane().setPreferredSize(new Dimension(0, 30));
//		contentWrapper.getFooterPane().setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, theme.borderColour.getAsColor()));
		
		
		contentWrapper.setColour(theme.editFillColour.getAsColor());
		
		splitter = new JPanel(new GridLayout());
		splitter.setOpaque(false);
		
		editAreaPane = new PaneVertical(theme.editFillColour.getAsColor());
		editAreaPane.setOpaque(false);
		
		editAreaPane.getHeaderPane().setPreferredSize(new Dimension(0, 40));
		editAreaPane.getHeaderPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		
		previewAreaPane = new JPanel();
		previewAreaPane.setLayout(new BorderLayout());
		previewAreaPane.setBorder(BorderFactory.createEmptyBorder());
		previewAreaPane.setOpaque(false);
		
		return localWrapper;
	}

	@Override
	protected void constructPanels(JPanel wrapper) {
		
		wrapper.add(leftWrapper, BorderLayout.WEST);
		wrapper.add(rightWrapper, BorderLayout.CENTER);
		
		leftWrapper.add(notebooksWrapper, BorderLayout.WEST);
		leftWrapper.add(notesWrapper, BorderLayout.EAST);
		rightWrapper.add(contentWrapper, BorderLayout.CENTER);

		notebooksWrapper.getHeaderPane().add(notebooksTop, BorderLayout.NORTH);
		notebooksWrapper.getFooterPane().add(notebooksBot, BorderLayout.CENTER);

		notesWrapper.getHeaderPane().add(notesTop);
		notesWrapper.getFooterPane().add(notesBot);
		
		contentWrapper.getCenterPane().add(splitter, BorderLayout.CENTER);
		
		splitter.add(editAreaPane);		
		splitter.add(previewAreaPane);
	}
	
	@Override
	public JPanel getLibraryListPanel() {
		return notebooksListContainer;
	}

	@Override
	public JPanel getNotebookListPanel() {
		return notebooksListContainer;
	}

	@Override
	public JPanel getNoteListPanel() {
		return notesListContainer;
	}

	@Override
	public JPanel getLibraryTitlePanel() {
		return notebooksTop;
	}

	@Override
	public JPanel getNotebookTitlePanel() {
		return notebooksTop;
	}

	@Override
	public JPanel getNoteTitlePanel() {
		return notesTop;
	}

	@Override
	public JPanel getEditTitlePanel() {
		return editAreaPane.getHeaderPane();
	}

	@Override
	public JPanel getEditAreaPanel() {
		return editAreaPane;
	}

	@Override
	public JPanel getEditTagPanel() {
		return new JPanel();
	}

	@Override
	public JPanel getPreviewAreaPanel() {
		return previewAreaPane;
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
		return notebooksBot.getLeftPane();
	}

	@Override
	public JPanel getAddNoteButtonPanel() {
		return notesTop.getLeftPane();
	}


}
