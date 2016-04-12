package com.joelin.demo.microservice.sb.service;

import com.joelin.demo.microservice.sb.entity.UserEntity;

public interface LoginService {


	UserEntity login(UserEntity user);
}
