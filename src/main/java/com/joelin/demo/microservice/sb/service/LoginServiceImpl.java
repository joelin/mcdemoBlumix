package com.joelin.demo.microservice.sb.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joelin.demo.microservice.sb.EncryptUtils;
import com.joelin.demo.microservice.sb.entity.UserEntity;
import com.joelin.demo.microservice.sb.repository.UserRepository;
import com.joelin.demo.microservice.sb.service.exception.ServiceException;


@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserRepository userRepository;

	public UserEntity login(UserEntity user) {
		if(StringUtils.isBlank(user.getEmail())) {
			throw new ServiceException("用户名不能为空");
		}
		
		if(StringUtils.isBlank(user.getPassword())) {
			throw new ServiceException("密码不能为空");
		}
		
		UserEntity userEntity = userRepository.findByEmail(user.getEmail());
		if(null == userEntity){
			throw new ServiceException("用户名不存在");
		}
		
		String password = EncryptUtils.encryptMD5(user.getPassword());
		if(!StringUtils.equals(password, userEntity.getPassword())){
			throw new ServiceException("密码输入错误");
		}
		
		return userEntity;
	}

}
