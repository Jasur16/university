package com.test.my_project.controller;

import com.test.my_project.entity.Department;
import com.test.my_project.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long departmentId){
        Department department = departmentService.getDepartmentById(departmentId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<Department>> getDepartmentByFacultyId(@PathVariable Long facultyId) {
        List<Department> departments = departmentService.getDepartmentByFacultyId(facultyId);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PostMapping("/faculties/{facultyId}")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department, @PathVariable Long facultyId) {
        Department createdDepartment = departmentService.createDepartment(department, facultyId);
        if (createdDepartment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long departmentId, @RequestBody Department department) {
        Department updateDEpartment = departmentService.updateDepartment(departmentId, department); {
            return new ResponseEntity<>(updateDEpartment, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
