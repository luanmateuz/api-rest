package com.github.luanmateuz.apirest.service;

import com.github.luanmateuz.apirest.model.Employee;
import com.github.luanmateuz.apirest.repository.EmployeeRepository;
import com.github.luanmateuz.apirest.util.EmployeeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @BeforeEach
    public void setup() {
        BDDMockito.when(employeeService.findAll()).thenReturn(EmployeeCreator.allEmployees());
    }

    @Test
    @DisplayName("findAll returns a list of Employees when successful")
    void findAll_ReturnsListOfEmployees_WhenSuccessful() {
        List<Employee> employeeList = employeeService.findAll();

        List<String> names = EmployeeCreator.allEmployees()
                .stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        Assertions.assertThat(employeeList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .extracting(Employee::getName)
                .containsAll(names);
    }
}