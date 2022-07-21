package com.cl.employee_managment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cl.employee_managment.dao.EmployeeDAO;
import com.cl.employee_managment.dto.Employee;
import com.cl.employee_managment.exception.IdNotFoundException;
import com.cl.employee_managment.utill.ResponseStructure;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Stubbing;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDAO dao;

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {
		if (employee.getSalary() > 10000) {
			employee.setGrade("B");
		} else {
			employee.setGrade("A");
		}

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setMessage("Employee Saved Sucessfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setT(dao.saveEmployee(employee));
		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Employee>> udpateEmployee(Employee employee, int id) {
		if (employee.getSalary() > 10000) {
			employee.setGrade("B");
		} else {
			employee.setGrade("A");
		}
		Employee emp = dao.updateEmployee(employee, id);
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (emp != null) {
			structure.setMessage("Updated Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(emp);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
			structure.setMessage("Invalid ID");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setT(null);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> getEmployeeById(int id) {
		Optional<Employee> optional = dao.getEmployeeById(id);
		if (optional.isEmpty()) {
			throw new IdNotFoundException();
		} else {
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Employee Found Sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		}

	}

	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(int id) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setMessage("Employee Found Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.deleteEmployee(id));
		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findAllEmployees() {
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		structure.setMessage("Employee Found Sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(dao.findAllEmployee());
		return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.OK);
	}

}
