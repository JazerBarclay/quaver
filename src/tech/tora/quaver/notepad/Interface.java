package tech.tora.quaver.notepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.json.simple.parser.ParseException;

import tech.tora.quaver.Configuration;
import tech.tora.quaver.Launcher;
import tech.tora.quaver.colour.ColourValue;
import tech.tora.quaver.list.BasicList;
import tech.tora.quaver.list.BasicListNode;
import tech.tora.quaver.log.Logging;
import tech.tora.quaver.notepad.layout.BasicLayout;
import tech.tora.quaver.notepad.layout.Layout;
import tech.tora.quaver.notepad.widget.elements.EditAreaThing;
import tech.tora.quaver.notepad.widget.elements.PreviewAreaThing;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;

public class Interface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Configuration config;

	public boolean newBuild = false;

	public Layout layout;
	public Theme theme;

	public JLabel notebookTitle;

	public BasicList notebooksList;
	public BasicList notesList;
	
	public EditAreaThing editArea;
	public PreviewAreaThing previewArea;
	
	public static Notebook activeNotebook = null;
	public static Note activeNote = null;

	public Interface(Configuration c) {
		if (c == null) {
			c = new Configuration();
			newBuild = true;
			config = new Configuration();
			config.devmode = false;
			config.name = "Default";
			config.theme = "Default";
			config.libraries = new String[]{};
			System.out.println("New Build");
		} else {
			config = c;
			System.out.println("Found Config");
		}
		initLayout();
		buildLayout();
		buildWindow();
		getNotebooks();
	}

	public void initLayout() {

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

		layout = new BasicLayout(theme);
		layout.setWidth(layout.getDefaultWidth());
		layout.setHeight(layout.getDefaultHeight());
		
		notebookTitle = new JLabel();
		notebookTitle.setText("Quaver M" + Launcher.buildID);
		notebookTitle.setFont(new Font("Helvetica", Font.BOLD, 14));
		notebookTitle.setHorizontalAlignment(JLabel.CENTER);
		notebookTitle.setBackground(new Color(200, 200, 0));
		notebookTitle.setPreferredSize(new Dimension(200, 30));
		notebookTitle.setMinimumSize(new Dimension(200, 30));
		notebookTitle.setMaximumSize(new Dimension(200, 30));
		
		notebooksList = new BasicList(200);
		BasicListNode n = new BasicListNode(25, "0", "Notebooks", 
				new Color(230, 230, 230), new Color(200, 200, 200), 
				new Font("Helvetica", Font.BOLD, 14), new Color(0, 0, 0)) {};
		notebooksList.addNode(n);
		
		notesList = new BasicList(300);
		
	}

	public void buildLayout() {

		((BasicLayout) layout).notebooksTop.add(notebookTitle);

		((BasicLayout) layout).notebooksListContainer.add(notebooksList, BorderLayout.CENTER);
		
		((BasicLayout) layout).notesListContainer.add(notesList, BorderLayout.CENTER);
		
	}
	
	public void buildWindow() {
		
		try {
			setIconImage(new ImageIcon(Launcher.class.getResource("/icon1.png")).getImage());
		} catch(Exception e) {
			e.printStackTrace();
		}
		setJMenuBar(layout.getMenu());
		setContentPane(layout.getWrapper());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setTitle("Quaver : " + Launcher.buildID);
		setVisible(true);

		setSize(layout.getWidth(), layout.getHeight());
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("Hello There");
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println("Mew");
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println("Wee");
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println("Deactivated");
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// Handle close operation without saving on a new build
				System.out.println("Bye");
				Launcher.exit(0, "Close Button Request");
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// Do Nothing
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println("Welcome Back");
			}
		});
		
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

	public void getNotebooks() {
		
	}

}
