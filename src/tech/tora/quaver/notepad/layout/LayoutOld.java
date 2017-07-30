package tech.tora.quaver.notepad.layout;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.theme.Theme;
import tech.tora.tools.system.log.Logging;

/**
 * Base class for interface layout
 * 
 * @author Nythril
 */
public abstract class LayoutOld {
	
	private static int width = -1, height = -1;
	private static int defaultWidth = 0, defaultHeight = 0;
	
	protected JFrame window;
	protected JMenuBar topMenu;
	protected JPanel wrapper;
	
	// Creates the elements and builds the framework
	public LayoutOld(Theme theme) {
		initFrame(theme);
		buildFrame();
		setDefaultWidth(800);
		setDefaultHeight(600);
	}
	
	/** Returns top level wrapper **/
	public abstract JPanel getWrapper();

	/** Creates all elements for frame **/
	public abstract void initFrame(Theme t);

	/** Adds elements to each other to create layout **/
	public abstract void buildFrame();
	
	public abstract void windowCloseAction();
	
	public void buildWindow(String windowTitle, boolean alwaysOnTop) {
		
		window = new JFrame();
		window.setAlwaysOnTop(alwaysOnTop);
		try {
			window.setIconImage(
					new ImageIcon(Launcher.class.getResource("/icon1.png")).getImage());
		} catch (Exception e) {
			Logging.warn("Window Icon", "Failed to set icon image", e);
		}
		
		window.setJMenuBar(getMenu());
		window.setContentPane(getWrapper());
		window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		window.pack();
		window.setTitle(windowTitle + (Launcher.config.devmode ? " : " + Launcher.buildID : ""));

		window.setSize(getDefaultWidth(), getDefaultHeight());
		window.setLocationRelativeTo(null);
		
		window.addWindowListener(new WindowListener() {
			
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
				windowCloseAction();
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
		
	}
	
	/** Returns the menu bar **/
	public JMenuBar getMenu() {
		return topMenu;
	}
	
	// Set window 
	public void showWindow(boolean visible) {
		window.setVisible(visible);
	}

	/* ------------------------------------------------------ */
	
	/** Default width set by the layout standard **/
	public void setDefaultWidth(int w) {
		defaultWidth = w;
	}
	
	/** Returns the default width of the layout **/
	public int getDefaultWidth() {
		return defaultWidth;
	}
	
	/** Default height set by the layout standard **/
	public void setDefaultHeight(int h) {
		defaultHeight = h;
	}

	/** Returns the default height of the layout **/
	public int getDefaultHeight() {
		return defaultHeight;
	}
	
	/** Sets the active width of the layout **/
	public void setWidth(int w) {
		width = w;
	}

	/** Sets the active height of the layout **/
	public void setHeight(int h) {
		height = h;
	}

	/** Returns the active width of the layout **/
	public int getWidth() {
		return width;
	};

	/** Returns the active height of the layout **/
	public int getHeight() {
		return height;
	};
	
}
