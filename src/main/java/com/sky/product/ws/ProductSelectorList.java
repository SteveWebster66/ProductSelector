package com.sky.product.ws;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.sky.product.model.Product;

/**
 * Simple transform class to split products into unique genres,
 * and a product map with a genre key.
 *
 * It us used to in client view.
 *
 * @author stevewebster
 *
 */
public class ProductSelectorList {

	private Map<String, List<Product>> productsByGenres;

	private Set<String> genres;

	public void loadCatalogue(List<Product> products) {

		genres = findUniqueGenres(products);

		splitProductsByGenre(genres, products);
	}

	public List<Product> getProductsByGenres(String key) {
		return productsByGenres.get(key);
	}

	public Map<String, List<Product>> getProductsByGenres() {
		return productsByGenres;
	}

	public void setProductsByGenres(Map<String, List<Product>> productsByGenres) {
		this.productsByGenres = productsByGenres;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	private void splitProductsByGenre(Set<String> genresSet, List<Product> products) {

		productsByGenres = new HashMap<String, List<Product>>();

		for (String key : genresSet) {

			productsByGenres.put(key, products.stream()
				    .filter( p -> key.equals(p.getGenre()))
				    .collect(Collectors.toList()));
		}
	}

	private Set<String> findUniqueGenres(final List<Product> products) {

	    return new HashSet<String>(products.stream().
	    		                  map(e -> e.getGenre())
	    		                  .collect(Collectors.toSet()));
	}

}
