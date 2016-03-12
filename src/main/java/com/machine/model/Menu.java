package com.machine.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private MenuObject title;
	private List<MenuObject> items =  new ArrayList<>();
	/**
	 * @return the items
	 */
	public List<MenuObject> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<MenuObject> items) {
		this.items = items;
	}
	/**
	 * @return the title
	 */
	public MenuObject getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(MenuObject title) {
		this.title = title;
	}
	
 }
