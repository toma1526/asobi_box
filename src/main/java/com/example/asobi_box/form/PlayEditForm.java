package com.example.asobi_box.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayEditForm {
	@NotNull
	private Integer id;

	@NotBlank(message = "遊び名を入れてください。")
	private String name;

	private MultipartFile imageFile;

	@NotBlank(message = "説明を入力してください。")
	private String description;

	@NotNull(message = "最小人数を入れてください。")
	@Min(value = 1, message = "人数は1人以上に設定してください。")
	private Integer minNumber;

	@NotNull(message = "最大人数を入れてください")
	@Min(value = 1, message = "最大人数は1人以上に設定してください。")
	private Integer maxNumber;

	@NotBlank(message = "場所を入れてください。")
	private String category;

}
