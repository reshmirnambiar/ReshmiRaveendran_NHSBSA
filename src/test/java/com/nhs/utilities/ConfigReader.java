package com.nhs.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	public Properties readProperties() {
		Properties prop = new Properties();
		String env = System.getProperty("env", "qa"); // default to 'qa' if not passed

		String path = "src/test/resources/config/config." + env + ".properties";

		try (FileInputStream fis = new FileInputStream(path)) {
			prop.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Could not load properties file: " + path, e);
		}
		return prop;
	}
}
