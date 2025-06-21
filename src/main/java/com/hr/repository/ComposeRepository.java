package com.hr.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hr.entity.UserCompose;

@Repository
public interface ComposeRepository extends CrudRepository<UserCompose, Integer>
{
	
	public List<UserCompose>findByParentUkid(int parentUkid);
	

}
