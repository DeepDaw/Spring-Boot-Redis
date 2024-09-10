package com.example.springboot_redis.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.springboot_redis.model.Student;
import com.example.springboot_redis.repo.Repo;

@Service
public class StudentService {

    private static final String KEY = "ef";
    private static final long CACHE_TTL = 1; // Time to live in minutes
    private static final TimeUnit CACHE_TTL_UNIT = TimeUnit.MINUTES;

    @Autowired
    private RedisTemplate<String, List<Student>> redisTemplate;

    @Autowired
    private Repo repo;

    public List<Student> getAllStudents() {
        // Try to get data from cache
        List<Student> cachedStudents = redisTemplate.opsForValue().get(KEY);
        
        if (cachedStudents != null) {
            System.out.println("Data retrieved from Redis cache");
            return cachedStudents;
        }

        // If cache is empty, get data from database
        List<Student> studentsFromDb = repo.findAll();
        
     // Store data in cache with a TTL of 1 minutes
        redisTemplate.opsForValue().set(KEY, studentsFromDb, CACHE_TTL, CACHE_TTL_UNIT);
        
        System.out.println("Data retrieved from database and cached in Redis");
        return studentsFromDb;
    
}
}