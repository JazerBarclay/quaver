package tech.tora.quaver.colour;

public class ColourValue {

	/*
	 * These values are initialised on construction
	 * or are defaulted at (0,0,0)
	 */
	int r = 0;
	int g = 0;
	int b = 0;
	
	/**
	 * This value is always calculated
	 */
	String hex;
	
	public ColourValue() {
		// Do Nothing
	}
	
	public ColourValue(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public String getHex() {
		hex = "";
		return hex;
	}
	
	public static String getHex(int r, int g ,int b) {
		String localHex = "";
		return localHex;
	}
	
	public static String getHex(ColourValue v) {
		String localHex = "";
		return localHex;
	}
	
}
