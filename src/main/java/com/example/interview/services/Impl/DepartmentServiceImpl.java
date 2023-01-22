package com.example.interview.services.Impl;

import com.example.interview.exceptions.ResourceNotFoundException;
import com.example.interview.models.Department;
import com.example.interview.repositories.DepartmentRepository;
import com.example.interview.repositories.EmployeeRepository;
import com.example.interview.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Department add(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department findOne(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> {throw new ResourceNotFoundException("Department with id " + id+ " not found");});
    }

    @Override
    public Department modify(Long id, Department department) {
        Department departmentDB = findOne(id);
        departmentDB.setName(department.getName());
        return departmentRepository.save(departmentDB);
    }

    @Override
    public Long remove(Long id) {
        findOne(id);
        if(employeeRepository.findAllByDepartmentId(id).isEmpty()) {
            departmentRepository.deleteById(id);
            return id;
        } else {
            throw new RuntimeException("It's not possible to delete department with id " + id + ". You must delete employee before.");
        }
    }
}
