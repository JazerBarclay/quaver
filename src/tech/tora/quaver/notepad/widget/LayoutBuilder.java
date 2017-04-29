package tech.tora.quaver.notepad.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tech.tora.quaver.list.QuickList;
import tech.tora.quaver.list.SelectionList;

public class LayoutBuilder {

	private int notebookWidth, notesWidth;
	
	private Color fontColor;
	
	private Color notebookFillColor, notebookHoverFillColor, notesFillColor, notesHoverFillColor, editPreviewFillColor, borderColor;
	private Font notebookTitleFont, notebookL1Font, notebookL2Font, notesL1Font, notesL2Font;
	
	public JPanel wrapperPane;
	
	public JPanel leftWrapper;
	public JPanel rightWrapper;

	public JPanel bookPane;
	public JPanel bookTopPane;
	public JPanel bookBotPane;
	
	public JLabel bookTopLabel;
	public JLabel bookBotLabel;
	
	public JPanel notesPane;
	public JPanel notesTopPane;
	public JPanel notesBotPane;
	
	public JLabel notesTopRightFill;
	public JLabel notesTopTitle;
	public JLabel notesBotLabel;

	public JPanel rightTopPane;
	public JPanel rightBotPane;

	public EditAreaThing editArea;
	
	public QuickList notebooksList;
	public SelectionList notesList;
	
	public LayoutBuilder(int notebooksWidth, int notesWidth, Color fontColor,
			Color notebookFillColor, Color notebookHoverFillColor, Color notesFillColor, Color notesHoverFillColor, 
			Color editPreviewFillColor, Color borderColor, 
			Font notebookTitleFont, Font notebookL1Font, Font notebookL2Font, Font notesL1Font, Font notesL2Font) {
		
		// Init Variables
		this.setNotebookWidth(notebooksWidth);
		this.setNotesWidth(notesWidth);

		this.fontColor = fontColor;
		this.notebookFillColor = notebookFillColor;
		this.notebookHoverFillColor = notebookHoverFillColor;
		this.notesFillColor = notesFillColor;
		this.notesHoverFillColor = notesHoverFillColor;
		this.editPreviewFillColor = editPreviewFillColor;
		this.borderColor = borderColor;
		
		this.notebookTitleFont = notebookTitleFont;
		this.notebookL1Font = notebookL1Font;
		this.notebookL2Font = notebookL2Font;
		this.notesL1Font = notesL1Font;
		this.notesL2Font = notesL2Font;
		
		notebooksList = new QuickList("Notebooks", fontColor, notebookFillColor, notebookHoverFillColor, 
				notebookTitleFont, notebookL1Font, notebookL2Font);
		notesList = new SelectionList(fontColor, notesFillColor, notesHoverFillColor, borderColor, 
				notesL1Font, notesL2Font);
	
		// Main Wrapper
		wrapperPane = new JPanel(new BorderLayout());
		
		// Notebook and Notes Pane Wrapper
		leftWrapper = new JPanel(new BorderLayout());
		leftWrapper.setOpaque(false);
		
		// Edit and Preview Pane Wrapper
		rightWrapper = new JPanel(new BorderLayout());
		rightWrapper.setBackground(editPreviewFillColor);
		
		// Notebook Panel
		bookPane = new JPanel(new BorderLayout());
		bookPane.setBackground(notebookFillColor);
		
		bookTopPane = new JPanel(new BorderLayout());
		bookTopPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(140, 140, 140)));
		bookTopPane.setOpaque(false);
		
		bookTopLabel = new JLabel();
		bookTopLabel.setText("Notebooks");
		bookTopLabel.setFont(notebookTitleFont);
		bookTopLabel.setHorizontalAlignment(JLabel.CENTER);
		bookTopLabel.setPreferredSize(new Dimension(notebookWidth, 30));
		
		bookBotPane = new JPanel(new BorderLayout());
		bookBotPane.setOpaque(false);
		
		bookBotLabel = new JLabel("");
		bookBotLabel.setPreferredSize(new Dimension(160, 30));
		bookBotLabel.setOpaque(false);
		
		// Notes Panel
		notesPane = new JPanel(new BorderLayout());
		notesPane.setBackground(notesFillColor);
		notesPane.setPreferredSize(new Dimension(notesWidth, 30));
		
		notesTopPane = new JPanel(new BorderLayout());
		notesTopPane.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(140, 140, 140)));
		notesTopPane.setOpaque(false);
		
		notesTopTitle = new JLabel();
		notesTopTitle.setText("Java Notebook");
		notesTopTitle.setFont(new Font("Helvetica", Font.BOLD, 14));
		notesTopTitle.setHorizontalAlignment(JLabel.CENTER);
		notesTopTitle.setPreferredSize(new Dimension(240, 30));
		notesTopTitle.setOpaque(false);
		
		notesTopRightFill = new JLabel();
		notesTopRightFill.setFont(new Font("Helvetica", Font.PLAIN, 14));
		notesTopRightFill.setHorizontalAlignment(JLabel.CENTER);
		notesTopRightFill.setPreferredSize(new Dimension(30, 30));
		
		notesBotPane = new JPanel(new BorderLayout());
		notesBotPane.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(140, 140, 140)));
		notesBotPane.setOpaque(false);
		
		notesBotLabel = new JLabel("Placeholder");
		notesBotLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
		notesBotLabel.setHorizontalAlignment(JLabel.CENTER);
		notesBotLabel.setPreferredSize(new Dimension(30, 30));
		
		/* Left Construction */
		
		// Notebooks Build
		bookTopPane.add(bookTopLabel, BorderLayout.NORTH);
		bookBotPane.add(bookBotLabel, BorderLayout.WEST);
		
		bookPane.add(bookTopPane, BorderLayout.NORTH);
		bookPane.add(notebooksList, BorderLayout.CENTER);
		bookPane.add(bookBotPane, BorderLayout.SOUTH);
		
		// Notes Build
		notesTopPane.add(notesTopTitle, BorderLayout.CENTER);
		notesTopPane.add(notesTopRightFill, BorderLayout.EAST);
		
		notesBotPane.add(notesBotLabel, BorderLayout.CENTER);
		
		notesPane.add(notesTopPane, BorderLayout.NORTH);
		notesPane.add(notesList, BorderLayout.CENTER);
		notesPane.add(notesBotPane, BorderLayout.SOUTH);
		
		// 
		leftWrapper.add(bookPane, BorderLayout.WEST);
		leftWrapper.add(notesPane, BorderLayout.EAST);
		
		// Right Construction
		
		
		// Add left and right wrappers to main wrapper
		wrapperPane.add(leftWrapper, BorderLayout.WEST);
		wrapperPane.add(rightWrapper, BorderLayout.CENTER);
		
	}

	
	/** Generated Getters and Setters **/
	/* TODO Deal with adding updates to the form on changes */
	
	
	/**
	 * @return the notebookWidth
	 */
	public int getNotebookWidth() {
		return notebookWidth;
	}

	/**
	 * @param notebookWidth the notebookWidth to set
	 */
	public void setNotebookWidth(int notebookWidth) {
		this.notebookWidth = notebookWidth;
	}

	/**
	 * @return the notesWidth
	 */
	public int getNotesWidth() {
		return notesWidth;
	}

	/**
	 * @param notesWidth the notesWidth to set
	 */
	public void setNotesWidth(int notesWidth) {
		this.notesWidth = notesWidth;
	}


}
