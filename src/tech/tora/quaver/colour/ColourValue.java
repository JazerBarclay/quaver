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

	public ColourValue(String hex) {
		if (hex != null) {
			if (hex.length()==6) {
				r = Integer.parseInt(hex.substring(0, 2), 16);
				b = Integer.parseInt(hex.substring(2, 4), 16);
				g = Integer.parseInt(hex.substring(4, 6), 16);
			}	
		}
	}
	public String getHex() {
		return String.format("%02x%02x%02x", r, g, b);
	}

	public static String getHex(int r, int g ,int b) {
		return String.format("%02x%02x%02x", r, g, b);
	}

	public static String getHex(ColourValue v) {
		return String.format("%02x%02x%02x", v.r, v.g, v.b);
	}

}
