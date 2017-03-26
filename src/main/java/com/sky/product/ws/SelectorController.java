package com.sky.product.ws;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.sky.product.catalogue.CatalogueService;
import com.sky.product.location.LocationService;
import com.sky.product.model.Product;

@Controller
public class SelectorController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private CatalogueService catalogueService;

	/**
	 * For demo simplicity takes customerId from cookie or param.
	 *
	 * @param customerId
	 * @param model
	 * @return
	 */
	@GetMapping("/selector")
	public String selector(
			@RequestParam(value = "customerId", required = false) String customerIdParam, Model model,
			HttpServletRequest request) {

		String customerId = findFromCookie(customerIdParam, request);

		String locationId = lookupCustomerLocationFromId(customerId);

		List<Product> products = findProductsForLocation(locationId);

		if (products.isEmpty()) {
			return "404";
		} else {
			loadSelectorModel(customerId, model, products);
			return "selector";
		}
    }

	@PostMapping("/purchase")
	public String purchase(@ModelAttribute PurchasedList products, ModelMap model ) {

		// Simple stubb mapping, informing client which products have been purchased.
		model.addAttribute("customerId", products.getCustomerId());
		if (!products.getProducts().isEmpty()) {
			model.addAttribute("msg", "thank-you very much for your purchase - you now have access to the following channels:");
		} else {
			model.addAttribute("msg", "you have selected no products.");
		}

		model.addAttribute("purchased", products.getProducts());

        return "confirm";
    }

	private void loadSelectorModel(String customerId, Model model, List<Product> products) {

		ProductSelectorList clientProducts = new ProductSelectorList();

		clientProducts.loadCatalogue(products);

		model.addAttribute("msg", "please select which channels you wish to purchase.");
        model.addAttribute("customerId", customerId);
        model.addAttribute("catalog", clientProducts);
        model.addAttribute("purchasedList", new PurchasedList());
	}

	private List<Product> findProductsForLocation(String locationId) {

		if (locationId == null) {
			return new ArrayList<Product>();
		}
		return catalogueService.findProductsForLocation(locationId);
	}

	private String lookupCustomerLocationFromId(String customerId) {
		if (customerId == null) {
			return null;
		}
		return locationService.findCustomersLocation(customerId);
	}


	private String findFromCookie(String customerIdParam, HttpServletRequest request) {

		Cookie IdfromCookie = WebUtils.getCookie(request, "customerId");

		if (IdfromCookie == null) {
			return customerIdParam;
		}
		return IdfromCookie.getValue();
	}
}
