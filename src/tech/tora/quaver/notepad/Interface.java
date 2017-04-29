package tech.tora.quaver.notepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tech.tora.quaver.Launcher;
import tech.tora.quaver.list.QuickList;
import tech.tora.quaver.list.QuickListNode;
import tech.tora.quaver.list.SelectionList;
import tech.tora.quaver.list.SelectionListNode;
import tech.tora.quaver.notepad.widget.AddButton;
import tech.tora.quaver.notepad.widget.EditAreaThing;
import tech.tora.quaver.notepad.widget.LayoutBuilder;

public class Interface extends JFrame {

	/**
	 * Serial UUID
	 */
	private static final long serialVersionUID = 1L;
	
	// Interface Colours /  Fonts
//	public 
	public Color bookFillColour = new Color(230, 230, 230);
	public Color notesFillColour = new Color(230, 230, 230);
	public Color previewEditFillColour = new Color(230, 230, 230);
	
	public Interface() {
		if (initialiseNotebookAndNotes()) {
			initLayout();
		} else 
			Launcher.exit(1, "Failed to generate UI");
	}
	
	private boolean initialiseNotebookAndNotes() {
		return true;
	}
	
	private void initLayout() {
		
		LayoutBuilder layout = new LayoutBuilder(200, 300, new Color(40, 40, 40), // Font Colour
				new Color(230, 230, 230), new Color(210, 210, 210), // Notebook Fill, Notebook Hover
				new Color(230, 230, 230), new Color(210, 210, 210), // Note Fill, Note Hover
				new Color(230, 230, 230), new Color(140, 140, 140), // Edit Fill, Border
				new Font("Helvetica", Font.BOLD, 14), // Notebook Title
				new Font("Helvetica", Font.BOLD, 12), new Font("Helvetica", Font.BOLD, 12),  // NB L1, NB L2
				new Font("Helvetica", Font.BOLD, 14), new Font("Helvetica", Font.BOLD, 10)); // N L1, N L2
		
		// Testing Layout
//		layout.getNotebooksList().addNode(new QuickListNode(layout.getNotebooksList(), "", "Java Notebook", "4"));
//		layout.getNotebooksList().addNode(new QuickListNode(layout.getNotebooksList(), "", "Linux Handbook", "0"));
//		layout.getNotebooksList().addNode(new QuickListNode(layout.getNotebooksList(), "", "Research Notes", "23"));
//		
//		layout.getNotesList().addNode(new SelectionListNode(layout.getNotesList(), "Seeker", "10 Jan, 2016"));
//		layout.getNotesList().addNode(new SelectionListNode(layout.getNotesList(), "Spirit", "24 Mar, 2014"));
//		layout.getNotesList().addNode(new SelectionListNode(layout.getNotesList(), "Sage", "10 Jun, 2010"));
//		layout.getNotesList().addNode(new SelectionListNode(layout.getNotesList(), "True", "14 Jan, 2013"));
		
		
//		layout.getNotesTopPane().add(new AddButton() {
//			private static final long serialVersionUID = 1L;
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				System.out.println("Create New Note for [NOTEBOOK HERE]");
//			}
//		}, BorderLayout.WEST);
//	
//		layout.getBookBotPane().add(new AddButton() {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				System.out.println("Add Button Clicked");
//			}
//		}, BorderLayout.WEST);
		

//		editArea = new EditAreaThing() {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void onSave() {
//				System.out.println("Saving");
//			}
//
//			@Override
//			public void onChange() {
//				System.out.println("Changed");
//			}
//		};
		
//		layout.getRightWrapper().add(editArea, BorderLayout.CENTER);
		
		
		try {
			setIconImage(new ImageIcon("res/icon1.png").getImage());
		} catch(Exception e) {
			e.printStackTrace();
		}
		setContentPane(layout.wrapperPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setTitle("Quaver");
		setVisible(true);

		setSize(1280, 800);
		
	}
	
//	@SuppressWarnings("unused")
//	private void initLayout2() {
//
//		notebooksList = new QuickList("Notebooks", new Color(230, 230, 230), new Color(210, 210, 210),
//				new Font("Helvetica", Font.BOLD, 14), new Font("Helvetica", Font.BOLD, 12), new Font("Helvetica", Font.BOLD, 12));
//		notebooksList.setFillColor(bookFillColour);
//		notebooksList.setHoverColor(bookFillColour);
//		notesList = new SelectionList(new Color(230, 230, 230), new Color(210, 210, 210), new Color(140, 140, 140), 
//				new Font("Helvetica", Font.BOLD, 14), new Font("Helvetica", Font.BOLD, 10));
//		
//		bookPane = new JPanel(new BorderLayout());
//		bookPane.setBackground(bookFillColour);
//		bookPane.add(notebooksList, BorderLayout.CENTER);
//
//		bookTopLabel = new JLabel();
//		bookTopLabel.setText("Notebooks | Tags");
//		bookTopLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
//		bookTopLabel.setHorizontalAlignment(JLabel.CENTER);
//		bookTopLabel.setPreferredSize(new Dimension(200, 30));
//
//		bookTopPane = new JPanel(new BorderLayout());
//		bookTopPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(140, 140, 140)));
//		bookTopPane.setOpaque(false);
//		
//		bookTopPane.add(bookTopLabel, BorderLayout.NORTH);
//		bookPane.add(bookTopPane, BorderLayout.NORTH);
//
//		bookBotPane = new JPanel(new BorderLayout());
//		bookBotPane.setOpaque(false);
//		
//		bookPane.add(bookBotPane, BorderLayout.SOUTH);
//
//		notesPane = new JPanel(new BorderLayout());
//		notesPane.setBackground(new Color(230, 230, 230));
//		
//		notesPane.add(notesList, BorderLayout.CENTER);
//
//		notesTopTitle = new JLabel();
//		notesTopTitle.setText("Java Notebook");
//		notesTopTitle.setFont(new Font("Helvetica", Font.BOLD, 14));
//		notesTopTitle.setHorizontalAlignment(JLabel.CENTER);
//		notesTopTitle.setPreferredSize(new Dimension(240, 30));
//
//		notesTopRightFill = new JLabel();
//		notesTopRightFill.setFont(new Font("Helvetica", Font.PLAIN, 14));
//		notesTopRightFill.setHorizontalAlignment(JLabel.CENTER);
//		notesTopRightFill.setPreferredSize(new Dimension(30, 30));
//
//		notesTopPane = new JPanel(new BorderLayout());
//		notesTopPane.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(140, 140, 140)));
////		notesTopPane.setBackground(new Color(230, 230, 230));
//		notesTopPane.setOpaque(false);
//
//		notesTopPane.add(notesTopTitle, BorderLayout.CENTER);
//		notesTopPane.add(notesTopRightFill, BorderLayout.EAST);
//		notesPane.add(notesTopPane, BorderLayout.NORTH);
//
//		
//		JLabel notesBotFill = new JLabel("Testing");
//		notesBotFill.setFont(new Font("Helvetica", Font.PLAIN, 14));
//		notesBotFill.setHorizontalAlignment(JLabel.CENTER);
//		notesBotFill.setPreferredSize(new Dimension(30, 30));
//		
//		
//		notesBotPane = new JPanel(new BorderLayout());
//		notesBotPane.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(140, 140, 140)));
//		notesBotPane.setOpaque(false);
//		notesBotPane.add(notesBotFill);
//		notesPane.add(notesBotPane, BorderLayout.SOUTH);
//
//		bookBotPaneLabel = new JPanel();
//		bookBotPaneLabel.setPreferredSize(new Dimension(160, 30));
//		bookBotPaneLabel.setOpaque(false);
//		bookBotPane.add(bookBotPaneLabel, BorderLayout.EAST);
//
//		leftWrapper = new JPanel(new BorderLayout());
//		leftWrapper.setOpaque(false);
//		leftWrapper.add(bookPane, BorderLayout.WEST);
//		leftWrapper.add(notesPane, BorderLayout.EAST);
//		
//		// Edit & Preview Area
//		rightTopPane = new JPanel();
//		rightTopPane.setBackground(new Color(230, 230, 230));
//		
//		// Edit & Preview Area
//		rightWrapper = new JPanel(new BorderLayout());
//		rightWrapper.setBackground(new Color(140, 140, 140));
//		
//		// Main Wrapper
//		wrapperPane = new JPanel(new BorderLayout());
//		wrapperPane.setBackground(new Color(20, 230, 230));
//
//		rightWrapper.add(rightTopPane, BorderLayout.NORTH);
//
//		wrapperPane.add(leftWrapper, BorderLayout.WEST);
//		wrapperPane.add(rightWrapper, BorderLayout.CENTER);
//		
//		genHooks();
//		
//		setContentPane(wrapperPane);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		pack();
//		setTitle("Quaver");
//		setVisible(true);
//
//		setSize(1280, 800);
//	}
//	
//	public void genHooks() {
//
//		notebooksList.addNode(new QuickListNode(notebooksList, "", "Java Notebook", "4"));
//		notebooksList.addNode(new QuickListNode(notebooksList, "", "Linux Handbook", "0"));
//		notebooksList.addNode(new QuickListNode(notebooksList, "", "Research Notes", "23"));
//		
//		notesList.addNode(new SelectionListNode(notesList, "Seeker", "10 Jan, 2016"));
//		notesList.addNode(new SelectionListNode(notesList, "Spirit", "24 Mar, 2014"));
//		notesList.addNode(new SelectionListNode(notesList, "Sage", "10 Jun, 2010"));
//		notesList.addNode(new SelectionListNode(notesList, "True", "14 Jan, 2013"));
//		
//		notesTopPane.add(new AddButton() {
//			private static final long serialVersionUID = 1L;
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				System.out.println("Create New Note for [NOTEBOOK HERE]");
//			}
//		}, BorderLayout.WEST);
//	
//		bookBotPane.add(new AddButton() {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
////				System.out.println("Add Button Clicked");
//			}
//		}, BorderLayout.WEST);
//		
//
//		editArea = new EditAreaThing() {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void onSave() {
//				System.out.println("Saving");
//			}
//
//			@Override
//			public void onChange() {
//				System.out.println("Changed");
//			}
//		};
//		
//		rightWrapper.add(editArea, BorderLayout.CENTER);
//		
//		previewArea.setText("<html><head><title>" + "TITLE" + "</title>"
//				+ "</head><body style=\"background-color: #2a3137; color: #f2f2f2; font: helvetica; padding: 20px;\">" 
//				+ "<h1 style=\"color: #FFFFFF;\">"+ "TITLE" +"</h1><hr><br><div style=\"\"></div></body></html>");
//		
//	}
	
	public void updatePreview() {
		
	}
	
	public void save() {
		
	}
	
}
