package tech.tora.quaver.notepad.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import tech.tora.quaver.notepad.widget.layout.InsetsBorder;

public abstract class EditAreaThing extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
	private JScrollPane scrollPane;
	@SuppressWarnings("unused")
	private DefaultCaret caretPosition = new DefaultCaret();
	
	public EditAreaThing() {
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Helvetica",Font.PLAIN,14));
		textArea.setBackground(new Color(230, 230, 230));
		textArea.setMargin(new Insets(15, 15, 15, 15));
		textArea.setBorder(new InsetsBorder(new Insets(5, 10, 5, 5)));
		textArea.setLineWrap(true);
		textArea.addKeyListener(this);

//		caretPosition.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		
		scrollPane = new JScrollPane(textArea, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(new InsetsBorder(new Insets(0, 0, 0, 0)));
		
		setLayout(new BorderLayout());
		setOpaque(false);
		add(scrollPane, BorderLayout.CENTER);
	}

	public abstract void onSave();
	public abstract void onChange();
	public abstract void newNotebook();

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getModifiers() == Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) {
			if (e.getKeyCode() == KeyEvent.VK_S) {
				onSave();
			} else if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
	//			if (e.getKeyCode() == KeyEvent.VK_SHIFT) if (e.getKeyCode() == KeyEvent.VK_CONTROL)
			} else {
				System.err.println(e.getKeyCode());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		caretPosition = (DefaultCaret)textArea.getCaret();
//		System.out.println(caretPosition);
		onChange();
//		System.out.println("Typed " + e.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent e) {
//		System.out.println("Typed " + e.getKeyChar());
	}
	
	public void setText(String s) {
		textArea.setText(s);
	}
	
	public void appendText(String s) {
		textArea.setText(textArea.getText() + s);
	}
	
	public String getText() {
		return textArea.getText();
	}
	
}
