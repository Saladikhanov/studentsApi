package telran.java2022.student.service;

import telran.java2022.student.dto.StudentAddScore;
import telran.java2022.student.dto.StudentCreateDto;
import telran.java2022.student.dto.StudentDto;
import telran.java2022.student.dto.StudentUpdateDto;

public interface StudentService {
    Boolean addStudent(StudentCreateDto studentCreateDto);

    StudentDto findStudent(Integer id);

    StudentDto removeStudent(Integer id);

    StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto);

    Boolean addScore(Integer id, StudentAddScore studentAddScore);
    
    StudentDto[] findStudents(String name);
    
    Integer studentsQuantity(String[] students);
    
    StudentDto[] minScore(Integer score);

}
