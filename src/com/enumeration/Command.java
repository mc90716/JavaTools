package com.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum Command {
	LOGIN("log in", null),
	EXEC("exec a command", null);
	
	private final String description;
	private final CommandHandler handler;
	
	Command(String description, CommandHandler handler) {
		this.description = description;
		this.handler = handler;
	}
	
	private static final Map<String, Command> commands = new HashMap<String, Command>();
	
	static {
		for (Command c : Command.values()) {
			commands.put(c.name().toLowerCase(), c);
		}
	}
	
	public String description() {
		return this.description;
	}
	
	public CommandHandler handler() {
		return this.handler;
	}
	
	public static Command parse(String name) {
		return commands.get(name.toLowerCase());
	}
	
}
