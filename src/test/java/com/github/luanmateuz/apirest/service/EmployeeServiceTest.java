package com.github.luanmateuz.apirest.service;

import com.github.luanmateuz.apirest.exception.BadRequestException;
import com.github.luanmateuz.apirest.model.Employee;
import com.github.luanmateuz.apirest.repository.EmployeeRepository;
import com.github.luanmateuz.apirest.util.EmployeeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @BeforeEach
    public void setup() {
        BDDMockito.when(employeeRepositoryMock.findAll())
                .thenReturn(EmployeeCreator.allEmployees());
        BDDMockito.when(employeeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(EmployeeCreator.employee()));
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

    @Test
    @DisplayName("findById returns a Employee when successful")
    void findById_ReturnsEmployee_WhenSuccessful() {
        Employee employeeServiceById = employeeService.findById(1L);
        Employee employeeExpected = EmployeeCreator.employee();

        Assertions.assertThat(employeeServiceById)
                .isNotNull()
                .isEqualTo(employeeExpected);
    }

    @Test
    @DisplayName("findById throws BadRequestException when Employee not found")
    void findById_ThrowsBadRequestException_WhenEmployeeNotFound() {
        BDDMockito.when(employeeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> employeeService.findById(1L));
    }

    @Test
    @DisplayName("delete removes Employee when successful")
    void delete_RemovesEmployee_WhenSuccessful() {
        Assertions.assertThatCode(() -> employeeService.delete(1L)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete throws BadRequestException when Employee not found")
    void delete_ThrowsBadRequestException_WhenEmployeeNotFound() {
        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> employeeService.delete(ArgumentMatchers.any()));
    }
}