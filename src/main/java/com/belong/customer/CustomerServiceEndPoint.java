package com.belong.customer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.belong.customer.beans.Customer;
import com.belong.customer.beans.PhoneDetails;
import com.belong.customer.dataaccess.CustomerContactRepository;

@RestController
public class CustomerServiceEndPoint {

	@Autowired
	private CustomerContactRepository customerContactRepository;
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceExceptionHandler.class);

	@GetMapping("/customer/{id}/phoneNumbers")
	public Customer getPhoneNumbersByCustomerId(@PathVariable long id) {
		List<String> phoneNumbers = customerContactRepository.getPhoneNumbersByCustomerId(id);
		if (phoneNumbers.isEmpty()) {
			throw new DataNotFoundException("Customer not found for id " + id);
		}
		Customer cust = new Customer();
		cust.setCustomerId(id);
		cust.setPhoneNumbers(phoneNumbers);
		log.info("Returned {} phonenumbers for customer {}", cust.getPhoneNumbers().size(), id);
		return cust;
	}

	@GetMapping("/phoneNumbers")
	public List<PhoneDetails> getPhoneNumbers() {
		List<PhoneDetails> phoneDetails = customerContactRepository.getAllPhoneNumbers();
		if (phoneDetails == null) {
			throw new DataNotFoundException("No phone numbers present");
		}
		log.info("Returned {} phonenumbers ", phoneDetails.size());
		return phoneDetails;
	}

	@PostMapping("/phoneNumbers/activate")
	public void activatePhoneNumber(@RequestBody PhoneDetails phoneDetails) {
		customerContactRepository.updatePhoneNumberStatus(phoneDetails.getPhoneNumber(), true);
		log.info("Activated phone number {}", phoneDetails.getPhoneNumber());

	}

}
