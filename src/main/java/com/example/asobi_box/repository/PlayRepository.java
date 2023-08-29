package com.example.asobi_box.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.asobi_box.entity.Play;

public interface PlayRepository extends JpaRepository<Play, Integer> {
	public Page<Play> findByNameLike(String keyword, Pageable pageable);
}
