package com.example.asobi_box.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String index() {
		System.out.println("debug0");
		return "index";
	}

}
