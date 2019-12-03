package com.wildcodeschool.fco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wildcodeschool.fco.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
