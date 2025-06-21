package com.hr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hr.entity.CreatePost;


@Repository
public interface CreatePostRepository extends CrudRepository<CreatePost,Integer>
{
	

}
