package com.example.asobi_box.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.asobi_box.entity.Play;
import com.example.asobi_box.form.PlayEditForm;
import com.example.asobi_box.form.PlayRegisterForm;
import com.example.asobi_box.repository.PlayRepository;
import com.example.asobi_box.service.PlayService;

@Controller
@RequestMapping("/admin/plays")
public class AdminPlayController {
	private final PlayRepository playRepository;
	private final PlayService playService;

	public AdminPlayController(PlayRepository playRepository, PlayService playService) {
		this.playRepository = playRepository;
		this.playService = playService;
	}

	@GetMapping
	public String index(Model model,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			@RequestParam(name = "keyword", required = false) String keyword) {
		Page<Play> descriptionPage;
		if (keyword != null && !keyword.isEmpty()) {
			descriptionPage = playRepository.findByDescriptionLike("%" + keyword + "%", pageable);
		} else {
			descriptionPage = playRepository.findAll(pageable);
		}

		Page<Play> titlePage;
		if (keyword != null && !keyword.isEmpty()) {
			String query = "%" + keyword + "%";
			titlePage = playRepository.findByTitleLikeOrDescriptionLike(query, query, pageable);
		} else {
			titlePage = playRepository.findAll(pageable);
		}

		model.addAttribute("playPage", titlePage);
		model.addAttribute("keyword", keyword);

		return "admin/plays/index";

	}

	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		Play play = playRepository.getReferenceById(id);

		model.addAttribute("play", play);

		return "admin/plays/show";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("playRegisterForm", new PlayRegisterForm());
		return "admin/plays/register";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute @Validated PlayRegisterForm playRegisterForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/plays/register";
		}

		playService.create(playRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "遊びを登録しました。");

		return "redirect:/admin/plays";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {
		Play play = playRepository.getReferenceById(id);
		PlayEditForm playEditForm = new PlayEditForm(play.getId(), play.getTitle(), null, play.getDescription(),
				play.getMinNumber(), play.getMaxNumber(), play.getCategory());

		model.addAttribute("playEditForm", playEditForm);

		return "admin/plays/edit";
	}

	@PostMapping("/{id}/update")
	public String update(@ModelAttribute @Validated PlayEditForm playEditForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/plays/edit";
		}

		playService.update(playEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "遊び情報を編集しました。");

		return "redirect:/admin/plays";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
		playRepository.deleteById(id);

		redirectAttributes.addFlashAttribute("successMessage", "遊びを削除しました。");

		return "redirect:/admin/plays";
	}
}