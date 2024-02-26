package com.example.Employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Employee.Employee;
import com.example.Employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(int id) {

		Optional<Employee> employeeOptional = employeeRepository.findById(Integer.valueOf(id));

		return employeeOptional.get();

	}

	public Employee saveEmployee(Employee employee) {

		employee = employeeRepository.save(employee);

		return employee;

	}

}
