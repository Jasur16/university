package com.test.my_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "faculties")
@Data
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;
}
