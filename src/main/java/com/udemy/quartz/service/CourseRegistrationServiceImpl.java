package com.udemy.quartz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.quartz.entity.CourseRegistration;
import com.udemy.quartz.repository.CourseRegistrationRepository;
@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService{
@Autowired
	CourseRegistrationRepository courseRegistrationRepository;
	@Override
	public void addCourseRegistration(CourseRegistration c) {
		// TODO Auto-generated method stub
		courseRegistrationRepository.save(c);
	}

}
