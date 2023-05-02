package com.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prog.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
