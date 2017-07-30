package tech.tora.quaver.notepad.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.frame.AdvancedFrame;
import tech.tora.tools.swing.panel.PaneHorizontal;
import tech.tora.tools.swing.panel.PaneVertical;

public abstract class StandardLayoutTemplate extends CoreLayout {

	protected JPanel leftWrapper;
	protected JPanel rightWrapper;
	
	protected PaneVertical notebooksWrapper;
	protected PaneVertical notesWrapper;
	protected PaneVertical contentWrapper;
	protected JPanel splitter;
	
	protected JPanel notebooksTop;
	protected JPanel notebooksListContainer;
	protected PaneHorizontal notebooksBot;

	protected PaneHorizontal notesTop;
	protected JPanel notesListContainer;
	protected PaneHorizontal notesBot;
	
	private static int notebooksWidth = 200, notesWidth = 300;
	private static int notebooksTopHeight = 30, notebooksBotHeight = 30;
	private static int notesTopHeight = 30, notesBotHeight = 30;
	
	public StandardLayoutTemplate(AdvancedFrame parent, Theme theme) {
		super(parent, theme);
		setDefaultWidth(1600);
		setDefaultHeight(800);
	}

	@Override
	public void buildFrame(Theme theme) {

		// Main Wrappers
		wrapper.setLayout(new BorderLayout());
		wrapper.setBackground(new Color(100, 100, 100));
		
		leftWrapper = new JPanel(new BorderLayout());
		leftWrapper.setBackground(new Color(150, 150, 150));

		rightWrapper = new JPanel(new BorderLayout());
		rightWrapper.setBackground(new Color(150, 150, 150));

		// Notebooks
		notebooksWrapper = new PaneVertical(theme.notebookFillColour.getAsColor());
		
		notesWrapper = new PaneVertical(theme.noteFillColour.getAsColor());

		notebooksTop = new JPanel(new BorderLayout());
		notebooksTop.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, theme.borderColour.getAsColor()));
		notebooksTop.setPreferredSize(new Dimension(notebooksWidth, notebooksTopHeight));
		notebooksTop.setBackground(theme.notebookFillColour.getAsColor());
		
		notebooksListContainer = new JPanel(new FlowLayout());
		notebooksListContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, theme.borderColour.getAsColor()));
		notebooksListContainer.setBackground(theme.notebookFillColour.getAsColor());
		
		notebooksBot = new PaneHorizontal(theme.notebookFillColour.getAsColor());
		notebooksBot.setPreferredSize(new Dimension(notebooksWidth, notebooksBotHeight));
		
		// Notes
		notesTop = new PaneHorizontal(theme.noteFillColour.getAsColor());
		notesTop.setPreferredSize(new Dimension(notesWidth, notesTopHeight));
		notesTop.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, theme.borderColour.getAsColor()));
		
		notesListContainer = new JPanel(new FlowLayout());
		notesListContainer.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, theme.borderColour.getAsColor()));
		notesListContainer.setBackground(theme.noteFillColour.getAsColor());
		
		notesBot = new PaneHorizontal(theme.noteFillColour.getAsColor());
		notesBot.setPreferredSize(new Dimension(notesWidth, notesBotHeight));
		notesBot.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, theme.borderColour.getAsColor()));

		// Content
		contentWrapper = new PaneVertical(new Color(210, 210, 210));
		
		splitter = new JPanel(new GridLayout());
		splitter.setOpaque(false);
	}

	@Override
	public void constructFrame(JPanel wrapperPanel) {
		
		wrapper.add(leftWrapper, BorderLayout.WEST);
		wrapper.add(rightWrapper, BorderLayout.CENTER);
		
		leftWrapper.add(notebooksWrapper, BorderLayout.WEST);
		leftWrapper.add(notesWrapper, BorderLayout.EAST);
		rightWrapper.add(contentWrapper, BorderLayout.CENTER);

		notebooksWrapper.getHeaderPane().add(notebooksTop, BorderLayout.NORTH);
		notebooksWrapper.getCenterPane().add(notebooksListContainer);
		notebooksWrapper.getFooterPane().add(notebooksBot, BorderLayout.CENTER);

		notesWrapper.getHeaderPane().add(notesTop);
		notesWrapper.getCenterPane().add(notesListContainer);
		notesWrapper.getFooterPane().add(notesBot);
		
		contentWrapper.getCenterPane().add(splitter, BorderLayout.CENTER);
		
	}

	/* ------------------------------------------------------ */
	// Frame Action Management
	/* ------------------------------------------------------ */
	
	@Override
	public void windowOpenAction() {
		System.out.println("Greetings");
	}

	@Override
	public void windowCloseAction() {
		Launcher.exit(0, "Close requested from Test Layout Window");
	}

	@Override
	public void windowMinimiseAction() {
		System.out.println("Minimised");
	}

	@Override
	public void windowMaximiseAction() {
		System.out.println("Maximise");
	}

	@Override
	public void windowGainFocusAction() {
		System.out.println("Focused");
	}

	@Override
	public void windowLoseFocusAction() {
		System.out.println("LoS");
	}

}