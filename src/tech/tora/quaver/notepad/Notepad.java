package tech.tora.quaver.notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import org.json.simple.parser.ParseException;

import tech.tora.quaver.Configuration;
import tech.tora.quaver.Launcher;
import tech.tora.quaver.notepad.layout.QuaverLayout;
import tech.tora.quaver.notepad.screen.PreviewScreen;
import tech.tora.quaver.notepad.screen.StandardScreen;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Cell;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.frame.AdvancedFrame;

public class Notepad {

	// Flags
	private static boolean newBuild = true;

	// Running Values
	private static AdvancedFrame window;
	private static Configuration config;
	private static Theme theme;

	private static LinkedHashMap<String, Library> libraries = new LinkedHashMap<String, Library>();

	// Active Values
	private static QuaverLayout activeLayout;

	public Notepad(Configuration configuration) {
		// Check parsed configuration settings
		if (configuration == null) {
			configuration = new Configuration("Default", "Default", true);
			newBuild = true;
		} else {
			newBuild = false;
		}

		// TODO - Update when themes are saving and loading
		theme = Theme.getDefault();

		// Set the local static configuration for future use
		config = configuration;

		// Get data if not a new build
		if (!newBuild) {
			getLibraries();
		}

		// Setup the active layout
		activeLayout = setupInitialLayout();
		// Setup the window for the layout
		window = setupWindow();
		window.setVisible(true);

		// If new build, prompt to create configuration file
		if (newBuild) {
			int createNewConfig = JOptionPane.showConfirmDialog(window, "This is a new build. Would you like to create a configuration file now?"+"\n If not, this runtime will launch in trial mode.", "First Time Setup", JOptionPane.OK_CANCEL_OPTION);
			if (createNewConfig == 0)
				if (createConfigFile()) newBuild = false;
				else System.out.println("Failed to generate");
			else {
				generateTestData();
				JOptionPane.showMessageDialog(window, 
						"This is now running in trial mode. All changes made to any documents will not be saved."+"\nPlease relaunch to run first time setup after trial.", 
						"Trial Mode Activated", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		populateLists();

	}


	/* ------------------------------------------------------ */
	// Initialisation
	/* ------------------------------------------------------ */

	private QuaverLayout setupInitialLayout() {
		StandardScreen layout = new StandardScreen(config, theme) {
			@Override
			public JMenuBar getMenu() {	
				return generateCustomMenuBar();
			}
		};
		return layout;
	}

	private AdvancedFrame setupWindow() {
		AdvancedFrame frame = new AdvancedFrame() {

			/****/
			private static final long serialVersionUID = 1L;

			@Override
			public void openAction() {
				activeLayout.windowOpenAction();
			}

			@Override
			public void minimiseAction() {
				activeLayout.windowMinimiseAction();
			}

			@Override
			public void maximiseAction() {
				activeLayout.windowMaximiseAction();
			}

			@Override
			public void loseFocusAction() {
				activeLayout.windowLoseFocusAction();
			}

			@Override
			public void gainFocusAction() {
				activeLayout.windowGainFocusAction();
			}

			@Override
			public void closeAction() {
				activeLayout.windowCloseAction();
			}
		};

		frame.setSize(activeLayout.getDefaultWidth(), activeLayout.getDefaultHeight());
		frame.setTitle(activeLayout.getTitle());
		frame.updateContent(activeLayout.getWrapper(), false);
		frame.updateMenu(activeLayout.getMenu());
		frame.setLocationRelativeTo(null);

		return frame;
	}


	/* ------------------------------------------------------ */
	// Layout Management
	/* ------------------------------------------------------ */

	private void changeLayout(QuaverLayout layout) {
		
		Library activeLibrary = activeLayout.getActiveLibrary();
		Notebook activeNotebook = activeLayout.getActiveNotebook();
		Note activeNote = activeLayout.getActiveNote();

		activeLayout = layout;

		window.setVisible(false);

		window.setSize(activeLayout.getDefaultWidth(), activeLayout.getDefaultHeight());
		window.setTitle(activeLayout.getTitle());
		window.updateContent(activeLayout.getWrapper(), false);
		window.updateMenu(activeLayout.getMenu());
		window.setLocationRelativeTo(null);

		window.setVisible(true);

		populateLists();
		
		layout.setActiveLibrary(activeLibrary);		
		layout.setActiveNotebook(activeNotebook);		
		layout.setActiveNote(activeNote);

	}

	private JMenuBar generateCustomMenuBar() {
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu open = new JMenu("Open");
		JMenu edit = new JMenu("Edit");
		JMenu view = new JMenu("View");
		JMenu window = new JMenu("Window");

		/*
		 * FILE OPTIONS
		 */

		JMenuItem newLibrary = new JMenuItem("Add New Library");
		newLibrary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		JMenuItem newNotebook = new JMenuItem("Add New Notebook");
		newNotebook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		JMenuItem newNote = new JMenuItem("Add New Note");
		newNote.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		JMenuItem exit = new JMenuItem("Exit");
		newNote.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Launcher.exit(0, "Close requested from top bar");
			}
		});

		file.add(newLibrary);
		file.add(newNotebook);
		file.add(newNote);
		file.addSeparator();
		file.add(exit);

		/*
		 * VIEW OPTIONS
		 */

		JMenuItem defaultView = new JMenuItem("Switch to Default Layout");
		defaultView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeLayout(new StandardScreen(config, theme) {
					@Override
					public JMenuBar getMenu() {	
						return generateCustomMenuBar();
					}
				});
			}
		});
		JMenuItem compactView = new JMenuItem("Switch to Compact Layout");
		compactView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Currently Unavailabe");
			}
		});

		JMenuItem previewView = new JMenuItem("Switch to Preview Layout");
		previewView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeLayout(new PreviewScreen(config, theme) {
					@Override
					public JMenuBar getMenu() {	
						return generateCustomMenuBar();
					}
				});
			}
		});

		if (!(activeLayout instanceof StandardScreen)) view.add(defaultView);
		view.add(compactView);
		if (!(activeLayout instanceof PreviewScreen)) view.add(previewView);


		// Add all top level items
		menu.add(file);
		menu.add(open);
		menu.add(edit);
		menu.add(view);
		menu.add(window);
		return menu;
	}

	private void populateLists() {
		for(String key : libraries.keySet()) {
			activeLayout.addLibrary(libraries.get(key));
			for (Notebook nb : libraries.get(key).getNotebookAsArray()) {
				activeLayout.addNotebook(nb);
			}
		}

//		if (activeLayout.getActiveNotebook() != null) {
//
//		}
	}

	/* ------------------------------------------------------ */
	// Trial Data
	/* ------------------------------------------------------ */

	private void generateTestData() {
		Library lib = new Library(FileSystemView.getFileSystemView().getHomeDirectory().toString(), "Default");

		Notebook nb1 = Notebook.newNotebook("Wiki Guide", lib);
		Notebook nb2 = Notebook.newNotebook("Sketchbook", lib);

		Note note1 = Note.newNote("1. Getting Started", nb1);
		Note note2 = Note.newNote("2. Markdown Guilde", nb1);
		Note note3 = Note.newNote("3. Credit", nb1);

		Note note4 = Note.newNote("Doodle Note", nb2);

		nb1.addNote(note1);
		nb1.addNote(note2);
		nb1.addNote(note3);
		nb2.addNote(note4);

		lib.addNotebook(nb1);
		lib.addNotebook(nb2);

		libraries.put(lib.getPath()+Launcher.pathSeparator+lib.getName(), lib);
	}


	/* ------------------------------------------------------ */
	// Filesystem management
	/* ------------------------------------------------------ */

	private boolean createConfigFile() {
		try {
			Configuration.writeConfigJSON(config);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to create the configuration\n" + e, "Failed configuration", JOptionPane.ERROR_MESSAGE);
			Launcher.exit(1, "Failed to create configuration file");
			return false;
		}
	}

	private void getLibraries() {
		if (config.getLibraries().length < 1) return;
		Library aLib;
		Notebook aNb = null;
		Note aN;
		for (String lib : config.getLibraries()) {
			File library1 = new File(lib);
			if (library1.exists() && library1.getAbsolutePath().endsWith(Library.getExtension())) {
				aLib = new Library(library1.getParentFile().getAbsolutePath(), library1.getName().substring(0, library1.getName().length() - Library.getExtension().length()));
//				System.out.println(aLib.getPath() + " : " + aLib.getName());
				libraries.put(aLib.getPath() + Launcher.pathSeparator + aLib.getName(), aLib);
				for (File libF : library1.listFiles()) {
					if (Notebook.isNotebook(libF)) {
						aNb = readNotebook(libF.getAbsolutePath());
						if (aNb != null) {
							aLib.addNotebook(aNb);
						}
						for (File nbF : libF.listFiles()) {
							if (Note.isNote(nbF)) {	
								aN = readNote(nbF.getAbsolutePath());
								if (aN != null) {
									aNb.addNote(aN);
								}
							} else {
//								System.out.println(nbF.getAbsolutePath() + " is not a vaild note");
							}
						}
					} else {
//						System.out.println(libF.getAbsolutePath() + " is not a vaild notebook");
					}
				}
			} else {
//				System.out.println(library1.getAbsolutePath() + " is not a valid library");
			}
		}
		
		
		
	}
	
	private Notebook readNotebook(String path) {
		Notebook nb = null;
		try {
			nb = Notebook.readJSON(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		if (nb != null) {
			if (
					nb.getUUID() != null && !nb.getUUID().equals("") && 
					nb.getName() != null && !nb.getName().equals("") && 
					nb.getPath() != null && !nb.getPath().equals("")
			) return nb;
		}
		
		return null;
	}
	
	
	private Note readNote(String path) {
		Note n = null;
		
		try {
			Note n1 = Note.readContentsJSON(path);
			Note n2 = Note.readMetaJSON(path);
			
			n = new Note(n2.getUUID(), n2.getTitle(), n2.getCreatedAt(), n2.getUpdatedAt(), path, n2.getTags());
			for (Cell c : n1.getCells()) {
				n.addCell(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return n;
	}


}
