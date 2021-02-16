package com.corp.indexcalculationservice;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.corp.indexcalculationservice.service.IndexCalculationService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class ExchangeRateServiceUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IndexCalculationService orderService;

 /**	@Test
	public void testGetOrder() throws Exception {

		// given
		BaseCurrencyExchangeRate order = new BaseCurrencyExchangeRate();
		order.setOrderDate(new Date());
		order.setProductCode("LAP");
		order.setOrderNumber(1);
		order.setCustomerName("Jhon");
		order.setShippingAddress("Kolkata India-700091");
		order.setTotal(250);

		given(orderService.getOrder(Mockito.anyLong())).willReturn(order);

		// Get Order
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/order/retrieveOrder/{orderNumber}", "1")
						.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.orderNumber").value(1));

	}

	@Test
	public void testCreateOrder() throws Exception {

		// given
		BaseCurrencyExchangeRate order = new BaseCurrencyExchangeRate();
		order.setOrderDate(new Date());
		order.setProductCode("LAP");
		order.setOrderNumber(1);
		order.setCustomerName("Jhon");
		order.setShippingAddress("Kolkata India-700091");
		order.setTotal(250);

		ConvertCurrenciesRequest createOrderDto = new ConvertCurrenciesRequest();
		createOrderDto.setProductCode("LAP");

		given(orderService.createOrder(createOrderDto)).willReturn(String.valueOf(order.getOrderNumber()));

		// Get Order
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/order/createOrder").content(asJsonString(createOrderDto))
						.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	} **/
}
