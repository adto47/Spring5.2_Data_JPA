package com.spk.demo.serviceImpl;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spk.demo.dto.form.UserForm;
import com.spk.demo.model.Users;
import com.spk.demo.repository.UserRepository;
import com.spk.demo.service.UserService;
import com.spk.demo.util.UserRole;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		UserServiceImpl.userRepository = userRepository;
	}

	@Override
	public Optional<Users> getAdminById(long id) {
		return Optional.ofNullable(userRepository.findById(id)).get();
	}

	@Override
	public Optional<Users> getuserById(Long id) {
		return Optional.ofNullable(userRepository.findById(id)).get();
	}

	@Override
	public Collection<Users> getAllUser(UserRole adminRole) {
		return userRepository.findAll();
	}

	@Override
	public Users createUser(UserForm form, UserRole userRole) {
		Users users = new Users();

		users.setUserName(form.getUserName());
		users.setFirstName(form.getFirstName());
		users.setLastName(form.getLastName());
		users.setEmail(form.getEmail());
		users.setEnabled(false);
		users.setEncrytedPassword(form.getPassword());
		userRepository.saveAndFlush(users);
		userRepository.saveAndFlush(entity)
			

		return null;
	}

}
