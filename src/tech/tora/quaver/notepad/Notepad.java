package tech.tora.quaver.notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.json.simple.parser.ParseException;

import tech.tora.quaver.Configuration;
import tech.tora.quaver.Launcher;
import tech.tora.quaver.colour.ColourValue;
import tech.tora.quaver.log.Logging;
import tech.tora.quaver.notepad.layout.Layout;
import tech.tora.quaver.notepad.layout.TestLayout;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;

public class Notepad {

	public static JFrame window;
	public static Configuration config;
	public static Layout layout;

	public LinkedHashMap<Integer, String> libraryArray;
	public LinkedHashMap<String, Notebook> notebookArray;
	public LinkedHashMap<String, Note> noteArray;
	
	
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu viewMenu;
	
	// File
	private JMenuItem newMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem updateMenuItem;
	private JMenuItem exitMenuItem;
	
	// Edit
	private JMenuItem insertLinkMenuItem;
	
	private JMenuItem defaultSwitch;
	private JMenuItem compactSwitch;
	private JMenuItem previewSwitch;
	
	
	public Theme theme;
	
	public boolean newBuild = false;
	
	public Notepad(Configuration c) {
		if (c == null) {
			c = new Configuration();
			newBuild = true;
			config = new Configuration();
			config.devmode = true;
			config.name = "Default";
			config.theme = "Default";
			config.libraries = new String[]{};
			System.out.println("New Build");
		} else {
			config = c;
			System.out.println("Found Config");
		}
		
		if (config.theme == null) theme = getDefaultTheme();
		else {
			if (config.theme.equals("Default")) theme = getDefaultTheme();
			else {
				try {
					Theme.readThemeJSON(config.theme);
				} catch (IOException e) {
					Logging.errorMessage(1, null, "Read Theme Failed", "Cannot load theme from file", e);
				} catch (ParseException e) {
					Logging.errorMessage(1, null, "Read Theme Failed", "Cannot load theme from file", e);
				}
			}
		}
		
//		layout = new TestLayout(theme) {
//
//			@Override
//			public void buildTopBar(JMenuBar menu) {
//
//				fileMenu = new JMenu("File");
//				
//				newMenuItem = new JMenuItem("New Notebook");
//				newMenuItem.addActionListener(new ActionListener() {
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						
//					}
//				});
//				
//				updateMenuItem = new JMenuItem("New Note");
//				updateMenuItem.addActionListener(new ActionListener() {
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						
//					}
//				});
//				
//				saveMenuItem = new JMenuItem("Save All");
//				saveMenuItem.addActionListener(new ActionListener() {
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						
//					}
//				});
//				
//				exitMenuItem = new JMenuItem("Exit");
//				exitMenuItem.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						Launcher.exit(0, "Shutdown request from top menu");
//					}
//				});
//				
//				fileMenu.add(newMenuItem);
//				fileMenu.add(updateMenuItem);
//				fileMenu.add(saveMenuItem);
//				fileMenu.add(exitMenuItem);
//				
//
//				editMenu = new JMenu("Edit");
//				insertLinkMenuItem = new JMenuItem("Insert Link");
//				
//				editMenu.add(insertLinkMenuItem);
//				
//
//				viewMenu = new JMenu("View");
//				defaultSwitch = new JMenuItem("Default Mode");
//				defaultSwitch.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
////						Notepad.layoutManager.buildBasicLayout();
//					}
//				});
//				compactSwitch = new JMenuItem("Compact Mode");
//				compactSwitch.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						
//					}
//				});
//				
//				previewSwitch = new JMenuItem("Preview Mode");
//				previewSwitch.addActionListener(new ActionListener() {
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
////					    Notepad.layoutManager.buildPreviewLayout();
//					}
//				});
//
//				viewMenu.add(defaultSwitch);
//				viewMenu.add(compactSwitch);
//				viewMenu.add(previewSwitch);
//				
//				// Add Top Bar
//				getMenu().add(fileMenu);
//				getMenu().add(editMenu);
//				getMenu().add(viewMenu);
//				
//			
//			}
//			
//		};
//		layout.buildWindow("Title", "/icon1.png", false);
//		layout.showWindow(true);
		
		LayoutBuilder layoutManager = new LayoutBuilder(this);
		layoutManager.buildBasicLayout();
		
	}
	
	public void getLibraries() {
		int libCount = 0;
		for (String lib : config.libraries) {
			File f = new File(lib);
			if (!f.isDirectory()) {
				System.err.println("Library " + lib + " is not a directory");
			} else if (!f.getAbsolutePath().endsWith(".qvlibrary")) {
				System.err.println("Library " + lib + " ");
			}
			libraryArray.put(libCount, lib);
		}
	}
	
	public void getNotebooks() {
		int notebookCount = 0;
		
		for (String lib : config.libraries) {
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
