package com.spk.demo.service;

import java.util.Collection;
import java.util.Optional;

import com.spk.demo.dto.form.UserForm;
import com.spk.demo.model.Users;
import com.spk.demo.util.UserRole;

public interface UserService {

	Optional<Users> getAdminById(long id);

	Optional<Users> getuserById(Long id);

	Collection<Users> getAllUser(UserRole adminRole);

	Users createUser(UserForm form, UserRole userRole);

}
