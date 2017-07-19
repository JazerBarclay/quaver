package tech.tora.quaver.notepad.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import tech.tora.quaver.notepad.widget.layout.PaneHorizontal;
import tech.tora.quaver.notepad.widget.layout.PaneVertical;
import tech.tora.quaver.theme.Theme;

public class BasicLayout extends Layout {

	private JPanel leftWrapper;
	private JPanel rightWrapper;
	
	private PaneVertical notebooksWrapper;
	private PaneVertical notesWrapper;
	public PaneVertical contentWrapper;
	public JPanel splitter;
	
	public JPanel notebooksTop;
	public JPanel notebooksListContainer;
	public PaneHorizontal notebooksBot;

	public PaneHorizontal notesTop;
	public JPanel notesListContainer;
	private PaneHorizontal notesBot;
	
	private static int notebooksWidth = 200, notesWidth = 300;
	private static int notebooksTopHeight = 30, notebooksBotHeight = 30;
	private static int notesTopHeight = 30, notesBotHeight = 30;
	
	public BasicLayout(Theme t) {
		super(t);
		setDefaultWidth(1600);
		setDefaultHeight(800);
	}
	
	@Override
	public JPanel getWrapper() {
		return wrapper;
	}
	
	@Override
	public void initFrame(Theme theme) {
	
	    if (System.getProperty("os.name").contains("Mac")) {
	    		System.setProperty("apple.laf.useScreenMenuBar", "true");
	    }
		
		// Top Bar
		topMenu = new JMenuBar();

		// Main Wrappers
		wrapper = new JPanel(new BorderLayout());
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
	public void buildFrame() {
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

	@Override
	public JMenuBar getMenu() {
		return topMenu;
	}

}
