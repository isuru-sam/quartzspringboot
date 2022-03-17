package com.udemy.quartz.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Person {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="firstname")
    private String name;

    @Column(name= "surname")
    private String surname;

    @Column(name="age")
    private int age;

    @OneToMany(mappedBy="person",cascade=CascadeType.ALL)
    private List<Item> items=new ArrayList<>();
	
}
