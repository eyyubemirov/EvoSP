package one.evospringone.manager;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import one.evospringone.dto.SchoolDto;
import one.evospringone.dto.StudentDto;
import one.evospringone.model.School;
import one.evospringone.model.Student;
import one.evospringone.repository.SchoolRepository;
import one.evospringone.repository.StudentRepository;
import one.evospringone.service.SchoolService;
import one.evospringone.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private  final SchoolRepository screpository;
    private  final ModelMapper mapper;


    @Override
    public List<StudentDto> getAllDto() {
List<Student>students=repository.findAll();
List<StudentDto> dtos=students.stream().map(student -> mapper.map(student,StudentDto.class)).collect(Collectors.toList());
        return dtos;

    }

    @Override
    public StudentDto getById(Long id) {

        return  mapper.map(repository.findById(id),StudentDto.class);
    }

    @Override
    public StudentDto createDto(StudentDto dto) {
        Student entitiy=mapper.map(dto, Student.class);


        repository.save(entitiy);

        return mapper.map(entitiy,StudentDto.class);
    }

    @Override
    public void deleteById(Long id){
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            System.out.println("Silindi:)");
        }

        else {
            throw new RuntimeException("Not Fonud Student");
        }

    }

    @Override
    public StudentDto update(Long id, StudentDto dto) {
        //id olub olmamasini yoxlayir

        Student entitiy=mapper.map(dto, Student.class);
        School school=screpository.findById(id).orElseThrow(()->{
            throw new RuntimeException("no");
        });

        repository.save(entitiy);

        return mapper.map(entitiy,StudentDto.class);
    }
}
