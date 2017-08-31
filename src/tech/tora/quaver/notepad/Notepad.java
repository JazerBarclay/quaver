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

import tech.tora.quaver.Build;
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

	public static boolean newBuild;

	private static AdvancedFrame window;
	private static Configuration config;
	private static Build build;
	private static Theme theme;

	private static LinkedHashMap<String, Library> libraries = 
			new LinkedHashMap<String, Library>();

	private static QuaverLayout activeLayout;

	public Notepad(Configuration configuration, Build build) {
		
		if (configuration == null) {
			configuration = new Configuration("Default", "Default", true);
			newBuild = true;
		} else newBuild = false;

		this.config = configuration;
		this.build = build;
		this.theme = Theme.getDefault(); // TODO - Update when themes are saving and loading

		if (!newBuild) getLibraries();

		activeLayout = setupInitialLayout();
		window = setupWindow();
		window.setVisible(true);
		
		// If new build, prompt to create configuration file
		if (newBuild) {
			int createNewConfig = 
					JOptionPane.showConfirmDialog(
							window, 
							"This is a new build. Would you like to create a configuration file now?"
							+"\n If not, this runtime will launch in trial mode.", 
							"First Time Setup", 
							JOptionPane.OK_CANCEL_OPTION);
			if (createNewConfig == 0)
				if (createConfigFile()) newBuild = false;
				else System.out.println("Failed to generate");
			else {
				generateTestData();
				JOptionPane.showMessageDialog(
						window, 
						"This is now running in trial mode. All changes made to any documents will not be saved."
						+"\nPlease relaunch to run first time setup after trial.", 
						"Trial Mode Activated", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		populateLists();

	}
	
	private boolean createConfigFile() {
		try {
			Configuration.writeConfigJSON(config);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(
					null, 
					"Failed to create the configuration\n" + e, 
					"Failed configuration", 
					JOptionPane.ERROR_MESSAGE);
			Launcher.exit(1, "Failed to create configuration file");
			return false;
		}
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
	

	// Data
	
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

		libraries.put(lib.getPath()+File.separator+lib.getName(), lib);
	}
	
	private void populateLists() {
		for(String key : libraries.keySet()) {
			activeLayout.addLibraryToList(libraries.get(key));
			for (Notebook nb : libraries.get(key).getNotebookAsArray()) {
				activeLayout.addNotebookToList(nb);
			}
		}
	}
	
	private void getLibraries() {
		if (config.getLibraries().length < 1) return;
		Library localLib = null;
		File libFile;
		Notebook localNotebook = null;
		Note localNote = null;
		for (String lib : config.getLibraries()) {
			libFile = new File(lib);
			localLib = readLibrary(libFile);
			if (localLib != null) {
				libraries.put(localLib.getPath() + File.separator + localLib.getName(), localLib);
				for (File nbFile : libFile.listFiles()) {
					if (Notebook.isNotebook(nbFile)) {
						localNotebook = readNotebook(nbFile.getAbsolutePath());
						if (localNotebook != null) {
							localLib.addNotebook(localNotebook);
							for (File nFile : nbFile.listFiles()) {
								if (Note.isNote(nFile)) {	
									localNote = readNote(nFile.getAbsolutePath());
									if (localNote != null) localNotebook.addNote(localNote);
								}
							}
						}
					}
				}
			}
		}
	}

	
	private Library readLibrary(File libDirectory) {
		if (libDirectory.exists() && libDirectory.getAbsolutePath().endsWith(Library.getExtension())) {
			return new Library(
					libDirectory.getParentFile().getAbsolutePath(), 
					libDirectory.getName().substring(
							0, 
							libDirectory.getName().length() - Library.getExtension().length()));
		}
		return null;
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
	
	
	// Layout Management

	private QuaverLayout setupInitialLayout() {
		StandardScreen layout = new StandardScreen(
				theme, build.name(), build.release(), build.major(), build.minor()) {
			@Override
			public JMenuBar getMenu() {	
				return generateCustomMenuBar();
			}
		};
		return layout;
	}

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
		exit.addActionListener(new ActionListener() {
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

		JMenuItem defaultView = new JMenuItem("Switch to Default Layout");
		defaultView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeLayout(new StandardScreen(theme, build.name(), build.release(), build.major(), build.minor()) {
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
				changeLayout(new PreviewScreen(theme, build.name(), build.release(), build.major(), build.minor()) {
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

		JMenuItem alwaysOnTop = new JMenuItem("Set Always On Top");
		alwaysOnTop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Notepad.window.setAlwaysOnTop(!Notepad.window.isAlwaysOnTop());
				alwaysOnTop.setText((Notepad.window.isAlwaysOnTop() ? "* " : "") + "Set Always On Top");
			}
		});
		
		window.add(alwaysOnTop);

		menu.add(file);
		menu.add(open);
		menu.add(edit);
		menu.add(view);
		menu.add(window);
		return menu;
	}

}
