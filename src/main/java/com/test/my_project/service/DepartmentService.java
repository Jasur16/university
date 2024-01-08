package com.test.my_project.service;


import com.test.my_project.entity.Department;
import com.test.my_project.entity.Faculty;
import com.test.my_project.repository.DepartmentRepository;
import com.test.my_project.repository.FacultyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Department with id " + departmentId + " not found"
                ));
    }

    public List<Department> getDepartmentByFacultyId(Long facultyId){
        return departmentRepository.findByFacultyId(facultyId);
    }

    public Department createDepartment(Department department, Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new EntityNotFoundException("Faculty with id " + facultyId + " not found"));
        department.setFaculty(faculty);
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long departmentId, Department department) {
        Department oldDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department with id " + departmentId + " not found"));
        oldDepartment.setName(department.getName());
    return departmentRepository.save(oldDepartment);
    }

    public void deleteDepartmentById(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new EntityNotFoundException("Department with id " + departmentId + " not found");
        }
        departmentRepository.deleteById(departmentId);
    }
}
