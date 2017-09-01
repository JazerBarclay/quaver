package tech.tora.quaver.notepad.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextPane;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.notepad.template.QuaverTemplate;
import tech.tora.quaver.notepad.widget.elements.EditAreaThing;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.list.BasicList;

public abstract class TinyLayout extends QuaverLayout {

	protected JMenuBar menu;
	
	protected JLabel noteTitle;
	protected BasicList list;
	protected JTextPane txtNoteTitle;
	protected EditAreaThing editArea;
	
	protected Library activeLibrary = null;
	protected Notebook activeNotebook = null;
	protected Note activeNote = null;
	
	public TinyLayout(QuaverTemplate template, Theme theme, String projectName, int release, int major, int minor) {
		super(template, theme, projectName, release, major, minor);
		setDefaultWidth(1000);
		setDefaultHeight(600);
	}

	@Override
	public JMenuBar getMenu() {
		return menu;
	}

	@Override
	public void windowCloseAction() {
		Launcher.exit(1, "Close action requested from Tiny Layout");
	}
	
	@Override
	public void constructElements(Theme theme) {
		menu = new JMenuBar();
		
		noteTitle = new JLabel();
		noteTitle.setText(getTitle());
		noteTitle.setFont(new Font("Helvetica", Font.BOLD, 14));
		noteTitle.setHorizontalAlignment(JLabel.CENTER);
		noteTitle.setBackground(new Color(200, 200, 0));
		noteTitle.setPreferredSize(new Dimension(200, 30));
		noteTitle.setMinimumSize(new Dimension(200, 30));
		noteTitle.setMaximumSize(new Dimension(200, 30));
		
		list = new BasicList(300);
		list.setBackground(theme.notebookFillColour.getAsColor());
		
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
				if (!saveNoteToSystem(getActiveNote())) {
					System.out.println("File not saved");
				}
			}
			
			@Override
			public void onChange() {
				updatePreview(txtNoteTitle.getText(), editArea.getText());
			}
			
			@Override
			public void newNotebook() {
				
			}
			
		};
		
		
		editArea.setText("*This is a test preview of " + getTitle() + "*\n\n" 
				+ "# Patch Notes\n"
				+ "## " + release + "." + major + " r" + minor + "\n"
				+ "* Window management has been reworked to be cleaner when switching layouts\n"
				+ "* Always on top window support added to window\n"
				+ "* Top menu bar added for more functions not shown on screen\n"
				+ "* Standardized how layouts are generated for easy edits and updates\n"
				+ "* Layout selection added to top bar for different views of the data\n"
				+ "* Mac support added for top bar in top mac menu bar\n"
				+ "* Notebook list reworked to allow multiple libraries to be used\n"
				+ "* Markdown support temporarily disabled for maintenance (nightly)\n"
				+ "* New library, notebook and note creation disabled for maintenance (nightly)\n"
				+ "* Saving of any library, notebook and note is highly volatile right now (nightly)\n\n"
				+ "**Author Note:** Hope you guys find this update to be a big improvement. Also get back to me if you run it in the console to get how long the load in time is for you on your system.\nThanks :)\n"
				+ "\n"
				+ "## 0.3 r0\n"
				+ "* Flight checks done on startup\n"
				+ "* Configuration done on startup\n"
				+ "* Generates JSON configuration\n"
				+ "* Notebooks generated with meta file\n"
				+ "* Notebook UUID generated using notebook name salt\n"
				+ "* Notes generated in Notebook folder\n"
				+ "* Notes content holds only data\n"
				+ "* Notes meta contains all meta data attributed to the content\n"
				+ "* Saving only functional through keyboard shortcut CTRL+S\n"
				+ "* Cell type management functional\n"
				+ "* Documentation.md contains beginning of the technical specification\n"
				+ "");
		
	}

	@Override
	public void buildElements(QuaverTemplate template) {
		
		template.getNotebookTitlePanel().add(noteTitle);
		template.getNotebookListPanel().add(list);
		template.getEditTitlePanel().add(txtNoteTitle);
		template.getEditAreaPanel().add(editArea);
		
	}
	
	@Override
	public void setEditText(String text) {
		editArea.setText(text);
		editArea.setCaratPos(0);
	}
	
	@Override
	public String getEditText() {
		return editArea.getText();
	}

	@Override
	public void clearEditText() {
		txtNoteTitle.setText("");
		setEditText("");
	}

	@Override
	public String getEditTitle() {
		return txtNoteTitle.getText();
	}

	@Override
	public void setActiveLibrary(Library library) {
		this.activeLibrary = library;
	}
		
	@Override
	public Library getActiveLibrary() {
		return activeLibrary;
	}
	
	@Override
	public void setActiveNotebook(Notebook notebook) {
		this.activeNotebook = notebook;
	}

	@Override
	public Notebook getActiveNotebook() {
		return activeNotebook;
	}
	
	@Override
	public void setActiveNote(Note note) {
		this.activeNote = note;
	}

	@Override
	public Note getActiveNote() {
		return activeNote;
	}

}
