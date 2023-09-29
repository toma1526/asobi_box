package com.example.asobi_box.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.asobi_box.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByEmail(String email);
}
