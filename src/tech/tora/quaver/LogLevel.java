package tech.tora.quaver;

public enum LogLevel {

	INFO("information")
	,WARN("warning")
	,ERROR("error")
	;
	
	public String level;
	
	LogLevel(String value) {
		this.level = value;
	}
	
}
