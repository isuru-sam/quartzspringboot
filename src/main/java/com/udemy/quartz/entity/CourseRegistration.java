package com.udemy.quartz.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="course_registrations")
public class CourseRegistration {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    Person person;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    Course course;
    @Column(name="registered_at")
    LocalDateTime registeredAt;
    @Column
    int grade;
    
    // additional properties
    // standard constructors, getters, and setters
}