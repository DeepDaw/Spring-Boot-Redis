package com.example.springboot_redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_redis.model.Student;
import com.example.springboot_redis.service.StudentService;

@RestController
@RequestMapping("/students") 
public class Controller {
	@Autowired
    private StudentService studentService;

    // GET mapping to retrieve all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
