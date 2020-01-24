package com.spk.demo.validater;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spk.demo.dto.form.UserForm;
import com.spk.demo.model.Users;
import com.spk.demo.repository.UserRepository;

@Component
public class UserValidater implements Validator{

	private EmailValidator emailValidator = EmailValidator.getInstance();

	@Autowired
	private UserRepository repository;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UserForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserForm form = (UserForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "", "User name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "First name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Last name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is required");

		if (errors.hasErrors()) {
			return;
		}

		if (!emailValidator.isValid(form.getEmail())) {

			errors.rejectValue("email", "", "Email is not valid");
			return;
		}

		Users userAccount = repository.findAppUserByUserName(form.getUserName());
		if (userAccount != null) {
			if (form.getUserId() == null) {
				errors.rejectValue("userName", "", "User name is not available");
				return;
			} else if (!form.getUserId().equals(userAccount.getId())) {
				errors.rejectValue("userName", "", "User name is not available");
				return;
			}
		}

		userAccount = repository.findByEmail(form.getEmail());
		if (userAccount != null) {
			if (form.getUserId() == null) {
				errors.rejectValue("email", "", "Email is not available");
				return;
			} else if (!form.getUserId().equals(userAccount.getId())) {
				errors.rejectValue("email", "", "Email is not available");
				return;
			}
		}
	}

}
