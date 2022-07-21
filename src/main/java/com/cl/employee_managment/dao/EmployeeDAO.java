package com.cl.employee_managment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cl.employee_managment.dto.Employee;
import com.cl.employee_managment.repository.EmployeeRepository;
import com.fasterxml.jackson.annotation.OptBoolean;

@Repository
public class EmployeeDAO {

	@Autowired
	private EmployeeRepository repository;

	// save
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	// update
	public Employee updateEmployee(Employee employee, int id) {
		if (repository.findById(id).isEmpty()) {
			return null;
		} else {
			employee.setId(id);
			return 	repository.save(employee);
		}
	}

	// delete
	public Employee deleteEmployee(int id) {
		Employee employee=getEmployeeById(id).get();
		repository.delete(employee);
		return employee;
		
	}

	// getByID
	public Optional<Employee> getEmployeeById(int id) {
		return repository.findById(id);
	}

	// find All Employee
	public List<Employee> findAllEmployee() {
		return repository.findAll();
	}

}
