package tk.roydgar.obdservices.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.roydgar.obdservices.domain.CarErrorLog;
import tk.roydgar.obdservices.domain.dto.request.carerrorlog.CreateCarErrorLogRequest;
import tk.roydgar.obdservices.domain.dto.request.carerrorlog.UpdateCarErrorLogRequest;
import tk.roydgar.obdservices.domain.dto.response.CarErrorLogDto;
import tk.roydgar.obdservices.mapper.response.CarErrorLogResponseMapper;
import tk.roydgar.obdservices.service.CarErrorLogService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/car-error-log")
@RequiredArgsConstructor
public class CarErrorLogController {

    private final CarErrorLogService carErrorLogService;
    private final CarErrorLogResponseMapper responseMapper;

    @GetMapping("/{id}")
    public CarErrorLogDto findById(@PathVariable Long id) {
        return responseMapper.toDto(carErrorLogService.getById(id));
    }

    @GetMapping()
    public List<CarErrorLogDto> getAll(@RequestParam(value = "carId", required = false) Long carId) {
        List<CarErrorLog> carErrorLogDtos = carId == null
                ? carErrorLogService.getAll()
                : carErrorLogService.getAllByCarId(carId);

        return responseMapper.toDtoList(carErrorLogDtos);
    }

    @GetMapping(
            value = "/file/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody
    byte[] getFile(@PathVariable Long id) {
        return carErrorLogService.getCarLogFileAsByteArray(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarErrorLogDto create(CreateCarErrorLogRequest request,
                                 @RequestPart("file") MultipartFile file) throws IOException {
        return responseMapper.toDto(carErrorLogService.create(request, file));
    }
    
    @PutMapping
    public CarErrorLogDto update(UpdateCarErrorLogRequest request,
                                 @RequestPart("file") MultipartFile file) throws IOException{
        return responseMapper.toDto(carErrorLogService.update(request, file));
    }

    @DeleteMapping("/{id}")
    public CarErrorLogDto delete(@PathVariable Long id) {
        return responseMapper.toDto(carErrorLogService.delete(id));
    }
    
}
