package com.spk.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spk.demo.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	Users findAppUserByUserName(String userName);

	Users findByEmail(String email);
}
