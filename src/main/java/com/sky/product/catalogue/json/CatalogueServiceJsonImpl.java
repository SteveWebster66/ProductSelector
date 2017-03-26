package com.sky.product.catalogue.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.product.catalogue.CatalogueService;
import com.sky.product.model.Product;

/**
 *
 * Simple json backed in-memory catalogue implementation
 *
 * @author stevewebster
 *
 */
@Service
public class CatalogueServiceJsonImpl implements CatalogueService {

	private static final String CATALOGUE_LOCATION = "/catalogue/catalogue.json";

	private HashMap<String, List<Product>> catalogue;

	@PostConstruct
    public void initCatalogue() throws IOException {

		InputStream jsonCatalogue = this.getClass().getResourceAsStream(CATALOGUE_LOCATION);

		catalogue = new ObjectMapper().readValue(jsonCatalogue,
				 new TypeReference<HashMap<String, List<Product>>>() {});
	}

	@Override
	public List<Product> findProductsForLocation(String locationId) {
		System.out.println(locationId);
		return catalogue.get(locationId);
	}

}
