package com.github.luanmateuz.apirest.util;

import com.github.luanmateuz.apirest.model.Employee;

import java.util.Arrays;
import java.util.List;

public class EmployeeCreator {

    public static List<Employee> allEmployees() {
        Employee employee1 = new Employee(1L, "Luan", "Developer");
        Employee employee2 = new Employee(2L, "Joao", "Developer");
        Employee employee3 = new Employee(3L, "Natalia", "Network Manager");
        Employee employee4 = new Employee(4L, "Mateus", "TechLead");
        Employee employee5 = new Employee(5L, "Jose", "QA Tester");

        return Arrays.asList(employee1, employee2, employee3, employee4, employee5);
    }
}
