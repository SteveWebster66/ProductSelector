package com.sky.product.location;


public interface LocationService {

	/**
	 * Find the given customers location.
	 *
	 * @param customerId
	 * @return locationId
	 */
	public String findCustomersLocation(String customerId);
}
