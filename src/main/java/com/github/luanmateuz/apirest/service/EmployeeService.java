package com.github.luanmateuz.apirest.service;

import com.github.luanmateuz.apirest.exception.BadRequestException;
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

    public Employee findById(Long id) {
        return this.employeeRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException(String.format("Could not found Employee [%d]", id)));
    }

    public void delete(Long id) {
        this.employeeRepository.delete(findById(id));
    }
}
