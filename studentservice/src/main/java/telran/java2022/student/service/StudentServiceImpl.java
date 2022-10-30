package telran.java2022.student.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import telran.java2022.student.dao.StudentRepository;
import telran.java2022.student.dto.ScoreDto;
import telran.java2022.student.dto.StudentCreateDto;
import telran.java2022.student.dto.StudentDto;
import telran.java2022.student.dto.StudentUpdateDto;
import telran.java2022.student.model.Student;

@Component
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Boolean addStudent(StudentCreateDto studentCreateDto) {
	if (studentRepository.findById(studentCreateDto.getId()).isPresent()) {
	    return false;
	}
	Student student = new Student(studentCreateDto.getId(), studentCreateDto.getName(),
		studentCreateDto.getPassword());
	studentRepository.save(student);
	return true;
    }

    @Override
    public StudentDto findStudent(Integer id) {
	Student student = studentRepository.findById(id).orElse(null);
	return student == null ? null
		: StudentDto.builder()
					.id(student.getId())
					.name(student.getName())
					.scores(student.getScores())
					.build();
    }

    @Override
    public StudentDto removeStudent(Integer id) {
	Student student = studentRepository.findById(id).orElse(null);
	if (student == null) {
	    return null;
	}
	studentRepository.deleteById(id);
	return StudentDto.builder()
					.id(student.getId())
					.name(student.getName())
					.scores(student.getScores())
					.build();
    }

    @Override
    public StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
	Student student = studentRepository.findById(id).orElse(null);
	if (student == null) {
	    return null;
	}
	student.setName(studentUpdateDto.getName());
	student.setPassword(studentUpdateDto.getPassword());
	return StudentCreateDto.builder()
							.id(id).name(studentUpdateDto.getName())
							.password(studentUpdateDto.getPassword())
							.build();
    }

    @Override
    public Boolean addScore(Integer id, ScoreDto scoreDto) {
	Student student = studentRepository.findById(id).orElse(null);
	if (student == null) {
	    return null;
	}
	return student.addScore(scoreDto.getExamName(), scoreDto.getScore());
    }

    @Override
    public List<StudentDto> findStudents(String name) {
	return studentRepository.getStudents().stream()
										.filter(s -> name.equalsIgnoreCase(s.getName()))
										.map(s -> StudentDto.builder()
															.id(s.getId())
															.name(s.getName())
															.scores(s.getScores())
															.build())
										.collect(Collectors.toList());
    }

    @Override
    public Integer getStudentsNamesQuantity(List<String> students) {
	return studentRepository.getStudents().stream()
										.filter(s -> students.contains(s.getName()))
										.mapToInt(s -> 1).sum();
    }

    @Override
    public List<StudentDto> minScore(Integer score, String exam) {
	return studentRepository.getStudents().stream()
										.filter(s -> s.getScores().containsKey(exam) && s.getScores().get(exam) > score)
										.map(s -> StudentDto.builder()
															.id(s.getId())
															.name(s.getName())
															.scores(s.getScores())
															.build())
										.collect(Collectors.toList());
    }

}
