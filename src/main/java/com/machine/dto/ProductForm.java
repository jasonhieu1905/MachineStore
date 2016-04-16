package com.machine.dto;

import com.machine.model.FileUpload;
import com.machine.model.Product;

public class ProductForm {
	private Product product;
	
	private FileUpload mainFileUpload;
	
	private FileUpload detailFileUpload;

	public FileUpload getMainFileUpload() {
		return mainFileUpload;
	}

	public void setMainFileUpload(FileUpload mainFileUpload) {
		this.mainFileUpload = mainFileUpload;
	}

	public FileUpload getDetailFileUpload() {
		return detailFileUpload;
	}

	public void setDetailFileUpload(FileUpload detailFileUpload) {
		this.detailFileUpload = detailFileUpload;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public ProductForm(){}

	public ProductForm(Product product, FileUpload mainFileUpload, FileUpload detailFileUpload) {
		super();
		this.product = product;
		this.mainFileUpload = mainFileUpload;
		this.detailFileUpload = detailFileUpload;
	}
	
	
}
