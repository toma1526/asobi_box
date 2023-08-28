package com.example.asobi_box.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.asobi_box.entity.Play;
import com.example.asobi_box.repository.PlayRepository;

@Controller
@RequestMapping("/admin/plays")
public class AdminPlayController {
	private final PlayRepository playRepository;

	public AdminPlayController(PlayRepository playRepository) {
		this.playRepository = playRepository;
	}

	@GetMapping
	public String index(Model model) {
		List<Play> plays = playRepository.findAll();

		model.addAttribute("plays", plays);

		return "admin/plays/index";
	}
}
