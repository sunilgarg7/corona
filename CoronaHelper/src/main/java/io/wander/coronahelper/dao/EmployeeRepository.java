package io.wander.coronahelper.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.wander.coronahelper.data.Employee;

// Nothing to mark here
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	
	// By default we dont need to do anything, it will give a lot of template functionality
	
}
