package com.github.luanmateuz.apirest.repository;

import com.github.luanmateuz.apirest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
