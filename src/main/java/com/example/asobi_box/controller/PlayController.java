package com.example.asobi_box.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlayController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

}
