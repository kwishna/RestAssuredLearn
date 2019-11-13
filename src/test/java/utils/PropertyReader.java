package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	FileInputStream fin;
	Properties prop;

	private PropertyReader(String fileName) throws IOException {

		fin = new FileInputStream(new File(System.getProperty("user.dir") + "/Resources/" + fileName));
		prop = new Properties();
		prop.load(fin);
	}

	public static PropertyReader getPropertyFileReader(String fileName) throws IOException {

		if (fileName.isBlank()) {

			throw new RuntimeException("Property File Name Should Not Be Blank");
		}

		else if (!fileName.endsWith(".properties")) {

			fileName = fileName + ".properties";
		}

		return new PropertyReader(fileName);
	}

	public String getValueFor(String key) {

		return prop.getProperty(key.toUpperCase());
	}
}
