package com.example.asobi_box.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayRegisterForm {
	@NotBlank(message = "遊びの名前を入れてください")
	private String title;

	private MultipartFile imageFile;

	@NotBlank(message = "ルールや注意事項を入れてください。")
	private String description;

	@NotNull(message = "遊びの最小人数を入れてください。")
	@Min(value = 1, message = "人数は1人以上に設定してください。")
	private Integer minNumber;

	@NotNull(message = "遊びの最高人数を入れてください。")
	@Max(value = 100, message = "人数は100人以下に設定してください。")
	private Integer maxNumber;

	@NotBlank(message = "どこで遊ぶかを入れてください。")
	private String category;

}
