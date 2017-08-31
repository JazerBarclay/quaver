package tech.tora.quaver.notepad.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextPane;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.notepad.template.QuaverTemplate;
import tech.tora.quaver.notepad.template.StandardTemplate;
import tech.tora.quaver.notepad.widget.elements.AddButton;
import tech.tora.quaver.notepad.widget.elements.EditAreaThing;
import tech.tora.quaver.notepad.widget.elements.PreviewAreaThing;
import tech.tora.quaver.notepad.widget.list.NotebookList;
import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.list.BasicList;

public abstract class StandardLayout extends QuaverLayout {
	
	protected JMenuBar menu;
	
	protected JLabel notebookTitle;

	protected NotebookList notebooksList;
	protected BasicList notesList;
	
	protected AddButton btnAddNotebook;
	protected AddButton btnAddNote;
	
	protected JTextPane txtNoteTitle;
	
	protected EditAreaThing editArea;
	protected PreviewAreaThing previewArea;
	
	public StandardLayout(Theme theme, String projectName, int release, int major, int minor) {
		super(new StandardTemplate(theme), theme, projectName, release, major, minor);
		setDefaultWidth(1600);
		setDefaultHeight(800);
	}
	
	@Override
	public void windowCloseAction() {
		Launcher.exit(1, "Close action requested from Standard Layout");
	}


	@Override
	public void constructElements(Theme theme) {
		menu = new JMenuBar();
		
		notebookTitle = new JLabel();
		notebookTitle.setText(getTitle());
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
		
		txtNoteTitle = new JTextPane();
		txtNoteTitle.setBackground(theme.editFillColour.getAsColor());
		txtNoteTitle.setFont(new Font("Helvetica", Font.BOLD, 21));
		txtNoteTitle.setText("Welcome to Quaver");
		txtNoteTitle.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// Do Nothing
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				updatePreview(txtNoteTitle.getText(), editArea.getText());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// Do Nothing
			}
		});
		
		editArea = new EditAreaThing() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onSave() {
				saveNoteToSystem(getActiveNote());
			}
			
			@Override
			public void onChange() {
				updatePreview(txtNoteTitle.getText(), editArea.getText());
			}
			
			@Override
			public void newNotebook() {
				
			}
			
		};
		
		editArea.setText("This is a test preview of " + getTitle());
		
		
		previewArea = new PreviewAreaThing() {
			private static final long serialVersionUID = 1L;
		};
		
		updatePreview(txtNoteTitle.getText(), editArea.getText());
		
	}

	@Override
	public void buildElements(QuaverTemplate template) {
		
		template.getNotebookTitlePanel().add(notebookTitle);

		template.getNotebookListPanel().add(notebooksList);
		template.getNoteListPanel().add(notesList);
		
		template.getAddNotebookButtonPanel().add(btnAddNotebook);
		template.getAddNoteButtonPanel().add(btnAddNote);
		
		template.getEditTitlePanel().add(txtNoteTitle);
		
		template.getEditAreaPanel().add(editArea);
		template.getPreviewAreaPanel().add(previewArea);
		
	}

	@Override
	public JMenuBar getMenu() {
		return menu;
	}

	
}
