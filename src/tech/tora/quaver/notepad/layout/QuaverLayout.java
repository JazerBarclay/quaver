package tech.tora.quaver.notepad.layout;

import javax.swing.JPanel;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.notepad.template.QuaverTemplate;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Cell;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.layout.Layout;
import tech.tora.tools.swing.template.Template;

public abstract class QuaverLayout extends Layout {
	
	private QuaverTemplate template;
	private Theme theme;
	private String name;
	private int release, major, minor;
	
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
	
	public abstract void addLibraryToList(Library library);
	
	public abstract void addNotebookToList(Notebook notebook);

	public abstract void addNoteToList(Note note);

	public abstract void editLibraryInList(Library library);
	
	public abstract void editNotebookInList(Notebook notebook);

	public abstract void editNoteInList(Note note);
	
	public abstract void removeLibraryFromList(Library library);

	public abstract void removeNotebookFromList(Notebook notebook);

	public abstract void removeNoteFromList(Note note);

	public abstract boolean saveLibraryToSystem(Library library);

	public abstract boolean saveNotebookToSystem(Notebook notebook);

	public abstract boolean saveNoteToSystem(Note note);
	
	public abstract boolean deleteLibraryFromSystem(Library library);

	public abstract boolean deleteNotebookFromSystem(Notebook notebook);

	public abstract boolean deleteNoteFromSystem(Note note);

	
	/* ------------------------------------------------------ */
	// Actives
	/* ------------------------------------------------------ */
	
	public abstract Library getActiveLibrary();

	public abstract void setActiveLibrary(Library library);

	public abstract Notebook getActiveNotebook();

	public abstract void setActiveNotebook(Notebook notebook);

	public abstract Note getActiveNote();

	public abstract void setActiveNote(Note note);
	
	
	/* ------------------------------------------------------ */
	// Display
	/* ------------------------------------------------------ */
	
	public abstract void setEditText(String text);

	public abstract String getEditText();

	public abstract void clearEditText();
	
	@Deprecated
	public abstract void updatePreview(String title, Cell[] cells);

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
