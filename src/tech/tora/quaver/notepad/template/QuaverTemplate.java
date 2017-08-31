package tech.tora.quaver.notepad.template;

import javax.swing.JPanel;

import tech.tora.quaver.theme.Theme;
import tech.tora.tools.swing.template.Template;

public abstract class QuaverTemplate extends Template {

	public QuaverTemplate(Theme theme) {
		super(theme);
	}

	// Lists //
	public abstract JPanel getLibraryListPanel();
	public abstract JPanel getNotebookListPanel();
	public abstract JPanel getNoteListPanel();
	
	// List Headers //
	public abstract JPanel getLibraryTitlePanel();
	public abstract JPanel getNotebookTitlePanel();
	public abstract JPanel getNoteTitlePanel();

	// Edit and Preview //
	public abstract JPanel getEditTitlePanel();
	public abstract JPanel getEditAreaPanel();
	public abstract JPanel getEditTagPanel();
	public abstract JPanel getPreviewAreaPanel();
	public abstract JPanel getPreviewStatusPanel();

	// Buttons //
	public abstract JPanel getAddLibraryButtonPanel();
	public abstract JPanel getAddNotebookButtonPanel();
	public abstract JPanel getAddNoteButtonPanel();
	
	
}
