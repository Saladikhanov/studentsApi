package telran.java2022.student.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
		: StudentDto.builder().id(student.getId()).name(student.getName()).scores(student.getScores()).build();
    }

    @Override
    public StudentDto removeStudent(Integer id) {
	Student student = studentRepository.findById(id).orElse(null);
	if (student == null) {
	    return null;
	}
	studentRepository.deleteById(id);
	return StudentDto.builder().id(student.getId()).name(student.getName()).scores(student.getScores()).build();
    }

    @Override
    public StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
	Student student = studentRepository.findById(id).orElse(null);
	if (student == null) {
	    return null;
	}
	student.setName(studentUpdateDto.getName());
	student.setPassword(studentUpdateDto.getPassword());
	return StudentCreateDto.builder().id(id).name(studentUpdateDto.getName())
		.password(studentUpdateDto.getPassword()).build();
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
	
	List<StudentDto> res = null;
	Map<Integer, Student> allStudents = studentRepository.getStudents();
	for (Map.Entry<Integer, Student> entry : allStudents.entrySet()) {
	    Integer id = entry.getKey();
	    Student student = entry.getValue();
	    if(name.equalsIgnoreCase(student.getName())) {
		res.add(StudentDto.builder().id(student.getId()).name(student.getName()).scores(student.getScores()).build());
	    }
	    
	}

	return res;
    }

    @Override
    public Integer getStudentsNamesQuantity(List<String> students) {
	
	return null;
    }

    @Override
    public List<StudentDto> minScore(Integer score, String exam) {
	// TODO Auto-generated method stub
	return null;
    }

}
