package tech.tora.tools.swing.list;

/**
 * List of elements that are for view only
 * No management for click or hover over nodes
 * 
 * @author Nythril
 *
 */
public class PreviewList extends AbstractList {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PreviewList(int width) {
		super(width);
	}

	@Override
	public boolean manageDuplicateNode(AbstractListNode original, AbstractListNode newNode) {
//		return false;
//		return overwriteDuplicate(original, newNode);
		return renameAndSaveDuplicate(newNode, newNode.UUID + "_1", newNode.title + "_1");
	}
	
	
}
