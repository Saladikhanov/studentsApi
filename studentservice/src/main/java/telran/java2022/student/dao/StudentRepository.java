package telran.java2022.student.dao;

import java.util.Map;
import java.util.Optional;

import telran.java2022.student.model.Student;

public interface StudentRepository {

    Student save(Student student);

    Optional<Student> findById(int id);

    void deleteById(int id);

    public Map<Integer, Student> getStudents();

   

}
