package me.mohammad.filefinder;

public interface FileOperation {
	
	public String getKeyword();
	
	public String getStartingPath();
	
	public boolean isRunning();
	
	public long run();
	
}