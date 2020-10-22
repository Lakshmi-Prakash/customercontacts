package com.belong.customer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerServiceEndPointTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetPhoneNumbersByCustomerId() throws Exception {

		mockMvc.perform(get("/customer/1231/phoneNumbers")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.phoneNumbers[0]").value("+61424321333"))
				.andExpect(jsonPath("$.phoneNumbers[1]").value("+61424321334"))
				.andExpect(jsonPath("$.phoneNumbers[2]").value("+61424321365"));
	}

	@Test
	public void testActivatePhoneNumber() throws Exception {
		
	 mockMvc.perform(get("/phoneNumbers")).andDo(print()).andExpect(status().isOk())
		  .andExpect(jsonPath("[2].phoneNumber").value("+61424321500"))
		  .andExpect(jsonPath("[2].active").value(false));
	  
	  mockMvc.perform(post("/phoneNumbers/activate")
	           .contentType(MediaType.APPLICATION_JSON)
	           .content("{ \"phoneNumber\": \"+61424321500\" }") 
	           .accept(MediaType.APPLICATION_JSON))
	           .andExpect(status().isOk());
	  
	  mockMvc.perform(get("/phoneNumbers")).andDo(print()).andExpect(status().isOk())
	  .andExpect(jsonPath("[2].phoneNumber").value("+61424321500"))
	  .andExpect(jsonPath("[2].active").value(true));
	  
	  
	}
	 


}
