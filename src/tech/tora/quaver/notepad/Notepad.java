package tech.tora.quaver.notepad;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

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
import tech.tora.tools.system.log.Logging;

public class Notepad {

	public static AdvancedFrame window;
	public static Configuration config;
	public static Layout layout;

	public LinkedHashMap<String, Library> libraryArray = new LinkedHashMap<>();
	
	public Theme theme;
	
	public boolean newBuild = false;
	
	public Notepad(Configuration c) {
		setupConfiguration(c);
		setupFrame();
		window.setVisible(true);
	}
	
	/* ------------------------------------------------------ */
	// Initialisation
	/* ------------------------------------------------------ */
	
	private void setupConfiguration(Configuration c) {
		if (c == null) {
			c = new Configuration();
			newBuild = true;
			config = new Configuration("Default", "Default", false);
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
		layout = new StandardLayout(window, theme) {
			
			@Override
			public void constructTopBar(JMenuBar menu) {
				constructMenuBar(menu);
			}
			
		};
		layout.setTitle("Test Quaver");
		
	} // setupLayout();

	
	/* ------------------------------------------------------ */
	// Frame Management
	/* ------------------------------------------------------ */

	private void constructMenuBar(JMenuBar menu) {
		JMenu file = new JMenu("File");
		menu.add(file);
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
		// TODO - Add a search for an ignore file under res/.libignore
		
	}
	
	/**
	 * Get all libraries 
	 */
	public void getLibraries() {
		
	}
	
	public void getNotebooks() {
		int notebookCount = 0;
		
		for (String lib : config.getLibraries()) {
			// Check contents of the library location
			File[] libContents = new File(lib).listFiles();
			for (File libF : libContents) {
				if (isNotebook(libF)) {
					System.out.println("Notebook Found: " + libF.getName());
					File[] notebookContents = libF.listFiles();
					for (File nbF : notebookContents) {
						if (nbF.isDirectory() && nbF.getAbsolutePath().endsWith(".qvnote") && new File(nbF.getAbsolutePath()+Launcher.pathSeparator+"meta.json").exists()) {
							notebookCount++;
						}
					}
				}
			}
		}
		
		System.out.println(notebookCount);
	
	}
	
	private boolean isNotebook(File f) {
		if (f.isDirectory() && 
				f.getAbsolutePath().endsWith(".qvnotebook") && 
				new File(f.getAbsolutePath()+Launcher.pathSeparator + "meta.json").exists()) {
			return true;
		}
		return false;
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
