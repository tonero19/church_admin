package com.tony.church.service;

import com.tony.church.entity.ChurchEvent;
import com.tony.church.entity.Department;

import java.util.List;

public interface DepartmentService {

    public List<Department> findAll();

    public Department findById(int id);

    public void save(Department department);

    public void remove(Department department);
}
