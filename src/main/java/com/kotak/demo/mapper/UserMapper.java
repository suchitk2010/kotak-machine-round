package com.kotak.demo.mapper;

import com.kotak.demo.dto.UserResponse;
import com.kotak.demo.entity.User;
import com.kotak.demo.util.BeanUtils;

public class UserMapper {

	public static UserResponse toDto(User entity) {
		return BeanUtils.initializeBean(entity, UserResponse.class);
	}

}
