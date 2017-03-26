package com.sky.product.ws;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.sky.product.catalogue.CatalogueService;
import com.sky.product.location.LocationService;
import com.sky.product.model.Product;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SelectorController.class)
public class SelectorControllerTest {

	@Autowired
    private MockMvc mockMvc;

	@MockBean
    private CatalogueService catalogueService;

	@MockBean
    private LocationService locationService;

	@Before
    public void setupMock() {
        when(locationService.findCustomersLocation(isA(String.class)))
            .thenReturn("LIVERPOOL");
        when(catalogueService.findProductsForLocation(isA(String.class)))
        .thenReturn(buildProductsFor("LIVERPOOL"));
    }

	@Test
    public void loadSelectorPageWithValidCustomerId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/selector?customerId=dave"))
               .andExpect(MockMvcResultMatchers.model().hasNoErrors())
               .andExpect(MockMvcResultMatchers.model().attributeExists("catalog"))
               .andExpect(MockMvcResultMatchers.model().attributeExists("msg"))
               .andExpect(MockMvcResultMatchers.view().name("selector"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

	@Test
    public void loadSelectorPageWithNoCustomerId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/selector"))
               .andExpect(MockMvcResultMatchers.view().name("404"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }


	private List<Product> buildProductsFor(String string) {
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(new Product("genre1", "id"));
		return products;
	}
}
