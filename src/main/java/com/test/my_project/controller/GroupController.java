package com.test.my_project.controller;

import com.test.my_project.entity.Group;
import com.test.my_project.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long groupId) {
        Group group = groupService.getGroupById(groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Group>> getGroupByDepartmentId(@PathVariable Long departmentId){
        List<Group> groups = groupService.getGroupByDepartmentId(departmentId);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PostMapping("/departments/{departmentId}")
    public ResponseEntity<Group> createGroup(@RequestBody Group group, @PathVariable Long departmentId) {
        Group createdGroup = groupService.createGroup(group, departmentId);
        if (createdGroup == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long groupId, @RequestBody Group group) {
        Group updatedGroup = groupService.updateGroup(groupId, group);
        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroupById(groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
