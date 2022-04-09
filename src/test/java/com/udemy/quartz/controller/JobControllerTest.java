package com.udemy.quartz.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

///import org.springframework.security.test.context.support


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.udemy.quartz.filter.JwtRequestFilter;
import com.udemy.quartz.service.AppUserDetailsService;
import com.udemy.quartz.service.CourseRegistrationService;
import com.udemy.quartz.service.JWUtil;
import com.udemy.quartz.service.PersonService;
import com.udemy.quartz.service.SchedulerJobService;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)	
public class JobControllerTest {
	@Autowired
	
	private MockMvc mockMvc;
	@MockBean
	AppUserDetailsService  appUserDetailsService;
	@MockBean
	JWUtil jWUtil;
	@MockBean
	private CourseRegistrationService courseRegistrationService;
	@MockBean
	private AuthenticationManager authenticationManager;
	@MockBean
	private  SchedulerJobService scheduleJobService;
//	@MockBean
//	private UserDetailsService myUserDetailsService;
	@MockBean
	private JwtRequestFilter jwtRequestFilter;
	
	@MockBean
	private PersonService personService;
	
	@Test
	//@WithMockUser(value = "spring")
	//@AutoConfigureMockMvc(addFilters = false)
	public void greetingShouldReturnMessageFromService() throws Exception {
		//when(service.greet()).thenReturn("Hello, Mock");
		
		this.mockMvc.perform(get("/api/hello")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("hello")));
	}
}

