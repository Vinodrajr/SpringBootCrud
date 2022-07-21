package com.cl.employee_managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cl.employee_managment.dto.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
