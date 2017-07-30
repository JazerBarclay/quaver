package tech.tora.tools.system.log;

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
