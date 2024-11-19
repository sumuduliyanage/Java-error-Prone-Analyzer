package org.example.api;

import org.example.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private ServiceLayer studentService;

    @GetMapping("/healthCheck")
    public String sample() {
        return "I am healthy";
    }
}
