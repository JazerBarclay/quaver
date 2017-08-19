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
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.list.BasicClickListNode;
import tech.tora.tools.swing.list.BasicList;
import tech.tora.tools.swing.list.BasicListNode;
import tech.tora.tools.swing.list.ClickListener;

/**
 * Standard layout for the notepad
 * @author Nythril
 */
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
		
		notebooksList = new BasicList(200);
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
	
	@Override
	public void addLibraryNodeToList(Library lib) {
		BasicListNode node = new BasicListNode(28, lib.getPath()+Launcher.pathSeparator+lib.getName(), lib.getName(), 
		theme.notebookFillColour.addShade(-15, -15, -15), theme.notebookHoverColour.getAsColor(), 
		new Font("Helvetica", Font.BOLD, 14), theme.fontColour.getAsColor(), -20) {};
		notebooksList.addNode(node);
		notebooksList.revalidate();
	}
	
	@Override
	public void addNotebookNodeToList(Notebook notebook, ClickListener clickEvent) {
		notebooksList.addNode(new BasicClickListNode(25, 
			notebook.getUUID(), "  " + notebook.getName(), 
			theme.notebookFillColour.getAsColor(), theme.notebookHoverColour.getAsColor(), 
			new Font("Helvetica", Font.BOLD, 12), theme.fontColour.getAsColor(), -20) {
				@Override
				public void onClick() {
					notebooksList.onClick(this);
					clickEvent.onClick();
				}
		});
		notebooksList.revalidate();
	}
	
	@Override
	public void addNoteNodeToList(Note noteb) {
		// Do Here
		notesList.revalidate();
	}
	
//	@Override
//	public void populateMenus(LinkedHashMap<String, Library> libraries) {
//		
//	}
//	
//	public LinkedHashMap<String, Library> getData() {
//		Library l;
//		for (String key :  libraryArray.keySet()) {
//			l = libraryArray.get(key);
//			if (config.isDevmode()) System.out.println("(" + l.getNotebookCount() + ") " + l.getName() + ": " + l.getPath());
//			addLibrary(l);
//		}
//		return libraryArray;
//	}
//	
//	private void addLibrary(Library lib) {
//		BasicListNode node = new BasicListNode(28, lib.getPath()+Launcher.pathSeparator+lib.getName(), lib.getName(), 
//			theme.notebookFillColour.addShade(-15, -15, -15), theme.notebookHoverColour.getAsColor(), 
//			new Font("Helvetica", Font.BOLD, 14), theme.fontColour.getAsColor()) {};
//		notebooksList.addNode(node);
//		for (Notebook nb : lib.getNotebookAsArray()) {
//			if (config.isDevmode()) System.out.println("|-- (" + nb.getNoteCount() + ") " + nb.getName() + ": " + nb.getUUID() + " : " + nb.getPath());
//			addNotebook(nb);
//		}
//	}
//	
//	private void addNotebook(Notebook notebook) {
//		notebooksList.addNode(new BasicClickListNode(25, 
//				notebook.getUUID(), "  " + notebook.getName(), 
//				theme.notebookFillColour.getAsColor(), theme.notebookHoverColour.getAsColor(), 
//				new Font("Helvetica", Font.BOLD, 12), theme.fontColour.getAsColor()) {
//			
//			@Override
//			public void onClick() {
//				System.out.println("Test");
//			}
//			
//		});
//		for (Note n : notebook.getNoteAsArray()) {
//			if (config.isDevmode()) System.out.println("    |--(" + n.getCells().length + ") " + n.getTitle() + ": " + n.getUUID() + " : " + n.getPath());
//			addNote(n);
//		}
//	}
//	
//	private void addNote(Note note) {
//		
//	}
	

}
