package com.belong.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomerServiceExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String customerNotFoundHandler(DataNotFoundException ex) {
		log.error("Data not found for requested resource", ex);
		return ex.getMessage();
	}
	

	@ResponseBody
	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String customerIdInvalidFormat(NumberFormatException ex) {
		log.error("Expecting a number for customer id", ex);
		return "Invalid customer id expecting a number";
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String customerNotFoundHandler(Exception ex) {
		log.error("Unexepected exception occured", ex);
		return "Unexpected error while handling the request";
	}

}
