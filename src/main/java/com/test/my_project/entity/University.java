package com.test.my_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "university")
@Data
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;
}
