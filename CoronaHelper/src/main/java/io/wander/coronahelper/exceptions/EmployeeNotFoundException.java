package io.wander.coronahelper.exceptions;

class EmployeeNotFoundException extends RuntimeException {

	  EmployeeNotFoundException(Long id) {
	    super("Could not find employee " + id);
	  }
	}