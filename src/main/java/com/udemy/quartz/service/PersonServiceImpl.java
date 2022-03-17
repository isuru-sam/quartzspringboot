package com.udemy.quartz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.quartz.entity.Person;
import com.udemy.quartz.repository.PersonRepository;
@Service
public class PersonServiceImpl implements PersonService{
@Autowired
private PersonRepository personRepository;
	
	@Override
	public void addPerson(Person p) {
		personRepository.save(p);
		
	}

}
