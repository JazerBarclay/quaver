package tech.tora.quaver.types;

public enum CellType {
	
	TEXT("text"),
	MARKDOWN("markdown"),
	CODE("code"),
	LATEX("latex")
	;
	
	public String type;
	
	CellType(String value) {
		this.type = value;
	}

	public CellType[] allTypes() {
		return new CellType[] {
				CellType.TEXT, 
				CellType.MARKDOWN, 
				CellType.CODE, 
				CellType.LATEX};
	}
	
}
