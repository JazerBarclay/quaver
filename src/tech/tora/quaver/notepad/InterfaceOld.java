package tech.tora.quaver.notepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import tech.tora.quaver.Configuration;
import tech.tora.quaver.Launcher;
import tech.tora.quaver.listold.QuickListNode;
import tech.tora.quaver.listold.SelectionListNode;
import tech.tora.quaver.notepad.widget.elements.AddButton;
import tech.tora.quaver.notepad.widget.elements.EditAreaThing;
import tech.tora.quaver.notepad.widget.elements.LayoutBuilder;
import tech.tora.quaver.notepad.widget.elements.NoteNode;
import tech.tora.quaver.notepad.widget.elements.NotebookNode;
import tech.tora.quaver.notepad.widget.elements.PreviewAreaThing;
import tech.tora.quaver.types.Cell;
import tech.tora.quaver.types.CellType;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;

public class InterfaceOld extends JFrame {

	/**
	 * Serial UUID
	 */
	private static final long serialVersionUID = 1L;

	public static Configuration config;
	public boolean fresh = false;
	
	public LayoutBuilder layout;
	public EditAreaThing editArea;
	public PreviewAreaThing previewArea;

	
	
	public static Notebook activeNotebook = null;
	public static Note activeNote = null;

	public InterfaceOld(Configuration c) {
		if (c == null) {
			fresh = true;
			config = new Configuration();
			config.devmode = false;
			config.name = "Default";
			config.libraries = new String[]{};
		} else {
			config = c;
		}
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

		layout.notesBotFilter.setToolTipText("Filter Here");

		editArea = new EditAreaThing() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSave() {
				save();
			}

			@Override
			public void onChange() {
				updatePreview();
			}

			@Override
			public void newNotebook() {
				generateNewNotebook();
			}
		};

		previewArea = new PreviewAreaThing() {
			private static final long serialVersionUID = 1L;

		};
		previewArea.setText("<html><head><title>" + "Quaver" + "</title>"
				+ "</head><body style=\"background-color: #393F4B; color: #f2f2f2; font: helvetica; padding: 20px;\">" 
				+ "<h1>Welcome to Quaver</h1><hr><br/><p>This is a test preview (pre alpha version 0.8)</p></body></html>");


		// Setting up layout
		
		JPanel splitter = new JPanel(new GridLayout());
		splitter.setOpaque(false);
		splitter.add(editArea);
		splitter.add(previewArea);

		layout.getRightWrapper().add(splitter, BorderLayout.CENTER);


		
		
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

		setSize(1600, 800);

	}

	// General purpose getting notebooks and total notes contained in each
	private void getNotebooks() {

		int noteCount = 0;

		for (String lib : InterfaceOld.config.libraries) {
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
		
		layout.notebooksList.revalidate();

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
		String location = getComboInputPopup("Select a Destination", "Select a library for this notebook", config.libraries, config.libraries[0]);
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
				null, config.libraries, config.libraries[0]);
		if (response == null) return null;
		return (String)response;
	}

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
					editArea.appendText("[~" + c.type + "~]" + "\n");
					editArea.appendText(c.data);
				}
				updatePreview();
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}
		} else {
			editArea.setText("");
			previewArea.setText("");
		}


	}

	public void updatePreview() {
		
		Note n = activeNote;
		
		String previewText = "";
		
		previewText = "<html><head><title>" + n.title + "</title>"
				+ "</head><body style=\"background-color: #393F4B; color: #f2f2f2; font: helvetica; padding: 20px;\">" 
				+ "<h1>"+n.title+"</h1><hr>";
		
		String editText = editArea.getText();
		String[] split = editText.split("\n");
		
		boolean noAction;
		
		CellType lastUsedType = null;
		CellType type = CellType.MARKDOWN;
		
		
		for (String t : split) {
			
			noAction = true;
			
			// Replacements before starting checks
			t = t.replace("<", "&lt;");
			t = t.replace(">", "&gt;");

			// Type Checks
			if (t.startsWith("[~") && t.endsWith("~]") && (t.length() > 4 ? (t.substring(2, t.length()-2).equals(CellType.MARKDOWN.type)) : false)) {
				lastUsedType = type;
				type = CellType.MARKDOWN;
				if (config.devmode) previewText+="[Markdown Set]<br>";
				if (lastUsedType == CellType.CODE) previewText+="</div>";
				noAction = false;
			} 
			if (t.startsWith("[~") && t.endsWith("~]") && (t.length() > 4 ? (t.substring(2, t.length()-2).equals(CellType.CODE.type)) : false)) {
				lastUsedType = type;
				type = CellType.CODE;
				if (config.devmode) previewText+="[Code Set]<br>";
				previewText+="<div style=\"background-color: #2D2D2D;\">";
				// <div style=\"background-color: #272727; width: 10px; float: left;\"></div>
				if (lastUsedType == CellType.CODE) previewText+="</div>";
				noAction = false;
			} 
			if (t.startsWith("[~") && t.endsWith("~]") && (t.length() > 4 ? (t.substring(2, t.length()-2).equals(CellType.TEXT.type)) : false)) {
				lastUsedType = type;
				type = CellType.TEXT;
				if (config.devmode) previewText+="[Text Set]<br>";
				if (lastUsedType == CellType.CODE) previewText+="</div>";
				noAction = false;
			}
			
			if (type.equals(CellType.CODE.type) && t.startsWith("[=") && t.endsWith("=]") && (t.length() > 4) ? (t.substring(2, t.length() -2).equals("javascript")) : false) {
				if (config.devmode) previewText += "[--" + t.substring(2, t.length()-2) + "--]<br>";
				// set code style on here until the end of the section!
				noAction = false;
			}
			
			// Headings
			if (t.startsWith("# ")) {
				previewText += "<h1>";
				previewText += t.substring(2, t.length());
				previewText += "</h1>";
				noAction = false;
			}
			if (t.startsWith("## ")) {
				previewText += "<h2>";
				previewText += t.substring(3, t.length());
				previewText += "</h2>";
				noAction = false;
			}
			if (t.startsWith("### ")) {
				previewText += "<h3>";
				previewText += t.substring(4, t.length());
				previewText += "</h3>";
				noAction = false;
			}
			if (t.startsWith("#### ")) {
				previewText += "<h4>";
				previewText += t.substring(5, t.length());
				previewText += "</h4>";
				noAction = false;
			}
			if (t.startsWith("##### ")) {
				previewText += "<h5>";
				previewText += t.substring(6, t.length());
				previewText += "</h5>";
				noAction = false;
			}
			if (t.startsWith("###### ")) {
				previewText += "<h6>";
				previewText += t.substring(7, t.length());
				previewText += "</h6>";
				noAction = false;
			}

			// Formatting
			if (t.startsWith("*") && t.endsWith("*") && t.length()>2) {
				previewText+="<i>";
				previewText+=t.substring(1, t.length()-1);
				previewText+="</i>";
				noAction = false;
			}
			
			// Quoting
			if (t.startsWith("&gt;")) {
				previewText+="<div style=\"color: #00ff00;\">";
				previewText+=t;
				previewText+="</div>";
				noAction = false;
			}
			// Bullet Points
			if (t.startsWith("* ")) {
				previewText += "&bull " + t.substring(2) + "<br>";
				noAction = false;
			}
			// Tab Management
			// todo
			if (noAction) {
				if (type == CellType.CODE) {
					previewText+="<b>" + t + "</b><br/>";
				} 
				else {
					previewText+=t + "<br/>";
				}
			}
		}
		
		previewText += "</body></html>";
				
		previewArea.setText(previewText);
		
	}

	public void save() {
		// Thought here - what if it lost the active one. need a check if its been lonst and reset to null
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
