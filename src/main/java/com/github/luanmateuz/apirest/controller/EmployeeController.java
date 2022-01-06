package com.github.luanmateuz.apirest.controller;

import com.github.luanmateuz.apirest.model.Employee;
import com.github.luanmateuz.apirest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity
                .ok()
                .body(this.employeeService.findAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(this.employeeService.findById(id));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.employeeService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
