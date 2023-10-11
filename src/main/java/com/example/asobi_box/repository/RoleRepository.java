package com.example.asobi_box.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.asobi_box.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByName(String name);
}
