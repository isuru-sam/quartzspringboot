package com.udemy.quartz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.quartz.entity.CourseRegistration;
import com.udemy.quartz.entity.Person;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Integer>{

}
