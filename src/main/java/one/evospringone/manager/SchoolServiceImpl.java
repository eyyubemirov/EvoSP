package one.evospringone.manager;

import lombok.RequiredArgsConstructor;
import one.evospringone.dto.SchoolDto;
import one.evospringone.exception.UserNotFoundException;
import one.evospringone.model.School;
import one.evospringone.repository.SchoolRepository;
import one.evospringone.service.SchoolService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final ModelMapper mapper;
    @Override
    public List<SchoolDto> getAll() {
        List<School> schools= schoolRepository.findAll();
        List<SchoolDto> dtos=schools.stream().map(school -> mapper.map(school,SchoolDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public SchoolDto getById(Long id) {
        schoolRepository.findById(id).orElseThrow(()->new UserNotFoundException("Id uygun school tapilmadi"));
        return mapper.map(schoolRepository.findById(id),SchoolDto.class);
    }

    @Override
    public SchoolDto create(SchoolDto dto) {
        School school=mapper.map(dto,School.class);
        schoolRepository.save(school);

        return mapper.map(school,SchoolDto.class);
    }

    @Override
    public SchoolDto update(Long id, SchoolDto dto) {
        schoolRepository.findById(id).ifPresentOrElse(school -> dto.setId(school.getId()),
                ()-> {
            throw new UserNotFoundException("Id uygun school tapilmadi");
                });

        School school=mapper.map(dto,School.class);
        schoolRepository.save(school);
        return mapper.map(school,SchoolDto.class);
    }

    @Override
    public void deleteById(Long id) {

       if (schoolRepository.findById(id).isPresent()){
           schoolRepository.deleteById(id);
           System.out.println("Silindi:)");
       }

        throw new UserNotFoundException("Silinecek  School yoxdur");


    }
}
