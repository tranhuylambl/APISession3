package com.ra.service.employee;

import com.ra.model.entity.Department;
import com.ra.model.entity.Employee;
import com.ra.repository.DepartmentRepository;
import com.ra.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            Employee exist = opt.get();
            exist.setFullName(employee.getFullName());
            exist.setAddress(employee.getAddress());
            exist.setPhone(employee.getPhone());
            exist.setStatus(employee.getStatus());
            exist.setDepartment(employee.getDepartment());
            return employeeRepository.save(exist);
        }
        return null;
    }

    @Override
    public boolean changeStatusEmployee(Integer id) {
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            Employee emp = opt.get();
            emp.setStatus(!emp.getStatus());
            employeeRepository.save(emp);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEmployeeFromDepartment(Integer employeeId) {
        Optional<Employee> opt = employeeRepository.findById(employeeId);
        if (opt.isPresent()) {
            Employee emp = opt.get();
            emp.setDepartment(null);
            employeeRepository.save(emp);
            return true;
        }
        return false;
    }


    @Override
    public Employee changeDepartment(Integer employeeId, Integer departmentId) {
        Optional<Employee> empOpt = employeeRepository.findById(employeeId);
        Optional<Department> depOpt = departmentRepository.findById(departmentId);
        if (empOpt.isPresent() && depOpt.isPresent()) {
            Employee emp = empOpt.get();
            emp.setDepartment(depOpt.get());
            return employeeRepository.save(emp);
        }
        return null;
    }

}
