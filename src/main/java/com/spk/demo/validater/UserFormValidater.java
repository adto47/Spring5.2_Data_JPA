package com.spk.demo.validater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spk.demo.dto.form.UserForm;
import com.spk.demo.model.Users;
import com.spk.demo.repository.UserRepository;
import com.spk.demo.util.APIStatus;

@Component
public class UserFormValidater {

	@Autowired
	UserRepository repository;
	Users userAcc = null;

	public APIStatus validateUser(UserForm form) {
		if (form.getFirstName() == "" || form.getFirstName() == null) {
			return APIStatus.REQUIRED_FIRST_NAME;
		}
		if (form.getUserName() == "" || form.getUserName() == null) {
			return APIStatus.REQUIRED_USER_NAME;
		}
		if (form.getPassword() == "" || form.getPassword() == null) {
			return APIStatus.REQUIRED_PASSWORD;
		}

		userAcc = repository.findByEmail(form.getEmail());
		if (userAcc != null) {
			return APIStatus.EMAIL_ALREADY_EXIST;
		}
		userAcc = repository.findAppUserByUserName(form.getUserName());
		if (userAcc != null) {
			return APIStatus.USER_NAME_ALREADY_EXIST;
		}
		return APIStatus.SUCCESS;

	}

}
