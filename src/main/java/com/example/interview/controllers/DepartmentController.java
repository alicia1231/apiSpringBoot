package com.example.interview.controllers;

import com.example.interview.models.Department;
import com.example.interview.services.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Department department) {
        return new ResponseEntity(departmentService.add(department), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id) {
        return new ResponseEntity(departmentService.findOne(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,@Valid @RequestBody Department department) {
        return new ResponseEntity(departmentService.modify(id,department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Map map = new HashMap();
        map.put("id",departmentService.remove(id));
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
