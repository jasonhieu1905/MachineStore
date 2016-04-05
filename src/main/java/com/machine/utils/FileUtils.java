package com.machine.utils;

import java.io.File;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {

	private void deleteUploadedFile(final String fileName) {
		URL url = getClass().getResource("/resources/images/banner5.png");
		File file = new File(url.getPath());
		boolean delete = file.delete();
		System.out.println(delete);
	}

}
