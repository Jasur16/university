package com.test.my_project.service;


import com.test.my_project.entity.Department;
import com.test.my_project.entity.Group;
import com.test.my_project.repository.DepartmentRepository;
import com.test.my_project.repository.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, DepartmentRepository departmentRepository) {
        this.groupRepository = groupRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Group with id " + groupId + " not found"
                ));
    }

    public List<Group> getGroupByDepartmentId(Long departmentId) {
        return groupRepository.findByDepartmentId(departmentId);
    }

    public Group createGroup(Group group, Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department with id " + departmentId + " not found"));
        group.setDepartment(department);
        return groupRepository.save(group);
    }

    public Group updateGroup(Long groupId, Group group) {
        Group oldGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Group with id " + groupId + " not found"
                ));
        oldGroup.setName(group.getName());
        return groupRepository.save(oldGroup);
    }

    public void deleteGroupById(Long groupId) {
        if (!groupRepository.existsById(groupId)) {
            throw new EntityNotFoundException(
                    "Group with id " + groupId + " not found"
            );
        }
        groupRepository.deleteById(groupId);
    }
}
