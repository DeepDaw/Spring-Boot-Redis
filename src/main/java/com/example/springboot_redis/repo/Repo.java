package com.example.springboot_redis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot_redis.model.Student;

public interface Repo extends JpaRepository<Student, String> {
	
}
