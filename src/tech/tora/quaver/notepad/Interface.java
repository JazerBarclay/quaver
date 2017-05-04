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
import tech.tora.quaver.notepad.widget.NotebookNode;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;

public class Interface extends JFrame {

	/**
	 * Serial UUID
	 */
	private static final long serialVersionUID = 1L;

	public LayoutBuilder layout;
	public EditAreaThing editArea;

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
		
		if (activeNotebook != null) layout.notesTopTitle.setText(activeNotebook.name);
		
	}
	
	public void updatePreview() {

	}

	public void save() {

	}

}
