package com.example.interview.controllers;

import com.example.interview.models.Employee;
import com.example.interview.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Employee employee) throws URISyntaxException {
        return new ResponseEntity(employeeService.add(employee), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") String id) {
        return new ResponseEntity(employeeService.findOne(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id,@Valid @RequestBody Employee employee) {
        return new ResponseEntity(employeeService.modify(id,employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        Map map = new HashMap();
        map.put("id",employeeService.remove(id));
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity getAllByDepartmentId(@PathVariable("id") Long id) {
        return new ResponseEntity(employeeService.findAllByDepartmentId(id), HttpStatus.OK);
    }

    @GetMapping("/department/{id}/average-salary")
    public ResponseEntity getAverageSalaryByDepartment(@PathVariable("id") Long id) {
        Map map = new HashMap();
        map.put("averageSalary",employeeService.calculateAverageSalaryByDepartmentId(id));
        return new ResponseEntity(map, HttpStatus.OK);
    }

}
