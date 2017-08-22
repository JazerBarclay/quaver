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
import tech.tora.quaver.notepad.widget.list.NotebookList;
import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.list.BasicList;

/**
 * Standard layout for the notepad
 * @author Nythril
 */
public abstract class StandardLayout extends StandardLayoutTemplate {
	
	public JLabel notebookTitle;

	public NotebookList notebooksList;
	public BasicList notesList;

	public AddButton btnAddNotebook;
	public AddButton btnAddNote;
	
	public EditAreaThing editArea;
	public PreviewAreaThing previewArea;
	
	public StandardLayout(Theme theme) {
		super(theme);
	}

	@Override
	public void buildElements(Theme theme) {
		notebookTitle = new JLabel();
		notebookTitle.setText("Quaver M" + Launcher.buildID);
		notebookTitle.setFont(new Font("Helvetica", Font.BOLD, 14));
		notebookTitle.setHorizontalAlignment(JLabel.CENTER);
		notebookTitle.setBackground(new Color(200, 200, 0));
		notebookTitle.setPreferredSize(new Dimension(200, 30));
		notebookTitle.setMinimumSize(new Dimension(200, 30));
		notebookTitle.setMaximumSize(new Dimension(200, 30));
		
		notebooksList = new NotebookList(200, "Helvetica", theme.notebookFillColour.getAsColor(), 
				theme.notebookFillColour.getAsColor(), theme.notebookHoverColour.getAsColor(), 
				theme.fontColour.getAsColor());
		notebooksList.setBackground(theme.notebookFillColour.getAsColor());
		
		notesList = new BasicList(300);
		notesList.setBackground(theme.noteFillColour.getAsColor());
		
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
				
				saveNote(getActiveNote());
			}
			
			@Override
			public void newNotebook() {
				
			}
			
		};
		
		editArea.setText("# Welcome to Quaver\nThis is a test preview (Mark " + Launcher.buildRelease + "." + Launcher.buildMajor + " r" + Launcher.buildMinor +  ")");
		
		
		
		previewArea = new PreviewAreaThing() {
			private static final long serialVersionUID = 1L;
		};
		
		previewArea.setText("<html><head><title>" + "Quaver" + "</title>"
				+ "</head><body style=\"background-color: #393F4B; color: #f2f2f2; font: helvetica; padding: 20px;\">" 
				+ "<h1>Welcome to Quaver</h1><hr><br/><p>This is a test preview (Mark " + Launcher.buildRelease + "." + Launcher.buildMajor + " r" + Launcher.buildMinor +  ")</p></body></html>");
		
	}

	@Override
	public void constructElements() {
		notebooksTop.add(notebookTitle);

		// Add Lists
		notebooksListContainer.add(notebooksList);
		notesListContainer.add(notesList);
		
		// Add Buttons
		notebooksBot.getLeftPane().add(btnAddNotebook, BorderLayout.WEST);
		notesTop.getLeftPane().add(btnAddNote, BorderLayout.WEST);
		
		splitter.add(editArea);
		splitter.add(previewArea);
		
	}
	

}
