package com.example.asobi_box.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.asobi_box.entity.User;
import com.example.asobi_box.form.UserEditForm;
import com.example.asobi_box.repository.UserRepository;
import com.example.asobi_box.security.UserDetailsImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());

		model.addAttribute("user", user);

		return "user/index";
	}

	@GetMapping("/edit")
	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		UserEditForm userEditForm = new UserEditForm(user.getId(), user.getName(), user.getFurigana(),
				user.getPostalCode(), user.getAddress(), user.getPhoneNumber(), user.getEmail());

		model.addAttribute("userEditForm", userEditForm);

		return "user/edit";
	}
}
