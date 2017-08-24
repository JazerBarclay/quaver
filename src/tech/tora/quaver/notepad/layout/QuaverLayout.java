package tech.tora.quaver.notepad.layout;

import javax.swing.JPanel;

import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Cell;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.layout.Layout;

public abstract class QuaverLayout extends Layout {
	
	private JPanel wrapper;
	protected Theme theme;
	
	public QuaverLayout(Theme theme) {
		super();
		this.theme = theme;
		wrapper = buildFrame(theme);
		constructFrame(wrapper);
		buildElements(theme);
		constructElements();
	}

	
	
	/* ------------------------------------------------------ */
	// Layout Generation
	/* ------------------------------------------------------ */
	
	/**
	 * Create panels
	 * @param theme
	 * @return
	 */
	public abstract JPanel buildFrame(Theme theme);
	
	/**
	 * Combine panels to generate layout template 
	 * @param wrapper
	 * @return
	 */
	public abstract JPanel constructFrame(JPanel wrapper);
	
	/**
	 * Create elements for layout
	 * @param theme
	 */
	public abstract void buildElements(Theme theme);
	
	/**
	 * Add elements to the layout template
	 */
	public abstract void constructElements();
	
	
	/* ------------------------------------------------------ */
	// Data Management
	/* ------------------------------------------------------ */
	
	/**
	 * Add library to a list
	 * @param library
	 */
	public abstract void addLibrary(Library library);
	
	/**
	 * Add notebook to a list
	 * @param notebook
	 */
	public abstract void addNotebook(Notebook notebook);
	
	/**
	 * Add note to a list
	 * @param note
	 */
	public abstract void addNote(Note note);
	
	/**
	 * Alter a library in the list
	 * @param library
	 */
	public abstract void editLibrary(Library library);
	
	/**
	 * Alter a notebook in the list
	 * @param notebook
	 */
	public abstract void editNotebook(Notebook notebook);
	
	/**
	 * Alter note in the list
	 * @param note
	 */
	public abstract void editNote(Note note);
	
	/**
	 * Remove library from the list
	 * @param library
	 */
	public abstract void removeLibrary(Library library);
	
	/**
	 * Remove notebook from the list
	 * @param notebook
	 */
	public abstract void removeNotebook(Notebook notebook);
	
	/**
	 * Remove note from the list
	 * @param note
	 */
	public abstract void removeNote(Note note);
	
	/**
	 * Save library to the filesystem
	 * @param library
	 * @return true if save successful
	 */
	public abstract boolean saveLibrary(Library library);
	
	/**
	 * Save notebook to the filesystem
	 * @param notebook
	 * @return true if save successful
	 */
	public abstract boolean saveNotebook(Notebook notebook);
	
	/**
	 * Save note to the filesystem
	 * @param note
	 * @return true if save successful
	 */
	public abstract boolean saveNote(Note note);
	
	/**
	 * Delete library from the filesystem
	 * @param library
	 * @return true if delete successful
	 */
	public abstract boolean deleteLibrary(Library library);
	
	/**
	 * Delete notebook from the filesystem
	 * @param notebook
	 * @return true if delete successful
	 */
	public abstract boolean deleteNotebook(Notebook notebook);
	
	/**
	 * Delete note from the filesystem
	 * @param note
	 * @return true if delete successful
	 */
	public abstract boolean deleteNote(Note note);

	
	/* ------------------------------------------------------ */
	// Actives
	/* ------------------------------------------------------ */
	
	/**
	 * Get the active library in the list
	 * @return Library if set, null if unset
	 */
	public abstract Library getActiveLibrary();
	
	/**
	 * Set the active library
	 * @param library
	 */
	public abstract void setActiveLibrary(Library library);
	
	/**
	 * Get the active notebook in the list
	 * @return Notebook if set, null if unset
	 */
	public abstract Notebook getActiveNotebook();
	
	/**
	 * Sets the active notebook
	 * @param notebook
	 */
	public abstract void setActiveNotebook(Notebook notebook);
	
	/**
	 * Get the active note in the list
	 * @return Note if set, null if unset
	 */
	public abstract Note getActiveNote();
	
	/**
	 * Sets the active note
	 * @param note
	 */
	public abstract void setActiveNote(Note note);
	
	/* ------------------------------------------------------ */
	// Display
	/* ------------------------------------------------------ */
	
	/**
	 * Sets the edit area text
	 * @param text
	 */
	public abstract void setEditText(String text);
	
	/**
	 * Get the edit area text
	 * @return String text
	 */
	public abstract String getEditText();
	
	/**
	 * Clears the edit area
	 */
	public abstract void clearEditText();
	
	/**
	 * Update preview area with cells
	 * @param cells
	 */
	@Deprecated
	public abstract void updatePreview(Cell[] cells);

	/**
	 * Update the preview with a HTML complete string
	 * @param notes
	 */
	public abstract void updatePreview(String notes);
	
	/* ------------------------------------------------------ */
	// Standard Layout Overrides
	/* ------------------------------------------------------ */
	
	@Override
	public String getName() {
		return "Quaver";
	}

	@Override
	public String getTitle() {
		return getName();
	}

	@Override
	public JPanel getWrapper() {
		return wrapper;
	}


}
