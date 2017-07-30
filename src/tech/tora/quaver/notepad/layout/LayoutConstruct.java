package tech.tora.quaver.notepad.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.panel.PaneHorizontal;
import tech.tora.tools.swing.panel.PaneVertical;

public class LayoutConstruct {
	
	private Theme theme;
	
	private JPanel wrapper;
	
	private JPanel leftWrapper;
	private JPanel rightWrapper;

	private PaneVertical notebooksWrapper;
	private PaneVertical notesWrapper;
	private PaneVertical contentWrapper;

	private JPanel notebooksTop;
	private JPanel notebooksListContainer;
	private PaneHorizontal notebooksBot;

	private PaneHorizontal notesTop;
	private JPanel notesListContainer;
	private PaneHorizontal notesBot;

//	private JLabel notebooksTitle; // This will be a button, not text
	@SuppressWarnings("unused")
	private JLabel notesTitle;
	
	private JMenuBar topMenu;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenuItem newMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem updateMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem insertLinkMenuItem;
	
	public LayoutConstruct(Theme t) {
		this.theme = t;
		initFrame();
		buildFrame();
	}
	
	private void initFrame() {
		
		// Top Bar
		topMenu = new JMenuBar();
		
		fileMenu = new JMenu("File");
		newMenuItem = new JMenuItem("New Notebook");
		updateMenuItem = new JMenuItem("New Note");
		saveMenuItem = new JMenuItem("Save All");
		exitMenuItem = new JMenuItem("Exit");
		
		fileMenu.add(newMenuItem);
		fileMenu.add(updateMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(exitMenuItem);
		
		editMenu = new JMenu("Edit");
		insertLinkMenuItem = new JMenuItem("Insert Link");
		
		editMenu.add(insertLinkMenuItem);
		
	    topMenu.add(fileMenu);
	    topMenu.add(editMenu);
		
		
		// Main Wrappers
		wrapper = new JPanel(new BorderLayout());
		wrapper.setOpaque(false);
		
		leftWrapper = new JPanel(new BorderLayout());
		leftWrapper.setOpaque(false);

		rightWrapper = new JPanel(new BorderLayout());
		rightWrapper.setOpaque(false);

		// Notebooks
		notebooksWrapper = new PaneVertical(new Color(110, 110, 110));
		notebooksWrapper.setPreferredSize(null);
		notebooksWrapper.setOpaque(false);
		
		notesWrapper = new PaneVertical(null);
		notesWrapper.setPreferredSize(new Dimension(300, 0));
		notesWrapper.setOpaque(false);

		notebooksTop = new JPanel(new BorderLayout());
		notebooksTop.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, theme.borderColour.getAsColor()));
		notebooksTop.setPreferredSize(new Dimension(200, 40));
		
		notebooksListContainer = new JPanel(new FlowLayout());
		notebooksListContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, theme.borderColour.getAsColor()));
		
		notebooksBot = new PaneHorizontal(null);
		notebooksBot.setPreferredSize(new Dimension(200, 40));
		
		// Notes
		notesTop = new PaneHorizontal(null);
		notesTop.setPreferredSize(new Dimension(300, 40));
		notesTop.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, theme.borderColour.getAsColor()));

		notesListContainer = new JPanel(new FlowLayout());
		notesListContainer.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, theme.borderColour.getAsColor()));
		
		notesBot = new PaneHorizontal(null);
		notesBot.setPreferredSize(new Dimension(300, 40));
		notesBot.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, theme.borderColour.getAsColor()));
		
		// Content
		contentWrapper = new PaneVertical(new Color(210, 210, 210));
		contentWrapper.setOpaque(false);
		
	}
	
	private void buildFrame() {
		// Add components to each other
		wrapper.add(leftWrapper, BorderLayout.WEST);
		wrapper.add(rightWrapper, BorderLayout.CENTER);
		
		leftWrapper.add(notebooksWrapper, BorderLayout.WEST);
		leftWrapper.add(notesWrapper, BorderLayout.EAST);
		rightWrapper.add(contentWrapper, BorderLayout.CENTER);

		notebooksWrapper.getHeaderPane().add(notebooksTop);
		notebooksWrapper.getCenterPane().add(notebooksListContainer);
		notebooksWrapper.getFooterPane().add(notebooksBot);

		notesWrapper.getHeaderPane().add(notesTop);
		notesWrapper.getCenterPane().add(notesListContainer);
		notesWrapper.getFooterPane().add(notesBot);
	}
	
	public JPanel getWrapperPane() {
		return wrapper;
	}
	
	public JMenuBar getMenu() {
		return topMenu;
	}
	
}
