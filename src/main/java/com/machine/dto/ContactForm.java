package com.machine.dto;

import com.machine.model.Contact;
import com.machine.model.FileUpload;

public class ContactForm {
	
	private Contact contact;
	
	private FileUpload fileUpload;
	
	private FileUpload bannerFooterUpload;
	
	public FileUpload getBannerFooterUpload() {
		return bannerFooterUpload;
	}

	public void setBannerFooterUpload(FileUpload bannerFooterUpload) {
		this.bannerFooterUpload = bannerFooterUpload;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	public ContactForm(){
		this.contact = new Contact();
		this.fileUpload = new FileUpload();
		this.bannerFooterUpload = new FileUpload();
	}

}
