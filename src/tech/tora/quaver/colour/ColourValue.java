package tech.tora.quaver.colour;

public class ColourValue {

	/*
	 * These values are initialised on construction
	 * or are defaulted at (0,0,0)
	 */
	int r;
	int g;
	int b;
	
	/**
	 * This value is always calculated
	 */
	String hex;
	
	public ColourValue() {
		this.r = 0;
		this.g = 0;
		this.b = 0;
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
	
}
