package com.hr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hr.entity.CreatePost;
import com.hr.entity.Employee;
import com.hr.repository.CreatePostRepository;
import com.hr.repository.EmployeeRepository;

// Unit & Junit5 Testing -> use Service method and ( Integration testing use Controller )
//Inside hrService all methods .
public class HrServiceTest

{
    
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	
	@Mock
	private CreatePostRepository createPostRepository;
	
	
	@InjectMocks
	private HrService hrService;
	
	
	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Test
	void testAddEmployeeDetails()
	{
		Employee emp=new Employee();
		
		emp.setId(1);
		emp.setEmployeeName("Pratik");
		emp.setEmail("pratik2311@gmail.com");
		
		
		when(employeeRepository.save(emp)).thenReturn(emp);
		
	    Employee saveEmployeeDetails = hrService.addEmployeeDetails(emp);
	    
	    verify(employeeRepository).save(emp);
	     assertEquals(emp,saveEmployeeDetails);
	     
	}
	
	
	@Test
	void testGetAllEmpDetails()
	{
		List<Employee> empList=new ArrayList<>();
		
        Employee emp=new Employee();
		
		emp.setId(1);
		emp.setEmployeeName("Pratik");
		emp.setEmail("pratik2311@gmail.com");
		
        Employee emp2=new Employee();
		
		emp2.setId(2);
		emp2.setEmployeeName("Ritik");
		emp2.setEmail("ritik2311@gmail.com");
		
		empList.add(emp);
		empList.add(emp2);
		
		
		when(employeeRepository.findAll()).thenReturn (empList);
		
		List<Employee> result=hrService.getAllEmpDetails();
		
		assertEquals(empList,result);
		
	}
	
	
	@Test
	void testAddPostDetails()
	{
		CreatePost createPost =new CreatePost();
		
		createPost.setId(1);
		createPost.setTitle("Leave");
		createPost.setComment("Java Developer");
		
		
		when(createPostRepository.save(createPost)).thenReturn(createPost);
		
	    CreatePost saveEmployeeDetails = hrService.addPostDetails(createPost);
	    
	    verify(createPostRepository).save(createPost);
	     assertEquals(createPost,saveEmployeeDetails);
	     
	}
}
