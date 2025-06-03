package com.ra.service.employee;

import com.ra.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Integer id, Employee employee);
    
    boolean changeStatusEmployee(Integer id);

    boolean removeEmployeeFromDepartment(Integer employeeId);

    Employee changeDepartment(Integer employeeId, Integer departmentId);
}
