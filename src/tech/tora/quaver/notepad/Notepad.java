package tech.tora.quaver.notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedHashMap;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import tech.tora.quaver.Configuration;
import tech.tora.quaver.Launcher;
import tech.tora.quaver.notepad.layout.QuaverLayout;
import tech.tora.quaver.notepad.screen.PreviewScreen;
import tech.tora.quaver.notepad.screen.StandardScreen;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Library;
import tech.tora.quaver.types.Notebook;
import tech.tora.tools.swing.colour.ColourValue;
import tech.tora.tools.swing.frame.AdvancedFrame;
import tech.tora.tools.system.CustomUUID;

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
		if (configuration == null) {
			configuration = new Configuration("Default", "Default", true);
			newBuild = true;
		} else {
			newBuild = false;
		}
		
		theme = getDefaultTheme();
		config = configuration;
		
		activeLayout = setupInitialLayout();
		window = setupWindow();
		window.setVisible(true);
		
		if (newBuild) {
			int createNewConfig = JOptionPane.showConfirmDialog(window, "This is a new build. Would you like to create a configuration file now?"+"\n If not, this runtime will launch in trial mode.", "First Time Setup", JOptionPane.OK_CANCEL_OPTION);
			if (createNewConfig == 0)
				if (createConfigFile()) newBuild = false;
				else System.out.println("Failed to generate");
			else {
				JOptionPane.showMessageDialog(window, 
						"This is now running in trial mode. All changes made to any documents will not be saved."+"\nPlease relaunch to run first time setup after trial.", 
						"Trial Mode Activated", JOptionPane.INFORMATION_MESSAGE);
				generateTestData();
			}
		}
		
		
		
	}
	
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
	
	private void changeLayout(QuaverLayout layout) {
		layout.setActiveLibrary(activeLayout.getActiveLibrary());		
		layout.setActiveNotebook(activeLayout.getActiveNotebook());		
		layout.setActiveNote(activeLayout.getActiveNote());

		activeLayout = layout;
		
		window.setVisible(false);
		
		window.setSize(activeLayout.getDefaultWidth(), activeLayout.getDefaultHeight());
		window.setTitle(activeLayout.getTitle());
		window.updateContent(activeLayout.getWrapper(), false);
		window.updateMenu(activeLayout.getMenu());
		window.setLocationRelativeTo(null);
		
		window.setVisible(true);
		
	}
	
	private JMenuBar generateCustomMenuBar() {
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
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
		
		file.add(newLibrary);
		file.add(newNotebook);
		file.add(newNote);

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
		
		menu.add(file);
		menu.add(edit);
		menu.add(view);
		menu.add(window);
		return menu;
	}
	
	private void generateTestData() {
		
		Library lib = new Library("/", "Default");
		
		activeLayout.addLibrary(new Library("/", "Default"));
		activeLayout.addNotebook(new Notebook(CustomUUID.generateTimestampUUID("Wiki Guide"), "Wiki Guide", "/Hello/"));
		activeLayout.addNotebook(new Notebook(CustomUUID.generateTimestampUUID("Sketchbook"), "Sketchbook", "/Hello/"));
		
		
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
