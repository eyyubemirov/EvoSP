package one.evospringone.manager;

import lombok.RequiredArgsConstructor;
import one.evospringone.dto.SchoolDto;
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

    private final SchoolRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<SchoolDto> getAll() {
        List<School> schools=repository.findAll();
        List<SchoolDto> dtos=schools.stream().map(school -> mapper.map(school,SchoolDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public SchoolDto getById(Long id) {
        return mapper.map(repository.findById(id),SchoolDto.class);
    }

    @Override
    public SchoolDto create(SchoolDto dto) {
        School school=mapper.map(dto,School.class);
        repository.save(school);

        return mapper.map(school,SchoolDto.class);
    }

    @Override
    public SchoolDto update(Long id, SchoolDto dto) {
        repository.findById(id).ifPresentOrElse(school -> dto.setId(school.getId()),
                ()-> {
            throw new RuntimeException("not found School");
                });

        School school=mapper.map(dto,School.class);
        repository.save(school);
        return mapper.map(school,SchoolDto.class);
    }

    @Override
    public void deleteById(Long id) {

       if (repository.findById(id).isPresent()){
           repository.deleteById(id);
           System.out.println("Silindi:)");
       }

        throw new RuntimeException("Not Fonud School");


    }
}
