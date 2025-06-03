package com.ra.service.department;

import com.ra.model.entity.Department;
import com.ra.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Integer id, Department department) {
        Optional<Department> opt = departmentRepository.findById(id);
        if (opt.isPresent()) {
            Department exist = opt.get();
            exist.setName(department.getName());
            exist.setStatus(department.getStatus());
            return departmentRepository.save(exist);
        }
        return null;
    }

    @Override
    public boolean changeStatusDepartment(Integer id) {
        Optional<Department> opt = departmentRepository.findById(id);
        if (opt.isPresent()) {
            Department dep = opt.get();
            dep.setStatus(!dep.getStatus());
            departmentRepository.save(dep);
            return true;
        }
        return false;
    }

}