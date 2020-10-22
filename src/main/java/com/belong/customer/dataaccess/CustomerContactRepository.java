package com.belong.customer.dataaccess;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.belong.customer.beans.PhoneDetails;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

@Repository
public class CustomerContactRepository {

	private static Multimap<Long, String> customerIdToPhoneNumberMap = Multimaps
			.synchronizedMultimap(ArrayListMultimap.<Long, String>create());
	private static ConcurrentHashMap<String, Boolean> phoneNumberToActiveStatusMap = new ConcurrentHashMap<String, Boolean>();
	static {
		customerIdToPhoneNumberMap.put(1231l, "+61424321333");
		customerIdToPhoneNumberMap.put(1231l, "+61424321334");
		customerIdToPhoneNumberMap.put(1231l, "+61424321365");
		customerIdToPhoneNumberMap.put(1232l, "+61424321400");
		customerIdToPhoneNumberMap.put(1232l, "+61424321401");
		customerIdToPhoneNumberMap.put(1233l, "+61424321500");
		customerIdToPhoneNumberMap.put(1233l, "+61424321501");

		phoneNumberToActiveStatusMap.put("+61424321333", false);
		phoneNumberToActiveStatusMap.put("+61424321334", false);
		phoneNumberToActiveStatusMap.put("+61424321365", true);
		phoneNumberToActiveStatusMap.put("+61424321400", false);
		phoneNumberToActiveStatusMap.put("+61424321401", true);
		phoneNumberToActiveStatusMap.put("+61424321500", false);
		phoneNumberToActiveStatusMap.put("+61424321501", true);

	}

	public List<String> getPhoneNumbersByCustomerId(long customerId) {

		return (List<String>) customerIdToPhoneNumberMap.get(customerId);

	}

	public List<PhoneDetails> getAllPhoneNumbers() {
		List<PhoneDetails> phoneDetailsList = new ArrayList<PhoneDetails>();
		phoneNumberToActiveStatusMap.forEach((phoneNumber, isActive) -> {
			phoneDetailsList.add(new PhoneDetails(phoneNumber, isActive));
		});
		return phoneDetailsList;
	}

	public void updatePhoneNumberStatus(String phoneNumber, boolean newStatus) {
		synchronized (phoneNumberToActiveStatusMap) {
			phoneNumberToActiveStatusMap.put(phoneNumber, newStatus);			
		}
		
	}

}
