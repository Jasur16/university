package com.test.my_project.service;


import com.test.my_project.entity.Group;
import com.test.my_project.entity.Student;
import com.test.my_project.repository.GroupRepository;
import com.test.my_project.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Student with id " + studentId + " not found"
                ));
    }

    public List<Student> getStudentsByGroupId(Long groupId) {
        return studentRepository.findByGroupId(groupId);
    }

    public Student createStudent(Student student, Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group with id " + groupId + " not found"));
        student.setGroup(group);
        return studentRepository.save(student);
    }

    public Student updateStudent(Long studentId, Student student) {
        Student oldStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Student with id " + studentId + " not found"
                ));
        oldStudent.setName(student.getName());
        return studentRepository.save(oldStudent);
    }

    public void deleteStudentById(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new EntityNotFoundException(
                    "Student with id " + studentId + " not found"
            );
        }
        studentRepository.deleteById(studentId);
    }
}
