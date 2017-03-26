package com.sky.product.model;

public class Product {

	private String genre;
	private String id;
	private boolean purchased;

	public Product() {

	}

	public Product(String genre, String id) {
		this.genre = genre;
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isPurchased() {
		return purchased;
	}

	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}
}
