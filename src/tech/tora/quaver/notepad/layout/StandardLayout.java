package tech.tora.quaver.notepad.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.notepad.widget.elements.AddButton;
import tech.tora.quaver.notepad.widget.elements.EditAreaThing;
import tech.tora.quaver.notepad.widget.elements.PreviewAreaThing;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.frame.AdvancedFrame;
import tech.tora.tools.swing.list.BasicList;
import tech.tora.tools.swing.list.BasicListNode;

public abstract class StandardLayout extends StandardLayoutTemplate {
	
	public JLabel notebookTitle;

	public BasicList notebooksList;
	public BasicList notesList;

	public AddButton btnAddNotebook;
	public AddButton btnAddNote;
	
	public EditAreaThing editArea;
	public PreviewAreaThing previewArea;
	
	public static Notebook activeNotebook = null;
	public static Note activeNote = null;
	
	public StandardLayout(AdvancedFrame parent, Theme theme) {
		super(parent, theme);
	}

	@Override
	public void buildElements() {
		notebookTitle = new JLabel();
		notebookTitle.setText("Quaver M" + Launcher.buildID);
		notebookTitle.setFont(new Font("Helvetica", Font.BOLD, 14));
		notebookTitle.setHorizontalAlignment(JLabel.CENTER);
		notebookTitle.setBackground(new Color(200, 200, 0));
		notebookTitle.setPreferredSize(new Dimension(200, 30));
		notebookTitle.setMinimumSize(new Dimension(200, 30));
		notebookTitle.setMaximumSize(new Dimension(200, 30));
		
		notebooksList = new BasicList(200);
		BasicListNode n = new BasicListNode(25, "0", "Notebooks", 
				new Color(230, 230, 230), new Color(200, 200, 200), 
				new Font("Helvetica", Font.BOLD, 14), new Color(0, 0, 0)) {};
		notebooksList.addNode(n);
		
		notesList = new BasicList(300);
		
		btnAddNotebook = new AddButton() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Add Notebook Clicked");
			}
		};
		
		btnAddNote = new AddButton() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Add Note Clicked");
			}
		};
		
		editArea = new EditAreaThing() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onSave() {
				
			}
			
			@Override
			public void onChange() {
				
			}
			
			@Override
			public void newNotebook() {
				
			}
			
		};
		
		previewArea = new PreviewAreaThing() {
			private static final long serialVersionUID = 1L;
		};
		
	}

	@Override
	public void constructElements() {
		notebooksTop.add(notebookTitle);

		// Add Lists
		notebooksListContainer.add(notebooksList, BorderLayout.CENTER);
		notesListContainer.add(notesList, BorderLayout.CENTER);
		
		// Add Buttons
		notebooksBot.getLeftPane().add(btnAddNotebook, BorderLayout.WEST);
		notesTop.getLeftPane().add(btnAddNote, BorderLayout.WEST);
		
		splitter.add(editArea);
		splitter.add(previewArea);
		
	}

}
