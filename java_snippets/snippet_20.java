package com.example;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    @Bean
    CommandLineRunner initData(StudentRepository repository) {
        return args -> {
            Student student = new Student();
            student.setName("John Doe");
            repository.save(student);
        };
    }
}
