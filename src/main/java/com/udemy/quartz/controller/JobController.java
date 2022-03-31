package com.udemy.quartz.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.quartz.entity.Course;
import com.udemy.quartz.entity.CourseRegistration;
import com.udemy.quartz.entity.Item;
import com.udemy.quartz.entity.Person;
import com.udemy.quartz.entity.SchedulerJobInfo;
import com.udemy.quartz.job.SimpleJob;
import com.udemy.quartz.job.TestJob;
import com.udemy.quartz.model.AuthenticationRequest;
import com.udemy.quartz.model.AuthenticationResponse;
import com.udemy.quartz.service.AppUserDetailsService;
import com.udemy.quartz.service.CourseRegistrationService;
import com.udemy.quartz.service.JWUtil;
import com.udemy.quartz.service.PersonService;
import com.udemy.quartz.service.SchedulerJobService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//codejava.net/frameworks/hibernate/hibernate-many-to-many-association-with-extra-columns-in-join-table-example
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class JobController {

	private final SchedulerJobService scheduleJobService;
	@Autowired
	private PersonService personService;
	@Autowired
	private CourseRegistrationService courseRegistrationService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWUtil jwtTokenUtil;

	@Autowired
	private AppUserDetailsService userDetailsService;

	
	@GetMapping(path = "/hello")
	public String hello() {
		log.debug("Debug");
		log.info("Hello");
	return "hello";
	}
	
	
	@PostMapping(path = "/schedule")
	public void schdulejob() {
		SchedulerJobInfo j=new SchedulerJobInfo();
		j.setCronJob(false);
		j.setJobClass(TestJob.class.getName());
		j.setJobGroup("test");
		j.setJobId("1");
		j.setJobName("TestJob");
		j.setJobStatus("e");
		j.setRepeatTime(1l);
		
		scheduleJobService.scheduleNewJob(j);	
		
	}
	
	@PostMapping(path = "/addperson")
	public void addPerson() {
		Person p =new Person();
		p.setName("Isuru");
		p.setSurname("Sam");
		p.setAge(100);
		personService.addPerson(p);
	}
	
	@PostMapping(path = "/addpersonitems")
	public void addPersonItems() {
		Person p =new Person();
		p.setName("Isuru");
		p.setSurname("Sam");
		p.setAge(100);
		List<Item> items=new ArrayList<>();
		Item i=new Item();
		//i.setId(1);
		i.setItem_code("abc");
		i.setPerson(p);
		
		Item i1=new Item();
		//i1.setId(1);
		//i1.setPerson(person);
		i1.setItem_code("def");
		i1.setPerson(p);
		items.add(i);
		items.add(i1);
		p.setItems(items);
		personService.addPerson(p);
	}
	
	
	@PostMapping(path = "/addpersoncourse")
	public void addPersoncourse() {
		Person p =new Person();
		p.setName("Isuru");
		p.setSurname("Sam");
		p.setAge(100);
		List<Item> items=new ArrayList<>();
		Item i=new Item();
		//i.setId(1);
		i.setItem_code("abc");
		i.setPerson(p);
		
		Item i1=new Item();
		//i1.setId(1);
		//i1.setPerson(person);
		i1.setItem_code("def");
		i1.setPerson(p);
		items.add(i);
		items.add(i1);
		p.setItems(items);
		
		Course cs=new Course();
		cs.setName("Maths");
		cs.setId(25);
		List<CourseRegistration> crlist=new ArrayList<>();
		CourseRegistration c=new CourseRegistration();
		c.setGrade(56);
		c.setRegisteredAt(LocalDateTime.now());
		c.setPerson(p);
		c.setCourse(cs);
		
		
		CourseRegistration c1=new CourseRegistration();
		c1.setGrade(516);
		c1.setRegisteredAt(LocalDateTime.now());
		c1.setPerson(p);
		c1.setCourse(cs);
		
		cs.setRegistrations(crlist);
		
		p.setRegistrations(crlist);
		courseRegistrationService.addCourseRegistration(c1);
		//personService.addPerson(p);
	}
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
		Authentication auth=	authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}


	/*@RequestMapping(value = "/saveOrUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public Object saveOrUpdate(SchedulerJobInfo job) {
		log.info("params, job = {}", job);
		Message message = Message.failure();
		try {
			scheduleJobService.saveOrupdate(job);
			message = Message.success();
		} catch (Exception e) {
			message.setMsg(e.getMessage());
			log.error("updateCron ex:", e);
		}
		return message;
	}

	@RequestMapping("/metaData")
	public Object metaData() throws SchedulerException {
		SchedulerMetaData metaData = scheduleJobService.getMetaData();
		return metaData;
	}

	@RequestMapping("/getAllJobs")
	public Object getAllJobs() throws SchedulerException {
		List<SchedulerJobInfo> jobList = scheduleJobService.getAllJobList();
		return jobList;
	}

	@RequestMapping(value = "/runJob", method = { RequestMethod.GET, RequestMethod.POST })
	public Object runJob(SchedulerJobInfo job) {
		log.info("params, job = {}", job);
		Message message = Message.failure();
		try {
			scheduleJobService.startJobNow(job);
			message = Message.success();
		} catch (Exception e) {
			message.setMsg(e.getMessage());
			log.error("runJob ex:", e);
		}
		return message;
	}

	@RequestMapping(value = "/pauseJob", method = { RequestMethod.GET, RequestMethod.POST })
	public Object pauseJob(SchedulerJobInfo job) {
		log.info("params, job = {}", job);
		Message message = Message.failure();
		try {
			scheduleJobService.pauseJob(job);
			message = Message.success();
		} catch (Exception e) {
			message.setMsg(e.getMessage());
			log.error("pauseJob ex:", e);
		}
		return message;
	}

	@RequestMapping(value = "/resumeJob", method = { RequestMethod.GET, RequestMethod.POST })
	public Object resumeJob(SchedulerJobInfo job) {
		log.info("params, job = {}", job);
		Message message = Message.failure();
		try {
			scheduleJobService.resumeJob(job);
			message = Message.success();
		} catch (Exception e) {
			message.setMsg(e.getMessage());
			log.error("resumeJob ex:", e);
		}
		return message;
	}

	@RequestMapping(value = "/deleteJob", method = { RequestMethod.GET, RequestMethod.POST })
	public Object deleteJob(SchedulerJobInfo job) {
		log.info("params, job = {}", job);
		Message message = Message.failure();
		try {
			scheduleJobService.deleteJob(job);
			message = Message.success();
		} catch (Exception e) {
			message.setMsg(e.getMessage());
			log.error("deleteJob ex:", e);
		}
		return message;
	}*/
}
