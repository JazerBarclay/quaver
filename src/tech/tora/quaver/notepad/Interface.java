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
import tech.tora.quaver.notepad.widget.AddButton;
import tech.tora.quaver.notepad.widget.EditAreaThing;
import tech.tora.quaver.notepad.widget.LayoutBuilder;
import tech.tora.quaver.notepad.widget.NoteNode;
import tech.tora.quaver.notepad.widget.NotebookNode;
import tech.tora.quaver.structures.NotebookManager;
import tech.tora.quaver.structures.NotebookMeta;
import tech.tora.quaver.types.Notebook;

public class Interface extends JFrame {

	/**
	 * Serial UUID
	 */
	private static final long serialVersionUID = 1L;

	public LayoutBuilder layout;
	public EditAreaThing editArea;

	public NotebookNode[] notebooksList = new NotebookNode[] {};
	public NoteNode[] noteList = new NoteNode[] {};

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
				System.out.println("Create New Note for [NOTEBOOK HERE]");
			}
		}, BorderLayout.WEST);

		layout.getBookBotPane().add(new AddButton() {
			private static final long serialVersionUID = 1L;

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Add Button Clicked");
				generateNewNotebook();
			}
		}, BorderLayout.WEST);


		editArea = new EditAreaThing() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSave() {
				System.out.println("Saving");
			}

			@Override
			public void onChange() {
				System.out.println("Changed");
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
	
	private void getNotebooks() {

		for (String notebook : Launcher.config.libraries) {
			File dir = new File(notebook);
			if (dir.isDirectory() && dir.getAbsolutePath().endsWith(".qvnotebook")) {
				if (new File(dir.getAbsolutePath()+"/meta.json").exists()) {
					// Get notebook config
					NotebookMeta notebookMeta;
					try {
						notebookMeta = NotebookManager.readConfigJSON(dir.getAbsolutePath());
						System.out.println("NotebookMeta: " + notebookMeta.name + "_" + notebookMeta.uuid);
						int noteCount = 0;
						File[] notes = dir.listFiles();
						for (File n : notes) {
							File content = new File(n.getAbsolutePath()+"/content.json");
							File meta = new File(n.getAbsolutePath()+"/meta.json");
							if (!content.exists() || !meta.exists()) continue;
							else noteCount++;
						}
						System.out.println("Notes Count: " + noteCount);
						NotebookNode[] tmp = new NotebookNode[notebooksList.length + 1];
						for (int i = 0; i < notebooksList.length; i++) tmp[i] = notebooksList[i];
						tmp[notebooksList.length] = new NotebookNode(layout.notebooksList, "", notebookMeta.name, "" + noteCount, notebookMeta);
						notebooksList = tmp;
					} catch (IOException | ParseException e) {
						e.printStackTrace();
					}
				} else {
					// For some reason that notebook is not there
					// Now is the time to panic as file has been tampered with outside the application!
				}
				
			}

		}
		for (NotebookNode n : notebooksList) layout.notebooksList.addNode(n);
		
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


	public void updatePreview() {

	}

	public void save() {

	}

}
