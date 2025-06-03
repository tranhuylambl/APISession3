package com.ra.service.department;

import com.ra.model.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();

    Department saveDepartment(Department department);

    Department updateDepartment(Integer id, Department department);

    boolean changeStatusDepartment(Integer id);
}