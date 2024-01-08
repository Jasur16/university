package com.test.my_project.repository;
import com.test.my_project.entity.Faculty;
import com.test.my_project.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByDepartmentId(Long departmentId);
}
