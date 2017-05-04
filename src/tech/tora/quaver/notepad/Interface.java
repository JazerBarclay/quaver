package tech.tora.quaver.notepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.list.QuickListNode;
import tech.tora.quaver.list.SelectionListNode;
import tech.tora.quaver.notepad.widget.AddButton;
import tech.tora.quaver.notepad.widget.EditAreaThing;
import tech.tora.quaver.notepad.widget.LayoutBuilder;
import tech.tora.quaver.notepad.widget.NoteNode;
import tech.tora.quaver.notepad.widget.NotebookNode;
import tech.tora.quaver.notepad.widget.PreviewAreaThing;
import tech.tora.quaver.types.Cell;
import tech.tora.quaver.types.CellType;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;

public class Interface extends JFrame {

	/**
	 * Serial UUID
	 */
	private static final long serialVersionUID = 1L;

	public LayoutBuilder layout;
	public EditAreaThing editArea;
	public PreviewAreaThing previewArea;

	public static Notebook activeNotebook = null;
	public static Note activeNote = null;

	public Interface() {
		initLayout();
		getNotebooks();
	}

	private void initLayout() {

		layout = new LayoutBuilder(200, 300, new Color(40, 40, 40), // Font Colour
				new Color(230, 230, 230), new Color(210, 210, 210), // Notebook Fill, Notebook Hover
				new Color(230, 230, 230), new Color(210, 210, 210), // Note Fill, Note Hover
				new Color(230, 230, 230), new Color(140, 140, 140), // Edit Fill, Border
				new Font("Helvetica", Font.BOLD, 14), // Notebook Title
				new Font("Helvetica", Font.BOLD, 12), new Font("Helvetica", Font.BOLD, 12),  // NB L1, NB L2
				new Font("Helvetica", Font.BOLD, 14), new Font("Helvetica", Font.BOLD, 10)); // N L1, N L2

		layout.getNotesTopPane().add(new AddButton() {
			private static final long serialVersionUID = 1L;
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (activeNotebook != null) {
					generateNewNote(activeNotebook);
				}
			}
		}, BorderLayout.WEST);

		layout.getBookBotPane().add(new AddButton() {
			private static final long serialVersionUID = 1L;

			@Override
			public void mouseReleased(MouseEvent e) {
				generateNewNotebook();
			}
		}, BorderLayout.WEST);


		editArea = new EditAreaThing() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSave() {
				save();
			}

			@Override
			public void onChange() {
//				System.out.println("Changed");
			}

			@Override
			public void newNotebook() {
				generateNewNotebook();
			}
		};

		layout.getRightWrapper().add(editArea, BorderLayout.CENTER);

		try {
			setIconImage(new ImageIcon(Launcher.class.getResource("/icon1.png")).getImage());
		} catch(Exception e) {
			e.printStackTrace();
		}
		setContentPane(layout.getWrapperPane());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setTitle("Quaver");
		setVisible(true);

		setSize(1280, 800);

	}

	// General purpose getting notebooks and total notes contained in each
	private void getNotebooks() {

		int noteCount = 0;

		for (String lib : Launcher.config.libraries) {
			File[] libContents = new File(lib).listFiles();
			// For each of the files and folders in the library, if they end in .qvnotebook and contain a meta file
			for (File libF : libContents) {
				if (libF.isDirectory() && libF.getAbsolutePath().endsWith(".qvnotebook") && new File(libF.getAbsolutePath()+Launcher.pathSeparator + "meta.json").exists()) {
					System.out.println("Notebook Found: " + libF.getName());
					noteCount = 0;
					File[] notebookContents = libF.listFiles();
					for (File nbF : notebookContents) {
						if (nbF.isDirectory() && nbF.getAbsolutePath().endsWith(".qvnote") && new File(nbF.getAbsolutePath()+Launcher.pathSeparator+"meta.json").exists()) {
							noteCount++;
						}
					}
					Notebook n;
					try {
						n = Notebook.readJSON(libF.getAbsolutePath());
						NotebookNode newNotebook = new NotebookNode(layout.notebooksList, "", n.name, ""+noteCount, n){
							private static final long serialVersionUID = 1L;
							@Override
							public void mouseClick() {
								activeNotebook = n;
								layout.notebooksList.setActiveNode(this);
								activeNote = null;
								updateAll();
							}
						};
						layout.notebooksList.addNode(newNotebook);
					} catch (IOException | ParseException e) {
						e.printStackTrace();
					}
				}
			} // End Notebook File For Loop
		} // End Lib Search Loop

		if (activeNotebook != null) {
			for (QuickListNode n : layout.notebooksList.nodes) {
				NotebookNode node = (NotebookNode) n;
				if (node.notebook.name.equals(activeNotebook.name)) node.setActive(true);
			}
		}

	}

	private void getNotes() {

		File activeNotebookFile = new File(activeNotebook.path);
		File[] dirs = activeNotebookFile.listFiles();
		for (File f : dirs) {
			if (f.exists() && f.isDirectory() && f.getAbsolutePath().endsWith(".qvnote")&& new File(f.getAbsolutePath()+Launcher.pathSeparator+"meta.json").exists()) {
				Note n;
				try {
					n = Note.readMetaJSON(f.getAbsolutePath());
					NoteNode noteNode = new NoteNode(layout.notesList, n.title, new java.util.Date((long)n.created_at*1000).toString(), n) {
						private static final long serialVersionUID = 1L;
						@Override
						public void onClick() {
							activeNote = n;
							layout.notesList.setActiveNode(this);
							updateAll();
						}
					};
					layout.notesList.addNode(noteNode);
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (activeNote != null) {
			for (SelectionListNode n : layout.notesList.nodes) {
				NoteNode node = (NoteNode) n;
				if (node.note.uuid.equals(activeNote.uuid)) node.setActive(true);
			}
		}

	}

	private void generateNewNotebook() {

		// TODO add option for importing an existing notebook

		String notebookName = getTextInputPopup("New Notebook Name", "What would you like to call your new notebook?", "");
		if (notebookName == null) return;
		String location = getComboInputPopup("Select a Destination", "Select a library for this notebook", Launcher.config.libraries, Launcher.config.libraries[0]);
		if (location == null) return;

		try {
			Notebook.writeJSON(notebookName, UUID.nameUUIDFromBytes(notebookName.getBytes()).toString(), location);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to write notebook "+notebookName+"\n" + e, "Failed notebook write", JOptionPane.ERROR_MESSAGE);
		}

		// Update quicklist for new notebook to be added
		layout.notebooksList.clearList();
		getNotebooks();

	}

	private void generateNewNote(Notebook parent) {

		String noteName = getTextInputPopup("New Note Name", "What would you like to call your new note?", "");
		if (noteName == null) return;
		boolean failed = false;
		Note newNote = new Note(parent.path, 
				noteName, UUID.nameUUIDFromBytes(noteName.getBytes()).toString(), 
				System.currentTimeMillis() / 1000L, System.currentTimeMillis() / 1000L);
		try {
			Note.writeContentJSON(newNote);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to write note contents "+noteName+"\n" + e, "Failed note write", JOptionPane.ERROR_MESSAGE);
		}
		if (failed) return;
		try {
			Note.writeMetaJSON(newNote);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to write note contents "+noteName+"\n" + e, "Failed note write", JOptionPane.ERROR_MESSAGE);
		}
		if (failed) return;

		// Update selectionList for new note to be added
		layout.notebooksList.clearList();
		layout.notesList.clearList();
		getNotebooks();
		getNotes();

	}

	private String getTextInputPopup(String title, String message, String defaultValue) {
		String value = (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.NO_OPTION, null, null, defaultValue);
		if (value == null) return null;
		else if (value.equals("")) value = getTextInputPopup(title, message, defaultValue);
		return value;
	}

	private String getComboInputPopup(String title, String message, String[] array, String defaultValue) {
		Object response = JOptionPane.showInputDialog(null, 
				"Select a library for this notebook", "Select a Destination", JOptionPane.QUESTION_MESSAGE,
				null, Launcher.config.libraries, Launcher.config.libraries[0]);
		if (response == null) return null;
		return (String)response;
	}


	//		previewArea.setText("<html><head><title>" + "TITLE" + "</title>"
	//				+ "</head><body style=\"background-color: #2a3137; color: #f2f2f2; font: helvetica; padding: 20px;\">" 
	//				+ "<h1 style=\"color: #FFFFFF;\">"+ "TITLE" +"</h1><hr><br><div style=\"\"></div></body></html>");

	public void updateAll() {

		if (activeNotebook != null) {
			layout.notesTopTitle.setText(activeNotebook.name);
			layout.notesList.clearList();
			getNotes();
		}

		if (activeNote != null) {
			try {
				Note n = Note.readContentsJSON(activeNote.path);
				editArea.setText("");
				for (Cell c : n.cells) {
					editArea.appendText("[~" + c.type + "~]");
					editArea.appendText(c.data);
				}
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}
		} else {
			editArea.setText("");
		}


	}

	public void updatePreview() {

	}

	public void save() {
		// THought here - what if it lost the active one. need a check if its been lonst and reset to null
		if (activeNotebook != null && activeNote != null) {
			
			Cell[] cells = new Cell[]{};
			String[] splitText = editArea.getText().split("\n");
			Cell currentCell = new Cell();
			currentCell.type = CellType.MARKDOWN.type;
			currentCell.data = "";

			int lineCount = 0;

			for (String line : splitText) {
				lineCount++;

				if (lineCount == 1) {
					if (line.startsWith("[~") && line.endsWith("~]") && line.length() > 4) {
						if (line.substring(2, line.length()-2).equals(CellType.MARKDOWN.type)) {
							currentCell.type = CellType.MARKDOWN.type;
						} else if (line.substring(2, line.length()-2).equals(CellType.CODE.type)) {
							currentCell.type = CellType.CODE.type;
						} else if (line.substring(2, line.length()-2).equals(CellType.TEXT.type)) {
							currentCell.type = CellType.TEXT.type;
						}
						currentCell.data = "";
					} else {
						currentCell.data += line;
						if (lineCount == splitText.length) currentCell.data += "";
						else currentCell.data += "\n";
					}
				} else {
					if (line.startsWith("[~") && line.endsWith("~]") && line.length	() > 4) {
						Cell[] tmp = new Cell[cells.length+1];
						for (int i = 0; i < cells.length; i++) tmp[i] = cells[i];
						tmp[cells.length] = currentCell;
						cells = tmp;
						currentCell = new Cell();
						if (line.substring(2, line.length()-2).equals(CellType.MARKDOWN.type)) {
							currentCell.type = CellType.MARKDOWN.type;
						} else if (line.substring(2, line.length()-2).equals(CellType.CODE.type)) {
							currentCell.type = CellType.CODE.type;
						} else if (line.substring(2, line.length()-2).equals(CellType.TEXT.type)) {
							currentCell.type = CellType.TEXT.type;
						}
						currentCell.data = "";
					} else {
						currentCell.data += line;
						if (lineCount == splitText.length) currentCell.data += "";
						else currentCell.data += "\n";
					}
				}

			}

			Cell[] tmp = new Cell[cells.length+1];
			for (int i = 0; i < cells.length; i++) tmp[i] = cells[i];
			tmp[cells.length] = currentCell;
			cells = tmp;
			
			try {
				Note n0;
				Note n1 = Note.readMetaJSON(activeNote.path);
				
				n0 = new Note(new File(activeNote.path).getParent().toString(), n1.title, n1.uuid, n1.created_at, System.currentTimeMillis() / 1000L);
				n0.cells = cells;

				Note.writeContentJSON(n0);
				Note.writeMetaJSON(n0);
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	
}
