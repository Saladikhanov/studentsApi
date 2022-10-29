package telran.java2022.student.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StudentCreateDto {
    Integer id;
    String name;
    String password;
}
