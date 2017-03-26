package com.sky.product.catalogue;

import java.util.List;
import com.sky.product.model.Product;

public interface CatalogueService {

	/**
	 * look up list of products for a given location.
	 *
	 * @param locationId
	 * @return lisy of products
	 */
	public List<Product> findProductsForLocation(String locationId);

}
