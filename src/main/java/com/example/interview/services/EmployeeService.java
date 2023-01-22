package com.example.interview.services;

import com.example.interview.models.Employee;

import java.net.URISyntaxException;
import java.util.List;


public interface EmployeeService {


    Employee add(Employee employee) throws URISyntaxException;

    Employee findOne(String id);

    Employee modify(String id, Employee employee);

    String remove(String id);

    List<Employee> findAllByDepartmentId(Long id);

    Double calculateAverageSalaryByDepartmentId(Long id);
}
