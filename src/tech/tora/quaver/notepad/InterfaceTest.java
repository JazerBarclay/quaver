package tech.tora.quaver.notepad;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.json.simple.parser.ParseException;

import tech.tora.quaver.Configuration;
import tech.tora.quaver.Launcher;
import tech.tora.quaver.colour.ColourValue;
import tech.tora.quaver.list.QuickListNodeTest;
import tech.tora.quaver.list.QuickListTest;
import tech.tora.quaver.list.SelectionList;
import tech.tora.quaver.log.Logging;
import tech.tora.quaver.notepad.layout.BasicLayout;
import tech.tora.quaver.notepad.layout.Layout;
import tech.tora.quaver.notepad.widget.EditAreaThing;
import tech.tora.quaver.notepad.widget.PreviewAreaThing;
import tech.tora.quaver.theme.Theme;
import tech.tora.quaver.types.Note;
import tech.tora.quaver.types.Notebook;

public class InterfaceTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Configuration config;

	public boolean newBuild = false;

	public Layout layout;
	public Theme theme;

	public JLabel notebookTitle;
	
	public JScrollPane notebookScollPane;
//	public QuickListTest notebooksList;
//	public SelectionList notesList;
	
	public EditAreaThing editArea;
	public PreviewAreaThing previewArea;
	
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
		notebookTitle.setText("Notebooks");
		notebookTitle.setFont(new Font("Helvetica", Font.BOLD, 14));
		notebookTitle.setHorizontalAlignment(JLabel.CENTER);
		notebookTitle.setPreferredSize(new Dimension(300, 30));
		
		
	}

	public void buildLayout() {
		
		((BasicLayout) layout).notebooksTop.add(notebookTitle, BorderLayout.NORTH);

		
//		notebookScollPane = new JScrollPane();
		notebookScollPane = new JScrollPane(new JPanel(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		notebookScollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		notebookScollPane.getVerticalScrollBar().setUnitIncrement(20);
		notebookScollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		((BasicLayout) layout).notebooksListContainer.add(notebookScollPane);
		
	}
	
	public void buildWindow() {
		try {
			setIconImage(new ImageIcon(Launcher.class.getResource("/icon1.png")).getImage());
		} catch(Exception e) {
			e.printStackTrace();
		}
		setJMenuBar(layout.getMenu());
		setContentPane(layout.getWrapper());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setTitle("Quaver : " + Launcher.buildID);
		setVisible(true);

		setSize(layout.getWidth(), layout.getHeight());
		setLocationRelativeTo(null);
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
