package tech.tora.quaver.notepad.screen;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import tech.tora.quaver.notepad.layout.StandardLayout;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Cell;
import tech.tora.quaver.types.CellType;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.list.BasicClickListNode;
import tech.tora.tools.swing.list.BasicListNode;
import tech.tora.tools.swing.list.ClickListener;
import tech.tora.tools.system.log.Analytic;

public class StandardScreen extends StandardLayout {

	private Library activeLibrary = null;
	private Notebook activeNotebook = null;
	private Note activeNote = null;
	
	public StandardScreen(Theme theme, String projectName, int release, int major, int minor) {
		super(theme, projectName, release, major, minor);
	}

	@Override
	public void addLibraryToList(Library library) {
		notebooksList.addNode(new BasicListNode(20, library.getPath() + library.getName(), library.getName(), 
				getTheme().notebookFillColour.addShade(-15, -15, -15), getTheme().notebookHoverColour.getAsColor(), 
				new Font("Helvetica", Font.BOLD, 10), getTheme().fontColour.getAsColor(), 0) {
		});
	}

	@Override
	public void addNotebookToList(Notebook notebook) {
		notebooksList.addNotebookNode("/icon1.png", notebook.getUUID(), "   " + notebook.getName(), 
				"" + notebook.getNoteAsArray().length, new ClickListener() {
			
			@Override
			public void onClick() {
				setActiveLibrary(notebook.getParent());
				setActiveNotebook(notebook);
				setActiveNote(null);
				
				notesList.clear();
				clearEditText();
				updatePreview();
				
				for (Note n : notebook.getNoteAsArray()) {
					addNoteToList(n);
				}
			}
		});
	}

	@Override
	public void addNoteToList(Note note) {
		notesList.addNode(new BasicClickListNode(40, note.getUUID(), note.getTitle(), 
				getTheme().notebookFillColour.getAsColor(), getTheme().notebookHoverColour.getAsColor(), 
				new Font("Helvetica", Font.BOLD, 12), getTheme().fontColour.getAsColor(), -20) {
			@Override
			public void onClick() {
				notesList.onClick(this);
				
				setActiveNote(note);
				
				String text = "";
				for (Cell c : note.getCells()) {
					text+=("[~" + c.type + "~]");
					text+="\n";
					if (c.type.equals(CellType.CODE.type)) text+=("{~" + c.language + "~}");
					text+="\n";
					text+=c.data;
				}
			
				setEditText(text);
			}
			
		});
	}

	@Override
	public void editLibraryInList(Library library) {}

	@Override
	public void editNotebookInList(Notebook notebook) {}

	@Override
	public void editNoteInList(Note note) {}

	@Override
	public void removeLibraryFromList(Library library) {}

	@Override
	public void removeNotebookFromList(Notebook notebook) {}

	@Override
	public void removeNoteFromList(Note note) {}

	@Override
	public boolean saveLibraryToSystem(Library library) {
		return false;
	}

	@Override
	public boolean saveNotebookToSystem(Notebook notebook) {
		return false;
	}

	@Override
	public boolean saveNoteToSystem(Note note) {
		note.setTitle(txtNoteTitle.getText());
//		note.clearCells();
		// Set note cells here
		try {
			Note.writeContentJSON(note);
			Note.writeMetaJSON(note);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteLibraryFromSystem(Library library) {
		return false;
	}

	@Override
	public boolean deleteNotebookFromSystem(Notebook notebook) {
		return false;
	}

	@Override
	public boolean deleteNoteFromSystem(Note note) {
		return false;
	}
	

	// Actives
	
	@Override
	public Library getActiveLibrary() {
		return activeLibrary;
	}

	@Override
	public void setActiveLibrary(Library library) {
		this.activeLibrary = library;
		// go through list and run active library click method
		if (library == null) return;
		for (String key : notebooksList.getNodeKeys()) {
			if (notebooksList.getNodes().get(key).UUID.equals(library.getPath() + File.separator + library.getName())) {
				System.out.println("ACTIVE: " + library.getName());
			}
		}
	}

	@Override
	public Notebook getActiveNotebook() {
		return activeNotebook;
	}

	@Override
	public void setActiveNotebook(Notebook notebook) {
		this.activeNotebook = notebook;
		if (notebook == null) return;
		for (String key : notebooksList.getNodeKeys()) {
			if (notebooksList.getNodes().get(key).UUID.equals(notebook.getUUID())) {
				notebooksList.onClick((BasicListNode)notebooksList.getNodes().get(key));
				notesList.clear();
				editArea.setText("");
				updatePreview("", "");
				
				for (Note n : notebook.getNoteAsArray()) {
					addNoteToList(n);
				}
				
			}
		}
	}

	@Override
	public Note getActiveNote() {
		return activeNote;
	}

	@Override
	public void setActiveNote(Note note) {
		this.activeNote = note;
		if (note == null) return;
		// go through notes list and run active notes click method
		for (String key : notesList.getNodeKeys()) {
			if (notesList.getNodes().get(key).UUID.equals(note.getUUID())) {
				notesList.onClick((BasicListNode)notesList.getNodes().get(key));
				
				String text = "";
				for (Cell c : note.getCells()) {
					text+=("[~" + c.type + "~]");
					text+="\n";
					if (c.type.equals(CellType.CODE.type)) text+=("{~" + c.language + "~}");
					text+="\n";
					text+=c.data;
				}
				setEditText(text);
				txtNoteTitle.setText(note.getTitle());
				updatePreview(note.getTitle(), text);

			}
		}
	}

	// Edits
	
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
	public void updatePreview(String title, Cell[] cells) {
		updateNoteCells();
		String text = "";
		for (Cell c : cells) {
			text += ("[~" + c.type + "~]" + "<br>");
			for (String line : c.data.split("\\r?\\n")) {
				text += "" + line + "\n";
			}
		}
		updatePreview(title, text);
	}

	@Override
	public void updatePreview(String title, String notes) {
		updateNoteCells();
		String compiled = "";
		
		for (String text : notes.split("\n")) {
			compiled += text;
			compiled += "<br>";
		}
		
		String text = "<html>"
				+ "<head><title>" + "Quaver" + "</title></head>"
				+ "<body style=\"background-color: #393F4B; color: #f2f2f2; font: helvetica; padding: 20px; word-wrap: break-word;\">" 
				+ "<h1>" + title + "</h1>"
				+ "<hr><br/>"
				+ compiled
				+ "</body></html>";
		
		previewArea.setText(text);
		
	}

	@Override
	public String getEditTitle() {
		return txtNoteTitle.getText();
	}

	private void updatePreview() {
		updateNoteCells();
		updatePreview("", "");
	}
	
	private void updateNoteCells() {
		if (activeNote == null) return;
		
		Cell[] cells = new Cell[] {};
		
		Cell activeCell = null;
		CellType activeCellType = null;
		String activeCellLang = null;
		String activeCellData = null;
		
		int i = 0;
		for (String line : getEditText().split("\n")) {
			if (i == 0) {
				activeCell = new Cell();
				activeCellData = "";
				if (line.startsWith("[~") && line.endsWith("~]") && line.length() > 4) {
					if (line.equals("[~text~]")) activeCellType = CellType.TEXT;
					else if (line.equals("[~markdown~]")) activeCellType = CellType.MARKDOWN;
					else if (line.equals("[~code~]")) activeCellType = CellType.CODE;
					else if (line.equals("[~latex~]")) activeCellType = CellType.LATEX;
					else activeCellType = CellType.MARKDOWN;
					activeCellData = "";
				} else if (line.startsWith("{~") && line.endsWith("~}") && line.length() > 4) {
					activeCellType = CellType.CODE;
					activeCellLang = line.substring(2, line.length()-2);
					activeCellData = "";
				} else {
					activeCellType = CellType.MARKDOWN;
					activeCellLang = null;
					activeCellData = "";
					activeCellData += (line + "\n");
				}
			} else {
				if (line.startsWith("{~")  && line.endsWith("~}") && activeCellLang == null) {
					activeCellLang = line.substring(2, line.length() -2);
				} else if (line.startsWith("[~") && line.endsWith("~]")) {
					activeCell.data = activeCellData;
					activeCell.language = activeCellLang;
					activeCell.type = activeCellType.type;
					cells = addCellToArray(cells, activeCell);
					activeCell = new Cell();
					activeCellType = null;
					activeCellLang = null;
					activeCellData = "";
				} else {
					activeCellData += (line + "\n");
				}
			}
		}
		
//		Cell activeCell = null;
//		CellType type = null;
//		String activeCellData = "";
//		
//		String[] builder = new String[] {};
//		String[] splitText = getEditText().split("\n");
//		
//		int i = 0;
//		for (String line : splitText) {
//			if (i == 0) {
//				if (!line.startsWith("[~") || !line.endsWith("~]")) {
//					builder = addString(builder, "[~markdown~]");
//					i++;
//				}
//				builder = addString(builder, line);
//			} else {
//				
//			}
//			i++;
//		}
		
	}
	
	private String[] addString(String[] oldArray, String newString) {
		String[] newStringArray = new String[oldArray.length+1];
		for (int i = 0; i < oldArray.length; i++) newStringArray[i] = oldArray[i];
		newStringArray[oldArray.length] = newString;
		return newStringArray;
	}
	
	private Cell[] addCellToArray(Cell[] oldArray, Cell newCell) {
		Cell[] newCellArray = new Cell[oldArray.length+1];
		for (int i = 0; i < oldArray.length; i++) newCellArray[i] = oldArray[i];
		newCellArray[oldArray.length] = newCell;
		return newCellArray;
	}
	
}
