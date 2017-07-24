package tech.tora.quaver.notepad;

import tech.tora.quaver.notepad.layout.Layout;
import tech.tora.quaver.Launcher;
import tech.tora.quaver.list.BasicClickListNode;
import tech.tora.quaver.list.BasicList;
import tech.tora.quaver.list.BasicListNode;
import tech.tora.quaver.notepad.layout.BasicLayout;
import tech.tora.quaver.notepad.layout.CompactLayout;
import tech.tora.quaver.notepad.layout.PreviewLayout;
import tech.tora.quaver.notepad.widget.elements.AddButton;
import tech.tora.quaver.notepad.widget.elements.EditAreaThing;
import tech.tora.quaver.notepad.widget.elements.PreviewAreaThing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedHashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class LayoutBuilder {

	private Layout layout = null;
	
	private Notepad notepad;

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
	
	// View
	private EditAreaThing editArea;
	private PreviewAreaThing previewArea;
	
	public LayoutBuilder(Notepad notepad) {
		this.notepad = notepad;
	}
	
	public void buildBasicLayout() {
		clearWindow();
		layout = new BasicLayout(notepad.theme);

		layout = new BasicLayout(notepad.theme);
		layout.setWidth(layout.getDefaultWidth());
		layout.setHeight(layout.getDefaultHeight());
		
		// Menu Items
		generateTopBar();
		
		JLabel notebookTitle = new JLabel();
		notebookTitle.setText("Quaver M" + Launcher.buildID);
		notebookTitle.setFont(new Font("Helvetica", Font.BOLD, 14));
		notebookTitle.setHorizontalAlignment(JLabel.CENTER);
		notebookTitle.setBackground(new Color(200, 200, 0));
		notebookTitle.setPreferredSize(new Dimension(200, 30));
		notebookTitle.setMinimumSize(new Dimension(200, 30));
		notebookTitle.setMaximumSize(new Dimension(200, 30));
		
		BasicList notebooksList = new BasicList(200);
		BasicListNode n = new BasicListNode(25, "0", "Notebooks", 
				new Color(230, 230, 230), new Color(200, 200, 200), 
				new Font("Helvetica", Font.BOLD, 14), new Color(0, 0, 0)) {};
		notebooksList.addNode(n);
		
		BasicList notesList = new BasicList(300);
		
		AddButton btnAddNotebook = new AddButton() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Add Notebook Clicked");
				if (notepad.newBuild) System.out.println("Get input window");
				else System.out.println("Carry on");
			}
		};
		
		AddButton btnAddNote = new AddButton() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Add Note Clicked");
			}
		};
		
		editArea = new EditAreaThing() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSave() {
				System.out.println("Save");
			}
			
			@Override
			public void onChange() {
				System.out.println("Edit");
			}
			
			@Override
			public void newNotebook() {
				System.out.println("New Notebook");
			}
		};

		previewArea = new PreviewAreaThing() {
			private static final long serialVersionUID = 1L;
		};
		String previewText = "<html><head><title>Quaver M" + Launcher.buildID + "</title></head>";
		previewText += "<body style=\"background-color: #393F4B; color: #f2f2f2; font: helvetica; padding: 20px;\"";
		previewText += "<h1>Quaver Mark "+Launcher.buildID+"</h1><hr><br/>";
		previewText += "<p>This is a test preview (Mark "+Launcher.buildID+")</p>";
		previewText += "</body></html>";
		
		
		previewArea.setText(previewText);

		// Add Title to Notebook Section
		((BasicLayout) layout).notebooksTop.add(notebookTitle);

		// Add Lists
		((BasicLayout) layout).notebooksListContainer.add(notebooksList, BorderLayout.CENTER);
		((BasicLayout) layout).notesListContainer.add(notesList, BorderLayout.CENTER);
		
		// Add Buttons
		((BasicLayout) layout).notebooksBot.getLeftPane().add(btnAddNotebook, BorderLayout.WEST);
		((BasicLayout) layout).notesTop.getLeftPane().add(btnAddNote, BorderLayout.WEST);
		
		// Add Edit Area
		((BasicLayout) layout).splitter.add(editArea);
		((BasicLayout) layout).splitter.add(previewArea);
		
		// Build It
		buildNewWindow(layout);
		
		if (!notepad.newBuild) {
		
			notepad.getNotebooks();
		
			int titleNodeID = 0;
			int notebookCount = 0;
			String activeKey = "";
			BasicListNode parentNode = null;
			LinkedHashMap<String, BasicClickListNode> nodes = new LinkedHashMap<>();
			for (String key : notepad.notebookArray.keySet()) {
				if (activeKey == null && parentNode == null) {
					// First
					activeKey = key;
					parentNode = new BasicListNode(20, 
							"" + titleNodeID, key, 
							notepad.theme.notebookFillColour.getAsColor(), notepad.theme.notebookHoverColour.getAsColor(), 
							new Font("Helvetica", Font.BOLD, 14), new Color(40, 40, 40)) {
					};
					titleNodeID++;
				} else if (activeKey.equals(key)) {
					// Add to current
					
				} else {
					// Add to list and renew
					
				}
				// Add last
				notebooksList.addNode(parentNode);
			}
		}
		
		
	}
	
	public void buildCompactLayout() {
		clearWindow();
		layout = new CompactLayout(notepad.theme);
		

		
		generateTopBar();
		layout.getMenu().add(fileMenu);
		layout.getMenu().add(editMenu);
		layout.getMenu().add(viewMenu);
		
		
		buildNewWindow(layout);
	}
	
	public void buildPreviewLayout() {
		clearWindow();
		layout = new PreviewLayout(notepad.theme);
		
		((PreviewLayout) layout).getWrapper().add(previewArea);
		
		generateTopBar();
		layout.getMenu().add(fileMenu);
		layout.getMenu().add(editMenu);
		layout.getMenu().add(viewMenu);
		
		buildNewWindow(layout);

	}
	
	private void generateTopBar() {
		fileMenu = new JMenu("File");
		
		newMenuItem = new JMenuItem("New Notebook");
		newMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		updateMenuItem = new JMenuItem("New Note");
		updateMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		saveMenuItem = new JMenuItem("Save All");
		saveMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Launcher.exit(0, "Shutdown request from top menu");
			}
		});
		
		fileMenu.add(newMenuItem);
		fileMenu.add(updateMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(exitMenuItem);
		

		editMenu = new JMenu("Edit");
		insertLinkMenuItem = new JMenuItem("Insert Link");
		
		editMenu.add(insertLinkMenuItem);
		

		viewMenu = new JMenu("View");
		defaultSwitch = new JMenuItem("Default Mode");
		defaultSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Notepad.layoutManager.buildBasicLayout();
			}
		});
		compactSwitch = new JMenuItem("Compact Mode");
		compactSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    Notepad.layoutManager.buildCompactLayout();
			}
		});
		
		previewSwitch = new JMenuItem("Preview Mode");
		previewSwitch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    Notepad.layoutManager.buildPreviewLayout();
			}
		});

		if (!(layout instanceof BasicLayout)) viewMenu.add(defaultSwitch);
		if (!(layout instanceof CompactLayout)) viewMenu.add(compactSwitch);
		if (!(layout instanceof PreviewLayout)) viewMenu.add(previewSwitch);
		
		// Add Top Bar
		layout.getMenu().add(fileMenu);
		layout.getMenu().add(editMenu);
		layout.getMenu().add(viewMenu);
		
	}
	
	private void buildNewWindow(Layout l) {
		
		Notepad.window = new JFrame();
		
		Notepad.window.setAlwaysOnTop(true);
		
		try {
			Notepad.window.setIconImage(new ImageIcon(Launcher.class.getResource("/icon1.png")).getImage());
		} catch(Exception e) {
			e.printStackTrace();
		}
		Notepad.window.setJMenuBar(layout.getMenu());
		Notepad.window.setContentPane(layout.getWrapper());
		Notepad.window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		Notepad.window.pack();
		Notepad.window.setTitle("Quaver : " + Launcher.buildID);

		Notepad.window.setSize(layout.getDefaultWidth(), layout.getDefaultHeight());
		Notepad.window.setLocationRelativeTo(null);
		
		Notepad.window.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				//System.out.println("Hello There");
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				//System.out.println("Minimised");
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				//System.out.println("Maximised");
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				//System.out.println("Out of focus");
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// Handle close operation without saving on a new build
				//System.out.println("Bye");
				Launcher.exit(0, "Close Button Request");
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// Do Nothing
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				//System.out.println("Focused");
			}
		});
		
		Notepad.window.setVisible(true);
		
	}
	
	private void clearWindow() {
		if (Notepad.window != null) Notepad.window.dispose();
	}
	

}
