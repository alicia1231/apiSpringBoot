package com.example.interview.repositories;

import com.example.interview.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    List<Employee> findAllByDepartmentId(@Param("dpt_id") Long departmentId);
}
