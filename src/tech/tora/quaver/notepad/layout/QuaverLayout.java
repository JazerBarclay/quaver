package tech.tora.quaver.notepad.layout;

import javax.swing.JPanel;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.notepad.template.QuaverTemplate;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.layout.Layout;
import tech.tora.tools.swing.list.ListNode;
import tech.tora.tools.swing.template.Template;

public abstract class QuaverLayout extends Layout {
	
	private QuaverTemplate template;
	private Theme theme;
	protected String name;
	protected int release, major, minor;

	protected ListNode activeLibraryNode = null;
	protected ListNode activeNotebookNode = null;
	protected ListNode activeNoteNode = null;
	
	protected Library activeLibrary = null;
	protected Notebook activeNotebook = null;
	protected Note activeNote = null;
	
	public QuaverLayout(QuaverTemplate template, Theme theme, String projectName, int release, int major, int minor) {
		this.theme = theme;
		this.name = projectName;
		this.release = release;
		this.major = major;
		this.minor = minor;
		
		this.template = template;
		
		constructElements(theme);
		buildElements(template);
	}

	public abstract void constructElements(Theme theme);
	public abstract void buildElements(QuaverTemplate template);
	
	public Template getTemplate() {
		return template;
	}
	
	public Theme getTheme() {
		return theme;
	}
	
	@Override
	public String getName() {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	@Override
	public String getTitle() {
		return getName() + " M" + release + "." + major + " r" + minor;
	}

	@Override
	public JPanel getWrapper() {
		return getTemplate().getWrapperPanel();
	}
	
	/* ------------------------------------------------------ */
	// Data Management
	/* ------------------------------------------------------ */
	
	public abstract ListNode getLibraryNodeFromList();
	
	public abstract ListNode getNotebooNodeFromList();
	
	public abstract ListNode getNoteNodeFromList();
	
	public abstract void addLibraryToList(Library library);
	
	public abstract void addNotebookToList(Notebook notebook);

	public abstract void addNoteToList(Note note);

	public abstract void updateLibrary(Library library);
	
	public abstract void updateNotebook(Notebook notebook);
	
	public abstract void updateNote(Note note);
	
	public abstract void removeLibraryFromList(Library library);

	public abstract void removeNotebookFromList(Notebook notebook);

	public abstract void removeNoteFromList(Note note);

	public void saveLibraryToSystem(Library library) throws Exception {
		Library.writeMetaJSON(library);
	}

	public void saveNotebookToSystem(Notebook notebook) throws Exception {
		Notebook.writeMetaJSON(notebook);
	}

	public void saveNoteToSystem(Note note) throws Exception {
		Note.writeContentJSON(note);
		Note.writeMetaJSON(note);
	}
	
	public void deleteLibraryFromSystem(Library library) throws Exception {
		System.out.println(library.getPath());
//		try {
//		    Files.delete(new File(library.getPath()).toPath());
//		} catch (NoSuchFileException x) {
//		    System.err.format("%s: no such" + " file or directory%n", library.getPath());
//		} catch (DirectoryNotEmptyException x) {
//		    System.err.format("%s not empty%n", library.getPath());
//		} catch (IOException x) {
//		    // File permission problems are caught here.
//		    System.err.println(x);
//		}
	}

	public void deleteNotebookFromSystem(Notebook notebook) throws Exception {
		System.out.println(notebook.getPath());
//		try {
//		    Files.delete(new File(notebook.getPath()).toPath());
//		} catch (NoSuchFileException x) {
//		    System.err.format("%s: no such" + " file or directory%n", notebook.getPath());
//		} catch (DirectoryNotEmptyException x) {
//		    System.err.format("%s not empty%n", notebook.getPath());
//		} catch (IOException x) {
//		    // File permission problems are caught here.
//		    System.err.println(x);
//		}
	}

	public void deleteNoteFromSystem(Note note) throws Exception {
		System.out.println(note.getPath());
//		try {
//		    Files.delete(new File(note.getPath()).toPath());
//		} catch (NoSuchFileException x) {
//		    System.err.format("%s: no such" + " file or directory%n", note.getPath());
//		} catch (DirectoryNotEmptyException x) {
//		    System.err.format("%s not empty%n", note.getPath());
//		} catch (IOException x) {
//		    // File permission problems are caught here.
//		    System.err.println(x);
//		}
	}

	
	/* ------------------------------------------------------ */
	// Actives
	/* ------------------------------------------------------ */
	
	public void setActiveLibraryNode (ListNode libraryNode) {
		this.activeLibraryNode = libraryNode;
	}
	
	public ListNode getActiveLibraryNode() {
		return activeLibraryNode;
	}
	
	public void setActiveNotebookNode (ListNode notebookNode) {
		this.activeNotebookNode = notebookNode;
	}
	
	public ListNode getActiveNotebookNode() {
		return activeNotebookNode;
	}
	
	public void setActiveNoteNode (ListNode noteNode) {
		this.activeNoteNode = noteNode;
	}
	
	public ListNode getActiveNoteNode() {
		return activeNoteNode;
	}
	
	public void setActiveLibrary(Library library) {
		this.activeLibrary = library;
	}
	
	public Library getActiveLibrary() {
		return activeLibrary;
	}
	
	public void setActiveNotebook(Notebook notebook) {
		this.activeNotebook = notebook;
	}

	public Notebook getActiveNotebook() {
		return activeNotebook;
	}
	
	public void setActiveNote(Note note) {
		this.activeNote = note;
	}

	public Note getActiveNote() {
		return activeNote;
	}
	
	
	/* ------------------------------------------------------ */
	// Display
	/* ------------------------------------------------------ */
	
	public abstract void setEditText(String text);

	public abstract String getEditText();
	
	public abstract String getEditTitle();

	public abstract void clearEditText();
	
	public abstract void updatePreview(Note note);

	public abstract void updatePreview(String title, String notes);
	
	
	/* ------------------------------------------------------ */
	// Window Management
	/* ------------------------------------------------------ */
	
	@Override
	public void windowOpenAction() {}

	@Override
	public void windowCloseAction() {
		Launcher.exit(1, "Close action requested from a Quaver Layout");
	}

	@Override
	public void windowMinimiseAction() {}

	@Override
	public void windowMaximiseAction() {}

	@Override
	public void windowGainFocusAction() {}

	@Override
	public void windowLoseFocusAction() {}

}
