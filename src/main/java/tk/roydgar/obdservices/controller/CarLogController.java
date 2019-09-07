package tk.roydgar.obdservices.controller;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.roydgar.obdservices.domain.CarLog;
import tk.roydgar.obdservices.domain.dto.request.carlog.CreateCarLogRequest;
import tk.roydgar.obdservices.domain.dto.request.carlog.UpdateCarLogRequest;
import tk.roydgar.obdservices.domain.dto.response.CarLogDto;
import tk.roydgar.obdservices.mapper.response.CarLogResponseMapper;
import tk.roydgar.obdservices.service.CarLogService;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;
import static tk.roydgar.obdservices.util.WebEntityUtils.createFileResponse;

@RestController
@RequestMapping("api/car-log")
@RequiredArgsConstructor
public class CarLogController {

    private final CarLogService carLogService;
    private final CarLogResponseMapper responseMapper;

    @GetMapping("/{id}")
    public CarLogDto findById(@PathVariable Long id) {
        return responseMapper.toDto(carLogService.getById(id));
    }

    @GetMapping
    public List<CarLogDto> getAll(@RequestParam(value = "carId", required = false) Long carId) {
        List<CarLog> carErrorLogDtos = carId == null
                ? carLogService.getAll()
                : carLogService.getAllByCarId(carId);

        return responseMapper.toDtoList(carErrorLogDtos);
    }

    @GetMapping(value = "/file/{id}")
    @SneakyThrows
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        return createFileResponse(carLogService.getCarLogFile(id));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarLogDto create(CreateCarLogRequest request,
                            @RequestPart("file") MultipartFile file) throws IOException {
        return responseMapper.toDto(carLogService.create(request, file));
    }
    
    @PutMapping
    public CarLogDto update(UpdateCarLogRequest request,
                            @RequestPart("file") MultipartFile file) throws IOException {
        return responseMapper.toDto(carLogService.update(request, file));
    }

    @DeleteMapping("/{id}")
    public CarLogDto delete(@PathVariable Long id) {
        return responseMapper.toDto(carLogService.delete(id));
    }
    
}
