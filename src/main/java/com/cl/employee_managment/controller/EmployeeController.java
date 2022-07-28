package com.cl.employee_managment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cl.employee_managment.dto.Employee;
import com.cl.employee_managment.service.EmployeeService;
import com.cl.employee_managment.utill.ResponseStructure;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@PostMapping("/employee")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}

	@DeleteMapping("/employee")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(@RequestParam int id) {
		return service.deleteEmployee(id);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<ResponseStructure<Employee>> getEmployeeById(@PathVariable int id) {
		return service.getEmployeeById(id);
	}

	@GetMapping("/employee")
	public ResponseEntity<ResponseStructure<List<Employee>>> findAllEmployees() {
		return service.findAllEmployees();
	}

	@PutMapping("/employee")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestBody Employee employee,
			@RequestParam int id) {
		return service.udpateEmployee(employee, id);
	}
}
