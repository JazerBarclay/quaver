package tech.tora.tools.swing.list;

public class BasicList extends AbstractList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BasicList(int width) {
		super(width);
	}

	@Override
	public boolean manageDuplicateNode(AbstractListNode original, AbstractListNode newNode) {
		renameAndSaveDuplicate(newNode, newNode.UUID + "_1", newNode.title + "_1");
//		overwriteDuplicate(original, newNode);
		return true;
	}
	
	public void onClick(BasicListNode nodeClicked) {
		BasicListNode n;
		for (String key : getNodeKeys()) {
			n = (BasicListNode) getNodes().get(key);
			n.active = false;
			if (n == nodeClicked) {
				n.active = true;
				n.setFillColour(n.hover.addShade(n.clickMod, n.clickMod, n.clickMod));
			} else {
				n.setFillColour(n.fill.getAsColor());
			}
		}
		revalidate();
	}

}
