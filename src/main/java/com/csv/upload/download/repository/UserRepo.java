package com.csv.upload.download.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.csv.upload.download.model.User;


public interface UserRepo extends JpaRepository<User, Integer> {
	
}
