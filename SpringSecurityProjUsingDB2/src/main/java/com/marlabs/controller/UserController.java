package com.marlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marlabs.model.User;
import com.marlabs.service.IUserService;

@Controller
public class UserController {
	@Autowired
	private IUserService service;

	@GetMapping("/register")
	public String showUser() {
		return "UserRegister";
	}

	@PostMapping("/save")
	public String saveUser(@ModelAttribute User user, Model model) {
		Integer id = service.saveUser(user);
		String message = "User   " + id + "save";
		model.addAttribute("message", message);
		return "UserRegister";
	}

	@GetMapping("/home")
	public String showHome() {
		return "HomePage";
	}

	@GetMapping("/admin")
	public String showInbox() {
		return "AdminPage";
	}

	@GetMapping("/mgr")
	public String showEmp() {
		return "ManagerPage";
	}

	@GetMapping("/common")
	public String showCommon() {
		return "CommonPage";
	}

	@GetMapping("/denied")
	public String showNoAccess() {
		return "DeniedPage";
	}
}
