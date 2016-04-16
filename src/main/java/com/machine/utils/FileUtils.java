package com.machine.utils;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

public class FileUtils {
	
	public static String directoryImage(HttpServletRequest request){
		return  request.getSession().getServletContext().getRealPath("/") + "resources/images/";
	}
	
	public static void removeImageResources(String imageName,HttpServletRequest request){
		String directory = directoryImage(request);
		File file = new File(directory+imageName);
		file.delete();
	}
	
	public static String removeLastCharacterIfComma(String imageNames){
		if(imageNames.substring(imageNames.length()-1, imageNames.length()).equals(",")){
			return imageNames.substring(0,imageNames.length()-1);
		}
		return imageNames;
	}
	
}
