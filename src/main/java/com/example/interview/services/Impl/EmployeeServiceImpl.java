package com.example.interview.services.Impl;

import com.example.interview.exceptions.ResourceNotFoundException;
import com.example.interview.models.Employee;
import com.example.interview.repositories.EmployeeRepository;
import com.example.interview.services.EmployeeService;
import com.example.interview.services.IdGeneratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.IntStream;


@Service
public class EmployeeServiceImpl extends IdGeneratorService implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Double ZERO = (double) 0;

    protected EmployeeServiceImpl(RestTemplate restTemplate, EmployeeRepository employeeRepository) throws URISyntaxException {
        super(restTemplate);
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee add(Employee employee) {
        if(employee.getSalary() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee salary must be positive or zero");
        }
        ResponseEntity<List> response = restTemplate.getForEntity(URI, List.class);
        employee.setId(response.getBody().get(0).toString());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findOne(String id) {
        return employeeRepository.findById(id).orElseThrow(() -> {throw new ResourceNotFoundException("Employee with id " + id+ " not found");});
    }

    @Override
    public Employee modify(String id, Employee employee) {
        Employee employeeDB = findOne(id);
        employeeDB.setName(employee.getName());
        employeeDB.setSurname(employee.getSurname());
        employeeDB.setSalary(employee.getSalary());
        employeeDB.setDepartment(employee.getDepartment());

        return employeeRepository.save(employeeDB);
    }

    @Override
    public String remove(String id) {
        findOne(id);
        employeeRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Employee> findAllByDepartmentId(Long id) {
        return employeeRepository.findAllByDepartmentId(id);
    }

    @Override
    public Double calculateAverageSalaryByDepartmentId(Long id) {
        List<Employee> employees = findAllByDepartmentId(id);
        IntStream employeesSalaries= employees.stream().mapToInt(Employee::getSalary);

        return BigDecimal.valueOf(employeesSalaries.average().orElse(ZERO))
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
