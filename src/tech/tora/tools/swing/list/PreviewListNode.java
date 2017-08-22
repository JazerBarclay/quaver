package tech.tora.tools.swing.list;

import javax.swing.JPanel;

public class PreviewListNode extends AbstractListNode {

	public PreviewListNode(String uuid, String title) {
		super(uuid, title);
	}

	@Override
	public JPanel generateNode() {
		JPanel wrapper = new JPanel();
		return wrapper;
	}

}
