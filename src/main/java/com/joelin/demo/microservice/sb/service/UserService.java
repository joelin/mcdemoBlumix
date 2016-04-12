package com.joelin.demo.microservice.sb.service;

import java.util.List;

import com.joelin.demo.microservice.sb.entity.UserEntity;

public interface UserService {

	List<UserEntity> findAll();

	void save(UserEntity user) ;
}
