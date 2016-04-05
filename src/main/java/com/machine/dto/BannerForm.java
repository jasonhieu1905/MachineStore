package com.machine.dto;

import com.machine.model.Banner;
import com.machine.model.FileUpload;

public class BannerForm {

	private Banner banner;
	
	private FileUpload fileUpload;
	
	public BannerForm(){
		this.banner = new Banner();
		this.fileUpload = new FileUpload();
	}

	public Banner getBanner() {
		return banner;
	}

	public void setBanner(Banner banner) {
		this.banner = banner;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	
}
