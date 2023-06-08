package one.evospringone.service;

import one.evospringone.dto.SchoolDto;

import java.util.List;

public interface SchoolService {

    List<SchoolDto> getAll();

    SchoolDto getById(Long id);

    SchoolDto create(SchoolDto dto);

    SchoolDto update(Long id,SchoolDto dto);

    void deleteById(Long id);
}
