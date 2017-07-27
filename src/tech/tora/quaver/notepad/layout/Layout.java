package tech.tora.quaver.notepad.layout;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import tech.tora.quaver.Launcher;
import tech.tora.quaver.log.Logging;
import tech.tora.quaver.theme.Theme;

public abstract class Layout {

	private static int width = -1, height = -1;
	private static int defaultWidth = 800, defaultHeight = 600;

	private JFrame window;
	protected JMenuBar topMenu;
	protected JPanel wrapper;

	public Layout(Theme theme) {
		initVariables();
		initFrame(theme);
		buildTopBar(topMenu);
		buildFrame(wrapper);
	}

	private final void initVariables() {
		window = new JFrame();
		topMenu = new JMenuBar();
		wrapper = new JPanel();
	}

	public void buildWindow(String title, String iconResource, boolean onTopAlways) {
		
		try {
			window.setIconImage(
//				new ImageIcon(Launcher.class.getResource("/icon1.png")).getImage());
				new ImageIcon(Launcher.class.getResource(iconResource)).getImage());
		} catch (Exception e) {
			Logging.warn("Window Icon", "Failed to set icon image", e);
		}

		window.setAlwaysOnTop(onTopAlways);
		window.setJMenuBar(getMenu());
		window.setContentPane(getWrapper());
		window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		window.pack();

		window.setTitle(title);
		window.setSize(getDefaultWidth(), getDefaultHeight());
		window.setLocationRelativeTo(null);
	
		window.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				windowOpenAction();
			}
	
			@Override
			public void windowIconified(WindowEvent e) {
				windowMinimiseAction();
			}
	
			@Override
			public void windowDeiconified(WindowEvent e) {
				windowMaximiseAction();
			}
	
			@Override
			public void windowDeactivated(WindowEvent e) {
				windowLoseFocusAction();
			}
	
			@Override
			public void windowClosing(WindowEvent e) {
				windowCloseAction();
			}
	
			@Override
			public void windowClosed(WindowEvent e) {
				// Do Nothing
			}
	
			@Override
			public void windowActivated(WindowEvent e) {
				windowGainFocusAction();
			}
		});

	}


	/* ------------------------------------------------------ */
	// Frame Content Management
	/* ------------------------------------------------------ */
	
	public abstract void initFrame(Theme theme);
	public abstract void buildFrame(JPanel wrapperPanel);
	public abstract void buildTopBar(JMenuBar menu);
	public abstract void buildElements();

	/* ------------------------------------------------------ */
	// Window Action Overrides
	/* ------------------------------------------------------ */

	public abstract void windowOpenAction();
	public abstract void windowCloseAction();
	public abstract void windowMinimiseAction();
	public abstract void windowMaximiseAction();
	public abstract void windowGainFocusAction();
	public abstract void windowLoseFocusAction();

	/* ------------------------------------------------------ */

	/** Returns the menu bar **/
	public final JMenuBar getMenu() {
		return topMenu;
	}

	/** Returns the content wrapper pane **/
	public final JPanel getWrapper() {
		return wrapper;
	}

	/** Sets the window's visibility **/
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
		window.setSize(width, height);
	}

	/** Sets the active height of the layout **/
	public void setHeight(int h) {
		height = h;
		window.setSize(width, height);
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
