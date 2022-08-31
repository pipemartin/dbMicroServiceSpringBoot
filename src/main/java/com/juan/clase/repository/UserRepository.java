package com.juan.clase.repository;

import org.springframework.data.repository.CrudRepository;

import com.juan.clase.bean.db.User;


public interface UserRepository extends CrudRepository<User, Integer> {
	
}
