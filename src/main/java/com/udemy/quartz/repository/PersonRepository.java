package com.udemy.quartz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.quartz.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	
}