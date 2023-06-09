package one.evospringone.manager;

import lombok.RequiredArgsConstructor;

import one.evospringone.dto.SchoolDto;
import one.evospringone.dto.StudentDto;
import one.evospringone.exception.UserNotFoundException;
import one.evospringone.model.School;
import one.evospringone.model.Student;
import one.evospringone.repository.SchoolRepository;
import one.evospringone.repository.StudentRepository;
import one.evospringone.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private  final SchoolRepository schoolRepositoryository;
    private  final ModelMapper mapper;


    @Override
    public List<StudentDto> getAllDto() {
List<Student>students= studentRepository.findAll();
List<StudentDto> dtos=students.stream().map(student -> mapper.map(student,StudentDto.class)).collect(Collectors.toList());
        return dtos;

    }

    @Override
    public StudentDto getById(Long id) {
        studentRepository.findById(id).orElseThrow(()->new UserNotFoundException("Id uygun Student tapilmadi"));
        return  mapper.map(studentRepository.findById(id),StudentDto.class);
    }

    @Override
    public StudentDto createDto(StudentDto dto) {
        School school=schoolRepositoryository.findById(dto.getSchool().getId()).orElseThrow(()->new UserNotFoundException("ID uygun School yoxdur"));
        SchoolDto scDto=mapper.map(school,SchoolDto.class);
        Student entitiy=mapper.map(dto, Student.class);
        studentRepository.save(entitiy);
       StudentDto dto1= mapper.map(entitiy,StudentDto.class);
        dto1.setSchool(scDto);
        return dto1;
    }

    @Override
    public void deleteById(Long id){
        if (studentRepository.findById(id).isPresent()){
            studentRepository.deleteById(id);
            System.out.println("Silindi:)");
        }

        else {
            throw new UserNotFoundException("Student Tapilmadi");
        }

    }

    @Override
    public StudentDto update(Long id, StudentDto dto) {
        //id olub olmamasini yoxlayir
studentRepository.findById(id).orElseThrow(()->new UserNotFoundException("Id uygun Student tapilmadi"));
        School school=schoolRepositoryository.findById(dto.getSchool().getId()).orElseThrow(()->new UserNotFoundException("ID uygun School yoxdur"));
        SchoolDto scDto=mapper.map(school,SchoolDto.class);
Student entitiy=mapper.map(dto, Student.class);
        studentRepository.save(entitiy);
StudentDto scDto2= mapper.map(entitiy,StudentDto.class);
scDto2.setSchool(scDto);
        return scDto2;
    }
}
