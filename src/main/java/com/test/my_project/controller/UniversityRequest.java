package com.test.my_project.controller;

class UniversityRequest {
    private String name;
    private Long regionId;

    public UniversityRequest(String name, Long regionId) {
        this.name = name;
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }
}
