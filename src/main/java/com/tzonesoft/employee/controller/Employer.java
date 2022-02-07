package com.tzonesoft.employee.controller;

public class Employer {
    private String name;
    private Integer id;

    public Employer(String name, Integer id){
        this.name= name;
        this.id= id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

