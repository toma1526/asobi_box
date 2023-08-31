package com.example.asobi_box.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String index(Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			@RequestParam(name = "keyword", required = false) String keyword) {
		Page<Play> titlePage;

		if (keyword != null && !keyword.isEmpty()) {
			titlePage = playRepository.findByTitleLike("%" + keyword + "%", pageable);
		} else {
			titlePage = playRepository.findAll(pageable);
		}
		Page<Play> categoryPage;

		if (keyword != null && !keyword.isEmpty()) {
			categoryPage = playRepository.findByCategoryLike("%" + keyword + "%", pageable);
		} else {
			categoryPage = playRepository.findAll(pageable);
		}

		model.addAttribute("playPage", categoryPage);
		model.addAttribute("keyword", keyword);

		return "admin/plays/index";
	}
}
