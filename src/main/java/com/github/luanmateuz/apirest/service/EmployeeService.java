package com.github.luanmateuz.apirest.service;

import com.github.luanmateuz.apirest.model.Employee;
import com.github.luanmateuz.apirest.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }
}
