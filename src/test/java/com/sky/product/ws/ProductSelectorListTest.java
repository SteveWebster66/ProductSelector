package com.sky.product.ws;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sky.product.model.Product;

public class ProductSelectorListTest {

	ProductSelectorList products = new ProductSelectorList();

	@Before
	public void setup() {
		products = new ProductSelectorList();
	}

	@Test
	public void addAnEmptyList() {

		products.loadCatalogue(new ArrayList<Product>());

		assertNotNull(products.getGenres());

	}

	@Test
	public void addListWithOneGenre() {

		List<Product> list = buildWithSameGenre(1);

		products.loadCatalogue(list);

		assertTrue(products.getGenres().size() == 1);
		assertTrue(products.getProductsByGenres().size() == 1);
	}

	@Test
	public void addListWithSameMultipleGenre() {

		List<Product> list = buildWithSameGenre(3);

		products.loadCatalogue(list);

		assertTrue(products.getGenres().size() == 1);
		assertTrue(products.getProductsByGenres().get("genre1").size() == 3);
	}

	@Test
	public void addListWithDifferentMultipleGenre() {

		List<Product> list = buildWithMultiGenre(3);

		products.loadCatalogue(list);

		assertTrue(products.getGenres().size() == 3);
		assertTrue(products.getProductsByGenres().size() == 3);
	}

	private List<Product> buildWithSameGenre(int counter) {
		List<Product> list = new ArrayList<Product>();
		for (int i=0; i<counter; i++) {
			list.add(new Product("genre1", "id"+i));
		}
		return list;
	}

	private List<Product> buildWithMultiGenre(int counter) {
		List<Product> list = new ArrayList<Product>();
		for (int i=0; i<counter; i++) {
			list.add(new Product("genre"+i, "id"+i));
		}
		return list;
	}

}
