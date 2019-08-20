package tk.roydgar.obdservices.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.obdservices.domain.dto.request.workshop.CreateWorkshopRequest;
import tk.roydgar.obdservices.domain.dto.request.workshop.UpdateWorkshopRequest;
import tk.roydgar.obdservices.domain.dto.response.WorkshopDto;
import tk.roydgar.obdservices.mapper.response.WorkshopResponseMapper;
import tk.roydgar.obdservices.service.WorkshopService;

import java.util.List;

@RestController
@RequestMapping("api/workshop")
@RequiredArgsConstructor
public class WorkshopController {

    private final WorkshopService workshopService;
    private final WorkshopResponseMapper responseMapper;

    @GetMapping("/{id}")
    public WorkshopDto findById(@PathVariable Long id) {
        return responseMapper.toDto(workshopService.getById(id));
    }

    @GetMapping
    public List<WorkshopDto> getAll() {
        return responseMapper.toDtoList(workshopService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkshopDto create(CreateWorkshopRequest request) {
        return responseMapper.toDto(workshopService.create(request));
    }

    @PutMapping
    public WorkshopDto update(UpdateWorkshopRequest request) {
        return responseMapper.toDto(workshopService.update(request));
    }

    @DeleteMapping("/{id}")
    public WorkshopDto delete(@PathVariable Long id) {
        return responseMapper.toDto(workshopService.delete(id));
    }

}
