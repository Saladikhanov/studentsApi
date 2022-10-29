package telran.java2022.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java2022.student.dto.ScoreDto;
import telran.java2022.student.dto.StudentCreateDto;
import telran.java2022.student.dto.StudentDto;
import telran.java2022.student.dto.StudentUpdateDto;
import telran.java2022.student.service.StudentService;

@RestController
public class StudentController {
    @Autowired
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
    public Boolean addScore(@PathVariable Integer id, @RequestBody ScoreDto scoreDto) {
	return studentService.addScore(id, scoreDto);
    }

    @GetMapping("/students/name/{name}")
    public List<StudentDto> findStudents(@PathVariable String name) {
	return studentService.findStudents(name);
    }

    @PostMapping("/quantity/students")
    public Integer studentsQuantity(@RequestBody List<String> students) {
	return studentService.getStudentsNamesQuantity(students);
    }

    @GetMapping("/students/exam/{exam}/minscore/{score}")
    public List<StudentDto> minScore(@PathVariable Integer score, @PathVariable String exam) {
	return studentService.minScore(score, exam);
    }
}
