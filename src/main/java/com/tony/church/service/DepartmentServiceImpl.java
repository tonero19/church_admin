package com.tony.church.service;

import com.tony.church.dao.DepartmentRepository;
import com.tony.church.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {

        return departmentRepository.findAll();
    }

    @Override
    public Department findById(int id) {
        Optional<Department> result  = departmentRepository.findById(id);

        if(result.isPresent())
            return result.get();

        return null;
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void remove(Department department) {
        departmentRepository.delete(department);
    }
}
