package com.nick.workoutCalculator;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Utility class to loading resource files from within the project
 */
public class FileLoader {

	public String getResourceFileContents(String filePath) throws Exception {
		if(filePath == null) {
			throw new IllegalArgumentException("Filepath cannot be null");
		}

		URL resource = FileLoader.class.getClassLoader().getResource(filePath);
		if (resource == null) {
			throw new FileNotFoundException("Project resource file not found: " + filePath);
		}

		try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath)) {
			return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
		}
	}
}
