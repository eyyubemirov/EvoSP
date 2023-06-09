package one.evospringone.controller;

import lombok.RequiredArgsConstructor;
import one.evospringone.dto.SchoolDto;
import one.evospringone.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService service;

    @GetMapping("/getAll")
    public List<SchoolDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public SchoolDto getById(@PathVariable Long id) {

        return service.getById(id);
    }

    @PostMapping("/create")
    public SchoolDto create(@RequestBody SchoolDto dto) {
        return service.create(dto);
    }

    @PutMapping("/update/{id}")
    public SchoolDto update(@PathVariable Long id, @RequestBody SchoolDto dto) {
        return service.update(id, dto);

    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
