package com.github.luanmateuz.apirest.controller;

import com.github.luanmateuz.apirest.model.Employee;
import com.github.luanmateuz.apirest.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee", description = "Endpoints for managing Employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @Operation(
            summary = "find all employee",
            method = "findAll",
            description = "Find all employees in database",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)))
            })
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity
                .ok()
                .body(this.employeeService.findAll());
    }

    @GetMapping(value = "{id}")
    @Operation(
            summary = "find employee by id",
            method = "findById",
            description = "Find employee by id in database",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
            }
    )
    public ResponseEntity<Employee> findById(
            @Parameter(
                    description = "The id of the employee to find.",
                    example = "1")
            @PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(this.employeeService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "save employee",
            method = "save",
            description = "Save employee in database",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Already exists", content = @Content)
            }
    )
    public ResponseEntity<Employee> save(
            @Parameter(
                    description = "Employee to add. Name cannot be Blank, Empty or Null",
                    required = true,
                    schema = @Schema(implementation = Employee.class))
            @RequestBody @Valid Employee employee) {
        return ResponseEntity
                .created(URI.create("/"))
                .body(this.employeeService.save(employee));
    }

    @PutMapping
    @Operation(
            summary = "update employee",
            method = "update",
            description = "Update employee in database",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Success", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
            }
    )
    public ResponseEntity<Void> update(
            @Parameter(
                    description = "Employee to update. Id, and Name cannot be Blank, Empty or Null",
                    required = true,
                    schema = @Schema(implementation = Employee.class))
            @RequestBody @Valid Employee employee) {
        this.employeeService.update(employee);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping(value = "{id}")
    @Operation(
            summary = "delete employee",
            method = "delete",
            description = "Remove employee in database",
            tags = {"Employee"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Success", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
            }
    )
    public ResponseEntity<Void> delete(
            @Parameter(
                    description = "Id for delete employee, cannot be Blank, Empty or Null",
                    required = true,
                    schema = @Schema(description = "Id from employee", example = "1"))
            @PathVariable Long id) {
        this.employeeService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
