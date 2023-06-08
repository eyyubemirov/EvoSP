package one.evospringone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.evospringone.model.School;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StudentDto {
    private Long id;
    private String name;
    private String surname;
    private SchoolDto school;
}
