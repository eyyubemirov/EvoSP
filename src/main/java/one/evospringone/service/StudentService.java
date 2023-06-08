package one.evospringone.service;

import one.evospringone.dto.StudentDto;

import java.util.List;

public interface StudentService {



     List<StudentDto> getAllDto();
     StudentDto getById(Long id);

      StudentDto createDto(StudentDto dto);

     void deleteById(Long id);
     StudentDto update(Long id,StudentDto dto);
}
