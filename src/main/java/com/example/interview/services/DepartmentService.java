package com.example.interview.services;

import com.example.interview.models.Department;

public interface DepartmentService {

    Department add(Department department);

    Department findOne(Long id);

    Department modify(Long id, Department department);

    Long remove(Long id);
}
