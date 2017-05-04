package tech.tora.quaver.types;

public enum CellType {
	
	TEXT("text"),
	MARKDOWN("markdown"),
	CODE("code")
	;
	
	public String type;
	
	CellType(String value) {
		this.type = value;
	}
	
}
