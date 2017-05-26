package tech.tora.quaver.log;

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
