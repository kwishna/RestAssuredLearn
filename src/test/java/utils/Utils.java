package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.ValidatableResponse;

public class Utils {

	public static String readFile(String fileName) {

		String data = "";
		try {

			data = Files.readString(Paths.get(System.getProperty("user.dir") + "/src/main/java/dataFiles/" + fileName));
		} catch (IOException e) {

			e.printStackTrace();
		}

		return data;
	}
	
	public static XmlPath rawToXml(String response) {

		return new XmlPath(response);
	}
	
	public static JsonPath rawToJson(String response) {

		// Or, response.extract().response().jsonPath();
		return new JsonPath(response);
	}
	
	public static void saveIntoFile(ValidatableResponse response){
		
		if(response==null) {
			
			return;
		}
		
		String contentType = response.extract().response().contentType();
		
		if(contentType.contains("json")) {
			
			try {
				Files.write(Paths.get(System.getProperty("user.dir")+"/outputFiles/Response_"+new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date())+".json"),
						response.extract().response().asByteArray());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		if(contentType.contains("xml")) {
			
			try {
				Files.write(Paths.get(System.getProperty("user.dir")+"/outputFiles/Response_"+new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date())+".xml"),
						response.extract().response().asByteArray());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	
	public static void saveIntoFile(ValidatableResponse response, String fileName){
		
		if(response==null) {
			
			return;
		}
		
		String contentType = response.extract().response().contentType();
		
		if(contentType.contains("json")) {
			
			try {
				Files.write(Paths.get(System.getProperty("user.dir")+"/outputFiles/"+fileName+"_"+new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date())+".json"),
						response.extract().response().asByteArray());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		if(contentType.contains("xml")) {
			
			try {
				Files.write(Paths.get(System.getProperty("user.dir")+"/outputFiles/"+fileName+"_"+new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date())+".xml"),
						response.extract().response().asByteArray());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public static void saveToFile(String data) {
		
		try {
			Files.write(Paths.get(System.getProperty("user.dir")+"/outputFiles/Data_"+new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date())+".txt"),
					data.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void saveToFile(String data, String fileNameWithFormat) {
		
		String[] s = fileNameWithFormat.split("[.]");
		String end = s[s.length-1];
		StringBuilder fileName = new StringBuilder();
		
		for(int i=0; i<s.length-1; i++) {
			
			fileName.append(s[i]);
		}

		if(null != data || fileNameWithFormat != null) {
			try {
				Files.write(Paths.get(System.getProperty("user.dir") + "/outputFiles/" + fileName + "_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()) + "." + end),
						data.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
