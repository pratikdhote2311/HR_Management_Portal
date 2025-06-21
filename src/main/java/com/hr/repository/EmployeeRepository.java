package com.hr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hr.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>
{
	
	public Employee findByIdAndPassword(int id,String password);

	public Employee findByIdAndPasswordAndRole(int id,String password,String role);

}
