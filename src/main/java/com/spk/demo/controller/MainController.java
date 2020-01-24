package com.spk.demo.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spk.demo.dto.form.UserForm;
import com.spk.demo.exception.CustomException;
import com.spk.demo.model.Users;
import com.spk.demo.service.UserService;
import com.spk.demo.util.APIResponse;
import com.spk.demo.util.APIStatus;
import com.spk.demo.util.ResponseUtil;
import com.spk.demo.util.UserRole;
import com.spk.demo.validater.UserFormValidater;

@RestController
public class MainController {

	@Autowired
	UserService userService;
	@Autowired
	ResponseUtil responseUtil;
	@Autowired
	UserFormValidater uservalidater;

	@GetMapping(value = { "/", "/welcome" })
	public @ResponseBody String welcomePage(Model model) {
		model.addAttribute("message", "This is welcome page!");
		return "welcomePage";
	}

	@GetMapping(value = "/users")
	public ResponseEntity<APIResponse> allAdmin(Model model, Principal principal) {
		Collection<Users> list = userService.getAllUser(UserRole.ADMIN_ROLE);

		if (!list.isEmpty()) {

			return responseUtil.successResponse(list);
		}
		throw new CustomException(APIStatus.ERR_USER_NOT_EXIST);
	}

	@GetMapping(value = "/user/{id}")
	public ResponseEntity<APIResponse> userById(@PathVariable Long id) {
		Optional<Users> user = userService.getuserById(id);

		if (user.isPresent()) {
			return responseUtil.successResponse(user);
		}

		throw new CustomException(APIStatus.ERR_USER_NOT_FOUND);

	}

	@PostMapping(path = "/UserSignUp")
	public ResponseEntity<APIResponse> createUser(@RequestBody UserForm form) {

		if (uservalidater.validateUser(form).equals(APIStatus.SUCCESS)) {
			Users users = userService.createUser(form, UserRole.USER_ROLE);
			return responseUtil.successResponse(users);

		}
		throw new CustomException(uservalidater.validateUser(form));

	}

	@PostMapping(value = { "/login" })
	public ResponseEntity<APIResponse> login() {

		return null;
	}

	@GetMapping(value = { "/signin" })
	public String signInPage(Model model) {
		return "redirect:/login";
	}

	@GetMapping(value = "/403")
	public String accessDenied(Model model, Principal principal) {

		return "403Page";
	}

}
