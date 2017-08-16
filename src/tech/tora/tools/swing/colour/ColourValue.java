package tech.tora.tools.swing.colour;

import java.awt.Color;

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
	
	public Color getAsColor() {
		return new Color(r, g, b);
	}
	
	public void mixShade(int r, int g, int b) {
		if ((this.r + r) < 255) {
			if ((this.r + r) > 0) {
				this.r += r;
			} else this.r = 255;
		} else this.r = 0;
		
		if ((this.g + g) < 255) {
			if ((this.g + g) > 0) {
				this.g += g;
			} else this.g = 255;
		} else this.g = 0;
		
		if ((this.b + b) < 255) {
			if ((this.b + b) > 0) {
				this.b += b;
			} else this.b = 255;
		} else this.b = 0;
	}
	
	public Color addShade(int r, int g, int b) {
		int localR=this.r, localG=this.g, localB=this.b;
		if ((localR + r) < 255) {
			if ((localR + r) > 0) {
				localR += r;
			} else localR = 255;
		} else localR = 0;
		
		if ((localG + g) < 255) {
			if ((localG + g) > 0) {
				localG += g;
			} else localG = 255;
		} else localG = 0;
		
		if ((localB + b) < 255) {
			if ((localB + b) > 0) {
				localB += b;
			} else localB = 255;
		} else localB = 0;
		return new Color(localR, localG, localB);
	}
	
	public Color addShade(ColourValue colour) {
		return addShade(colour.r, colour.g, colour.b);
	}

}
