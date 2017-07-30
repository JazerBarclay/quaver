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
	public void manageDuplicateNode(AbstractListNode original, AbstractListNode newNode) {
		renameAndSaveDuplicate(newNode, newNode.UUID + "_1", newNode.title + "_1");
//		overwriteDuplicate(original, newNode);
	}

}
