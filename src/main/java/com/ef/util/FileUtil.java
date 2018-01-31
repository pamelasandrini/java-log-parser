package com.ef.util;

import java.io.File;

/**
 * Utility class for file handler.
 * 
 * @author pborsoni
 *
 */
public class FileUtil {

	private static final String ACCESS_LOG_FILE_NAME = "access.log";

	private FileUtil() {
		throw new IllegalAccessError("Utility class");
	}

	public static boolean fileExists(String pathname) {
		File file = new File(pathname);

		return file.exists() && file.isFile();
	}

	public static File getLogFileFromResourceFolder() {


		//TODO: not working properly
		ClassLoader classLoader = FileUtil.class.getClassLoader();
		return new File(classLoader.getResource(ACCESS_LOG_FILE_NAME).getFile());

	}

	public static File getLogFileFromPath(String pathname) {

		return new File(pathname);

	}
}
