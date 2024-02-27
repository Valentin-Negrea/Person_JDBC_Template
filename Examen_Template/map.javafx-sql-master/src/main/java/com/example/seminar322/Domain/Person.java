package com.example.seminar322.Domain;

import com.example.seminar322.Domain.Entity;

public class Person extends Entity {


    private String name;

    public Person(int id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

