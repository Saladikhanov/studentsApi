package telran.java2022.student.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java2022.student.dto.StudentAddScore;
import telran.java2022.student.dto.StudentCreateDto;
import telran.java2022.student.dto.StudentDto;
import telran.java2022.student.dto.StudentUpdateDto;
import telran.java2022.student.service.StudentService;

@RestController
public class StudentController {
    StudentService studentService;

    @PostMapping("/student")
    public Boolean addStudent(@RequestBody StudentCreateDto studentCreateDto) {
	return studentService.addStudent(studentCreateDto);

    }

    @GetMapping("/student/{id}")
    public StudentDto findStudent(@PathVariable Integer id) {
	return studentService.findStudent(id);
    }

    @DeleteMapping("/student/{id}")
    public StudentDto removeStudent(@PathVariable Integer id) {
	return studentService.removeStudent(id);
    }

    @PutMapping("/student/{id}")
    public StudentCreateDto updateStudent(@PathVariable Integer id, @RequestBody StudentUpdateDto studentUpdateDto) {
	return studentService.updateStudent(id, studentUpdateDto);
    }

    @PutMapping("/score/student/{id}")
    public Boolean addScore(@PathVariable Integer id, @RequestBody StudentAddScore studentAddScore) {
	return studentService.addScore(id, studentAddScore);
    }

    @GetMapping("/students/name/{name}")
    public StudentDto[] findStudents(@PathVariable String name) {
	return studentService.findStudents(name);
    }

    @PostMapping("/quantity/students")
    public Integer studentsQuantity(@RequestBody String[] students) {
	return studentService.studentsQuantity(students);
    }

    @GetMapping("/students/exam/History/minscore/{score}")
    public StudentDto[] minScore(@PathVariable Integer score) {
	return studentService.minScore(score);
    }
}
