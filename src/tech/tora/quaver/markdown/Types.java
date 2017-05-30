package tech.tora.quaver.markdown;

public enum Types {

	// Headers
	H1("h1"),
	H2("h2"),
	H3("h3"),
	H4("h4"),
	H5("h5"),
	H6("h6"),
	// Formatting
	ITALIC("italic"),
	BOLD("bold"),
	// Code / Quotes
	QUOTE("quote"),
	CODE("code"),
	// Lists
	ORDEREDLIST("ordered"),
	UNORDEREDLIST("unordered"),
	// Images
	IMAGE("image"),
	LINK("link"),
	;
	
	public String type;
	
	Types(String value) {
		this.type = value;
	}
	
}
