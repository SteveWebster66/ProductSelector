package com.sky.product.ws;

import java.util.List;

/**
 * Plain pojo used to store form purchase results.
 *
 * @author stevewebster
 *
 */
public class PurchasedList {

	String customerId;
	List<String> products;

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public List<String> getProducts() {
		return products;
	}
	public void setProducts(List<String> products) {
		this.products = products;
	}

}
