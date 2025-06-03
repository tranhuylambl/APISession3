package com.ra.controller;

import com.ra.model.dto.DataError;
import com.ra.model.entity.Employee;
import com.ra.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> list = employeeService.getAllEmployees();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saved = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        Employee updated = employeeService.updateEmployee(id, employee);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataError("Employee not found", 404));
        }
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> changeStatus(@PathVariable Integer id) {
        boolean updated = employeeService.changeStatusEmployee(id);
        if (!updated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataError("Employee not found", 404));
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/remove-from-department")
    public ResponseEntity<?> removeEmployeeFromDepartment(@PathVariable Integer id) {
        boolean removed = employeeService.removeEmployeeFromDepartment(id);
        if (!removed) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataError("Employee not found", 404));
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/change-department/{departmentId}")
    public ResponseEntity<?> changeDepartment(
            @PathVariable Integer id,
            @PathVariable Integer departmentId) {
        Employee updatedEmployee = employeeService.changeDepartment(id, departmentId);
        if (updatedEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataError("Employee or Department not found", 404));
        }
        return ResponseEntity.ok(updatedEmployee);
    }
}
