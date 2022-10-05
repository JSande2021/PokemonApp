package com.project.data;

import com.project.data.entity.UserLoginEntity;

public interface UserLoginDataAccessInterface <T> {

	UserLoginEntity FindByUsername(String username);
}
