package me.mohammad.filefinder;

import java.io.File;
import java.io.IOException;

public class FileFinder extends Thread {
	
	protected static FileFinder instance;
	
	private String keyword;
	private String startingPath;
	
	private FileFinder(final String keyword, final String startingPath) {
		this.keyword = keyword;
		this.startingPath = startingPath;
		start();
	}
	
	public static void main(String[] args) {
		if (instance != null) return;
		if (args.length != 2) exit(0, null);
		final String keyword = args[0];
		final String startingPath = args[1];
		final File startingPathFile = new File(startingPath);
		if (!(startingPathFile.exists())) exit(2, startingPath);
		instance = new FileFinder(keyword, startingPath);
		try {
			instance.wait(500);
			instance.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void exit(final int exitCode, final String message) {
		switch (exitCode) {
		case 0: {
			System.out.println("Invalid arguments!");
			try {
				instance.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(0);
			break;
		}
		case 1: {
			System.out.println("Please run the program with administrative privileges!");
			try {
				instance.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(1);
			break;
		}
		case 2: {
			System.out.println("Starting path doesn't exist! PATH: " + message);
			try {
				instance.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(2);
			break;
		}
		}
	}
	
	@Override
	public void run() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("FileFinder Tool by MomoTheSiM, developed in Java 16");
		System.out.println("Searching for Files containing keyword " + keyword + " in \nthe starting directory " + startingPath);
		System.out.println();
		final FileOperation operation = new SearchOperation(keyword, startingPath).handle((file) -> {
			System.out.println("Found File: " + file.getAbsolutePath());
		});
		final long time = operation.run();
		System.out.println("Done searching in " + time + "ms\n");
	}
	
}