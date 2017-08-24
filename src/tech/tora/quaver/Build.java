package tech.tora.quaver;

public class Build {

	private String name;
	private int buildRelease = 0, buildMajor = 0, buildMinor = 0;
	
	public Build(String name, int release, int major, int minor) {
		this.name = name;
		this.buildRelease = release;
		this.buildMajor = major;
		this.buildMinor = minor;
	}
	
	public String name() {
		return name;
	}
	
	public int release() {
		return buildRelease;
	}
	
	public int major() {
		return buildMajor;
	}
	
	public int minor() {
		return buildMinor;
	}
	
	public String toString() {
		return "M" + buildRelease + "." + buildMajor + " r" + buildMinor;
	}
	
}
