package tech.tora.tools.swing.frame;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public abstract class AdvancedFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* ------------------------------------------------------ */
	// Override Parent Constructors
	/* ------------------------------------------------------ */
	
	public AdvancedFrame() throws HeadlessException {
		super();
		init();
	}
	
	public AdvancedFrame(String title) {
		super(title);
		init();
	}

	public AdvancedFrame(GraphicsConfiguration gc) {
		super(gc);
		init();
	}

	public AdvancedFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		init();
	}
	
	public void init() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				openAction();
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				minimiseAction();
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				maximiseAction();
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				loseFocusAction();
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				closeAction();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// Do Nothing (for now)
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				gainFocusAction();
			}
		});
	}
	
	/* ------------------------------------------------------ */
	// Window Action Overrides
	/* ------------------------------------------------------ */

	public abstract void openAction();
	public abstract void closeAction();
	public abstract void minimiseAction();
	public abstract void maximiseAction();
	public abstract void gainFocusAction();
	public abstract void loseFocusAction();

	/* ------------------------------------------------------ */
	// Window Updates
	/* ------------------------------------------------------ */
	
	public void updateFrameSize(int width, int height, boolean resetPosition) {
		boolean vis = isVisible();
		if (vis) setVisible(false);
		setSize(width, height);
		if (resetPosition) setLocationRelativeTo(null);
		if (vis) setVisible(true);
	}
	
	public void updateContent(JPanel contentPane) {
		updateContent(contentPane, true);
	}
	
	public void updateContent(JPanel contentPane, boolean revalidate) {
		setContentPane(contentPane);
		if (revalidate) revalidate();
	}
	
	public void updateMenu(JMenuBar menu) {
		updateMenu(menu, true);
	}
	
	public void updateMenu(JMenuBar menu, boolean revalidate) {
		setJMenuBar(menu);
		if (revalidate) revalidate();
	}
	
	public void updateTitle(String title) {
		updateTitle(title, true);
	}
	
	public void updateTitle(String title, boolean revalidate) {
		setTitle(title);
		if (revalidate) revalidate(); 
	}

}
