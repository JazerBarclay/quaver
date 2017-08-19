package tech.tora.quaver.notepad;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import tech.tora.quaver.Configuration;
import tech.tora.quaver.Launcher;
import tech.tora.quaver.notepad.layout.StandardLayout;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.colour.ColourValue;
import tech.tora.tools.swing.frame.AdvancedFrame;
import tech.tora.tools.swing.layout.Layout;
import tech.tora.tools.swing.list.ClickListener;
import tech.tora.tools.system.log.Logging;

/**
 * Contains all data management and checks. Configuration taken set to 
 * @author Nythril
 *
 */
public class Notepad {

	private static AdvancedFrame window;
	private static Configuration config;
	private static Layout layout;

	private LinkedHashMap<String, Library> libraryArray = new LinkedHashMap<>();
	
	private Theme theme;
	
	private boolean newBuild = false;
	
	public Notepad(Configuration c) {
		setupConfiguration(c);
		setupFrame();
		window.setVisible(true);
		
		if (newBuild) {
			int createNewConfig = JOptionPane.showConfirmDialog(window, "This is a new build. Would you like to create a configuration file now?", "First Time Setup", JOptionPane.OK_CANCEL_OPTION);
			if (createNewConfig == 0)
				if (createConfigFile()) newBuild = false;
				else System.out.println("Failed to generate");
			else {
				JOptionPane.showMessageDialog(window, 
						"This is now running in trial mode. All changes made to any documents will not be saved. Please relaunch to run first time setup after trial.", 
						"Trial Mode Activated", JOptionPane.INFORMATION_MESSAGE);
				generateTestDataset();
				populateLists();
				
			}
			
		}
		
	}
	
	/* ------------------------------------------------------ */
	// Initialisation
	/* ------------------------------------------------------ */
	
	private void setupConfiguration(Configuration c) {
		if (c == null) {
			c = new Configuration("Default", "Default", true);
			newBuild = true;
			config = c;
			System.out.println("New Build");
		} else {
			config = c;
			System.out.println("Found Config");
		}
		
		if (config.getTheme() == null) theme = getDefaultTheme();
		else {
			if (config.getTheme().equals("Default")) theme = getDefaultTheme();
			else {
				try {
					Theme.readThemeJSON(config.getTheme());
				} catch (IOException e) {
					Logging.errorMessage(1, null, "Read Theme Failed", "Cannot load theme from file", e);
				} catch (ParseException e) {
					Logging.errorMessage(1, null, "Read Theme Failed", "Cannot load theme from file", e);
				}
			}
		}
	} // setupConfiguration(Configuration c);
	
	/**
	 * Initialise the frame for first time launch
	 */
	private void setupFrame() {
		window = new AdvancedFrame() {
			
			/** * **/
			private static final long serialVersionUID = 1L;

			@Override
			public void openAction() {
				layout.windowOpenAction();
			}
			
			@Override
			public void minimiseAction() {
				layout.windowMinimiseAction();
			}
			
			@Override
			public void maximiseAction() {
				layout.windowMaximiseAction();
			}
			
			@Override
			public void loseFocusAction() {
				layout.windowLoseFocusAction();
			}
			
			@Override
			public void gainFocusAction() {
				layout.windowGainFocusAction();
			}
			
			@Override
			public void closeAction() {
				layout.windowCloseAction();
			}
		};
		
		setupLayout();
		
		window.setSize(layout.getDefaultWidth(), layout.getDefaultHeight());
		window.setTitle(layout.getTitle());
		window.updateContent(layout.getWrapper(), false);
		window.updateMenu(layout.getMenu());
		window.setLocationRelativeTo(null);
		
	} // setupFrame();
	
	
	private void setupLayout() {
		layout = new StandardLayout(theme) {
			
			@Override
			public JMenuBar getMenu() {
				return constructMenuBar();
			}
			
		};
		
		for (String key : libraryArray.keySet()) {
			((StandardLayout) layout).addLibraryNodeToList(libraryArray.get(key));
			for (Notebook n : libraryArray.get(key).getNotebookAsArray()) {
				((StandardLayout) layout).addNotebookNodeToList(n, new ClickListener() {
					@Override
					public void onClick() {
						System.out.println("Test External");
					}
				});
			}
		}
		
	} // setupLayout();

	private void generateTestDataset() {
		// Get from 
		Library sketch = new Library("/users/jazer", "Default");
		Library privLib = new Library("/users/jazer", "Private");
		Library pubLib = new Library("/users/jazer", "Work");
		
		Notebook nb1 = Notebook.newNotebook("Exercise", privLib);
		Notebook nb2 = Notebook.newNotebook("Music Playlist", privLib);
		Notebook nb3 = Notebook.newNotebook("Java", pubLib);
		Notebook nb4 = Notebook.newNotebook("Webdesign", pubLib);
		Notebook nb5 = Notebook.newNotebook("TODO", pubLib);
		Notebook nb6 = Notebook.newNotebook("Quaver Wiki", sketch);
		Notebook nb7 = Notebook.newNotebook("Sketchbook", sketch);
		
		Note n1 = Note.newNote("Running Routes", nb1);
		Note n2 = Note.newNote("Weights", nb1);
		Note n3 = Note.newNote("Favourites", nb2);
		Note n4 = Note.newNote("JFrame", nb3);
		Note n5 = Note.newNote("JPanel", nb3);
		Note n6 = Note.newNote("Exit Codes", nb3);
		Note n7 = Note.newNote("HTML Core", nb4);
		Note n8 = Note.newNote("Quaver", nb5);
		Note n9 = Note.newNote("Getting Started", nb6);
		Note n10 = Note.newNote("Note 1", nb7);

		nb1.addNote(n1);
		nb1.addNote(n2);
		nb2.addNote(n3);
		nb3.addNote(n4);
		nb3.addNote(n5);
		nb3.addNote(n6);
		nb4.addNote(n7);
		nb5.addNote(n8);
		nb6.addNote(n9);
		nb7.addNote(n10);
		
		privLib.addNotebook(nb1);
		privLib.addNotebook(nb2);
		pubLib.addNotebook(nb3);
		pubLib.addNotebook(nb4);
		pubLib.addNotebook(nb5);
		sketch.addNotebook(nb6);

		libraryArray.put(sketch.getName(), sketch);
		libraryArray.put(privLib.getName(), privLib);
		libraryArray.put(pubLib.getName(), pubLib);
		
		
		
	}
	
	private void populateLists() {
		
		for (String key : libraryArray.keySet()) {
			((StandardLayout) layout).addLibraryNodeToList(libraryArray.get(key));
			for (Notebook n : libraryArray.get(key).getNotebookAsArray()) {
				((StandardLayout) layout).addNotebookNodeToList(n, new ClickListener() {
					
					@Override
					public void onClick() {
						System.out.println("Test External");
					}
				});
			}
		}
		
		window.revalidate();
		
	}
	
	/* ------------------------------------------------------ */
	// Frame Management
	/* ------------------------------------------------------ */

	private JMenuBar constructMenuBar() {
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu view = new JMenu("View");
		menu.add(file);
		menu.add(view);
		return menu;
	}
	
	public void switchLayout(Layout l) {
		layout = l;
		window.updateTitle(l.getTitle(), false);
		window.updateFrameSize(l.getDefaultWidth(), layout.getDefaultHeight(), true);
		window.updateMenu(l.getMenu(), false);
		window.updateContent(l.getWrapper());
	}

	/* ------------------------------------------------------ */
	// File System Management
	/* ------------------------------------------------------ */
	
	/**
	 * Touches all library locations and returns an array of failed library links
	 */
	public void touchLibraries() {
		
		
	}
	
	/**
	 * Get all libraries 
	 */
	public void getLibraries() {
		
	}
	
	public void getNotebooks() {
		
		for (String lib : config.getLibraries()) {
			for (File libF : new File(lib).listFiles()) {
				if (isNotebook(libF)) {
					if (config.isDevmode()) System.out.println("Notebook Found: " + libF.getName());
					for (File nbF : libF.listFiles()) {
						if (isNote(nbF)) {
							
						} // is note check
					} // notebook dir contents loop
				} // is notebook check
			} // library contents loop
		}
	
	}
	
	private boolean isNotebook(File f) {
		if (f.isDirectory() && 
				f.getAbsolutePath().endsWith(".qvnotebook") && 
				new File(f.getAbsolutePath()+Launcher.pathSeparator + "meta.json").exists()) {
			return true;
		}
		return false;
	}
	
	private boolean isNote(File f) {
		if (f.isDirectory() && 
				f.getAbsolutePath().endsWith(".qvnote") && 
				new File(f.getAbsolutePath()+Launcher.pathSeparator+"meta.json").exists()) {
			return true;
		}
		return false;
	}
	
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

	/* ------------------------------------------------------ */
	// Defaults
	/* ------------------------------------------------------ */
	
	public Theme getDefaultTheme() {
		Theme defaultTheme = new Theme();
		defaultTheme.themeName = "Default";
		defaultTheme.fontColour = new ColourValue(40, 40, 40);
		defaultTheme.wrapperFillColour = new ColourValue(0, 0, 0);
		defaultTheme.notebookFillColour = new ColourValue(230, 230, 230);
		defaultTheme.notebookHoverColour = new ColourValue(210, 210, 210);
		defaultTheme.noteFillColour = new ColourValue(230, 230, 230);
		defaultTheme.noteHoverColour = new ColourValue(210, 210, 210);
		defaultTheme.editFontColour = new ColourValue(40, 40, 40);
		defaultTheme.editFillColour = new ColourValue(230, 230, 230);
		defaultTheme.previewFontColour = new ColourValue(242, 242, 242);
		defaultTheme.previewFillColour = new ColourValue(57, 63, 75);
		defaultTheme.borderColour = new ColourValue(140, 140, 140);
		return defaultTheme;
	}
	
}
