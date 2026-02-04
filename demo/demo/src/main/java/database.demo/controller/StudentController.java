package database.demo.controller;

import database.demo.entity.Student;
import database.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/upload/csv")
    public String uploadCSV(@RequestParam MultipartFile file) throws Exception {
        service.insertCSV(file);
        return "CSV uploaded successfully";
    }

    @GetMapping
    public List<Student> getStudents() {
        return service.getAllStudents();
    }
}
