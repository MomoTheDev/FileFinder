package me.mohammad.filefinder;

import java.io.File;
import java.util.function.Consumer;

public class SearchOperation implements FileOperation {

	private String keyword;
	private boolean running;

	private String startingPath;
	private Consumer<File> handler;

	public SearchOperation(final String keyword, final String startingPath) {
		this.keyword = keyword;
		this.running = false;
		this.startingPath = startingPath;
	}

	public SearchOperation handle(final Consumer<File> consumer) {
		handler = consumer;
		return this;
	}

	@Override
	public String getKeyword() {
		return keyword;
	}

	@Override
	public String getStartingPath() {
		return startingPath;
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public long run() {
		if (isRunning())
			return -1;
		long millis = System.currentTimeMillis();
		running = true;
		final File file = new File(startingPath);
		operate(file);
		return (System.currentTimeMillis() - millis);
	}

	private void operate(final File file) {
		try {
			for (File f : file.listFiles()) {
				if (f.getName().contains(keyword)) {
					handler.accept(f);
				}
				if (f.isDirectory())
					operate(f);
			}
		} catch (Exception e) {
			if (!(FileFinder.hideErrors))
				System.out.println("An error occoured while searching through the file: " + file.getAbsolutePath());
		}
		running = false;
	}

}