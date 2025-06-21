package com.hr.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hr.entity.Employee;
import com.hr.repository.ComposeRepository;
import com.hr.repository.CreatePostRepository;
import com.hr.repository.EmployeeRepository;
import com.hr.service.HrService;

@WebMvcTest(HRController.class)
public class HrControllerTest 
{
	
	@Autowired
    private MockMvc mockMvc;
  
	@MockBean
    private HrService service;
  
	
	@MockBean
	private CreatePostRepository createPostRepository;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@MockBean
	private ComposeRepository composeRepository;
  
	
	@InjectMocks
	private HRController hrController;
	
	
	
	@BeforeEach
	private void setUp()
	{
		MockitoAnnotations.openMocks(this);
		
		this.mockMvc=MockMvcBuilders.standaloneSetup(hrController).build();
		
		
	}
	
	
	@Test
	private void testDashBoardBase() throws Exception
	{
		
		Employee emp=new Employee();
		
		emp.setId(101);
		emp.setEmployeeName("Pratik Dhote");
		emp.setDesignation("Developer");
		emp.setRole("USER");
		
		when(employeeRepository.findByIdAndPassword(101,"password123")).thenReturn(emp);
		
		mockMvc.perform(get("/dashboards")
				
				.param("Username", "HMP101")
				.param("Password", "password123")
)
		
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/user-dashbaord"));
		
		
	
		
		
	}
	
	@Test
	public void testSavePost() throws Exception

	
	{
         mockMvc.perform(post("/save-post")
        		 
        		 .param("title", "New Title")
        		 .param("comment","This is a comment")
        		 
        		 )
       
 		.andExpect(status().is3xxRedirection())
 		.andExpect(redirectedUrl("/create-post"));

         
	}
	
}

