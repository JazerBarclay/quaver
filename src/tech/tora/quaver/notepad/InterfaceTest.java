package tech.tora.quaver.notepad;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.json.simple.parser.ParseException;

import tech.tora.quaver.Configuration;
import tech.tora.quaver.Launcher;
import tech.tora.quaver.Theme;
import tech.tora.quaver.colour.ColourValue;
import tech.tora.quaver.notepad.layout.LayoutConstruct;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;

public class InterfaceTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Configuration config;

	public boolean newBuild = false;

	public LayoutConstruct layout;
	public static Theme theme;

	public static Notebook activeNotebook = null;
	public static Note activeNote = null;

	public InterfaceTest(Configuration c) {
		if (c == null) {
			c = new Configuration();
			newBuild = true;
			config = new Configuration();
			config.devmode = false;
			config.name = "Default";
			config.theme = "Default";
			config.libraries = new String[]{};
		} else {
			config = c;
		}
		initLayout();
		buildWindow();
		//		getNotebooks();
	}

	public void initLayout() {

		if (config.theme == null) theme = getDefaultTheme();
		else {
			if (config.theme.equals("Default")) theme = getDefaultTheme();
			else {
				try {
					Theme.readThemeJSON(config.theme);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

		layout = new LayoutConstruct();
	}

	public void buildWindow() {
		try {
			setIconImage(new ImageIcon(Launcher.class.getResource("/icon1.png")).getImage());
		} catch(Exception e) {
			e.printStackTrace();
		}
		setContentPane(layout.getWrapperPane());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setTitle("Quaver : " + Launcher.buildID);
		setVisible(true);

		setSize(1600, 800);
		setLocationRelativeTo(null);
	}

	public Theme getDefaultTheme() {
		Theme defaultTheme = new Theme();
		defaultTheme.themeName = "Default";
		defaultTheme.fontColour = new ColourValue(40, 40, 40);
		defaultTheme.wrapperFillColour = new ColourValue(0,0,0);
		defaultTheme.notebookFillColour = new ColourValue(230, 230, 230);
		defaultTheme.notebookHoverColour = new ColourValue(210, 210, 210);
		defaultTheme.noteFillColour = new ColourValue(230, 230, 1230);
		defaultTheme.noteHoverColour = new ColourValue(210, 210, 210);
		defaultTheme.editFontColour = new ColourValue(40, 40, 40);
		defaultTheme.editFillColour = new ColourValue(230, 230, 230);
		defaultTheme.previewFontColour = new ColourValue(242, 242, 242);
		defaultTheme.previewFillColour = new ColourValue(57,63,75);
		defaultTheme.borderColour = new ColourValue(140, 140, 140);
		return defaultTheme;
	}

	public void getNotebooks() {

	}

}
