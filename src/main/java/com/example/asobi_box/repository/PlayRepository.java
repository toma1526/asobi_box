package com.example.asobi_box.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.asobi_box.entity.Play;

public interface PlayRepository extends JpaRepository<Play, Integer> {

	public Page<Play> findByTitleLike(String keyword, Pageable pageable);

	public Page<Play> findByDescriptionLike(String keyword, Pageable pageble);

	public Page<Play> findByTitleLikeOrDescriptionLike(String title, String desc, Pageable pageable);

}
