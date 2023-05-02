package com.prog.controller;

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

import com.prog.exception.ResourceNotFoundException;
import com.prog.model.Employee;
import com.prog.repository.EmployeeRepository;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get all employee
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	//create employee 
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
			
	}
	
	//get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity <Employee> getEmployeeById( @PathVariable Long id) {
		
		Employee employee=employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("employee not found "));
		return ResponseEntity.ok(employee);
		
	}
	
	//update employee 
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id , @RequestBody Employee employeeDetails) {
		
		Employee employee=employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("employee not found "));
		
		employee.setFirst_name(employeeDetails.getFirst_name());
		employee.setLast_name(employeeDetails.getLast_name());
		employee.setEmail(employeeDetails.getEmail());
		
		Employee upEmployee =employeeRepository.save(employee);
		return ResponseEntity.ok(upEmployee);	
	}
	
	//delete employee 
	@DeleteMapping("/employees/{id}") 
	public ResponseEntity<Map<String,Boolean>> deleteEmployeeById(@PathVariable Long id) {
		
		Employee employee=employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("employee not found "));
		employeeRepository.delete(employee);
		Map<String, Boolean> response=new HashMap<>();
		response.put("Deleted" ,Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	
//	@DeleteMapping("/employees/{id}") 
//	public void deleteEmployee(@PathVariable("id") Long id) {
//		employeeRepository.deleteById(id);
//	}
		

}
