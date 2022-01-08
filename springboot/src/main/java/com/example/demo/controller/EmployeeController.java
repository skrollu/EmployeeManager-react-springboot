package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.jpa.Employee;
import com.example.demo.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist with this id " + id));
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		Employee employeeToUpdate = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist with this id " + id));
		employeeToUpdate.setFirstName(employee.getFirstName());
		employeeToUpdate.setLastName(employee.getLastName());
		employeeToUpdate.setEmailId(employee.getEmailId());
		Employee employeeUpdated = this.employeeRepository.save(employeeToUpdate);
		return ResponseEntity.ok(employeeUpdated);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Employee employeeToDelete = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist with this id " + id));
		employeeRepository.delete(employeeToDelete);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
