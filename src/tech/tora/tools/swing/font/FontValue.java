package tech.tora.tools.swing.font;

import java.awt.Font;

public class FontValue {

	public String fontType;
	public int fontSize;
	
	public Font getAsFont(boolean isBold, boolean isItalic) {
		return new Font(fontType, (isBold?Font.BOLD:(isItalic?Font.ITALIC:Font.PLAIN)), fontSize);
	}
	
}
