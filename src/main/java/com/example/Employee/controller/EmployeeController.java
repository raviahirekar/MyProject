package com.example.Employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee.Employee;
import com.example.Employee.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/Employee")
	@PreAuthorize("hasRole('view-users')")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@GetMapping("/Employee/{id}")
	public Employee getAllEmployee(@PathVariable int id) {
		
		Employee employee = employeeService.getEmployeeById(id);
		if(employee == null) {
			throw new EntityNotFoundException();
		}
		return employee;
	}
	
	@PostMapping("/Employee")
	@PreAuthorize("hasRole('write-users')")
	public ResponseEntity<Employee> getAllEmployee(@RequestBody Employee employee) {
		
		employeeService.saveEmployee(employee);
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		return response;
		
	}

}
