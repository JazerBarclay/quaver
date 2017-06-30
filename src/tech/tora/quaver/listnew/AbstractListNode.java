package tech.tora.quaver.listnew;

import javax.swing.JPanel;

public class AbstractListNode extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ListDataNode data;
	
	public AbstractListNode(String uuid, String title) {
		setupData(uuid, title);
	}
	
	protected void setupData(String uuid, String name) {
		data = new ListDataNode(uuid, name);
	}
	
	public ListDataNode getData() {
		return data;
	}

}
