package com.example.asobi_box.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.asobi_box.entity.Play;
import com.example.asobi_box.form.PlayRegisterForm;
import com.example.asobi_box.repository.PlayRepository;

@Service
public class PlayService {
	private final PlayRepository playRepository;

	public PlayService(PlayRepository playRepository) {
		this.playRepository = playRepository;
	}

	@Transactional
	public void create(PlayRegisterForm playRegisterForm) {
		Play play = new Play();
		MultipartFile imageFile = playRegisterForm.getImageFile();

		play.setTitle(playRegisterForm.getTitle());
		play.setDescription(playRegisterForm.getDescription());
		play.setMinNumber(playRegisterForm.getMinNumber());
		play.setMaxNumber(playRegisterForm.getMaxNumber());
		play.setCategory(playRegisterForm.getCategory());
		playRepository.save(play);
	}

	// UUIDを使って生成したファイル名を返す
	public String generateNewFileName(String fileName) {
		String[] fileNames = fileName.split("\\.");
		for (int i = 0; i < fileNames.length - 1; i++) {
			fileNames[i] = UUID.randomUUID().toString();
		}
		String hashedFileName = String.join(".", fileNames);
		return hashedFileName;
	}

	// 画像ファイルを指定したファイルにコピーする
	public void copyImageFile(MultipartFile imageFile, Path filePath) {
		try {
			Files.copy(imageFile.getInputStream(), filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
