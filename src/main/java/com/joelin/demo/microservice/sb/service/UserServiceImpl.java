package com.joelin.demo.microservice.sb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joelin.demo.microservice.sb.entity.AddressEntity;
import com.joelin.demo.microservice.sb.entity.UserEntity;
import com.joelin.demo.microservice.sb.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	

	public List<UserEntity> findAll(){
		return userRepository.findAll();
	}
	

	public void save(UserEntity user) {
		for(AddressEntity address: user.getAddresses()) {
			address.setUser(user);
		}
		userRepository.save(user);
	}
}
