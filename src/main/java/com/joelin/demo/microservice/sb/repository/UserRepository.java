package com.joelin.demo.microservice.sb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joelin.demo.microservice.sb.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	

	UserEntity findByEmail(String email);
}
