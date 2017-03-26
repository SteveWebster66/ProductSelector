package com.sky.product.location;

import org.springframework.stereotype.Service;

/**
 * Basic stub for location service
 * none - no location
 * dave = liverpool
 * else london
 *
 * @author stevewebster
 *
 */
@Service
public class LocationStub implements LocationService {

	@Override
	public String findCustomersLocation(String customerId) {

		if ((customerId == null) || (customerId.trim().isEmpty())) {
			return null;
		}

		if ("dave".equalsIgnoreCase(customerId)) {
			return "LIVERPOOL";
		} else {
		   return "LONDON";
		}
	}

}
