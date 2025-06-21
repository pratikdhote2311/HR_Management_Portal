package com.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.entity.CreatePost;
import com.hr.entity.Employee;
import com.hr.entity.UserCompose;
import com.hr.repository.ComposeRepository;
import com.hr.repository.CreatePostRepository;
import com.hr.repository.EmployeeRepository;

@Service
public class HrService 
{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CreatePostRepository createPostRepository;
	

	
	
	public Employee addEmployeeDetails(Employee emp)
	{
		Employee save = employeeRepository.save(emp);
		
		return save;
		
		
	}
	
	public List<Employee> getAllEmpDetails()
	{
		List<Employee> All = (List<Employee>)employeeRepository.findAll();
		
		return All;
		
	}
	
	// CreatePost Details ( we added post details) createpost.html
	
	public CreatePost addPostDetails(CreatePost createPost)
	{
		CreatePost save = createPostRepository.save(createPost);
		
		return save;
		
	}
	
	
	
	
	
}
