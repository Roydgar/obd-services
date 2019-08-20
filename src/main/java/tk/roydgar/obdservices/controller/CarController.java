package tk.roydgar.obdservices.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tk.roydgar.obdservices.domain.Car;
import tk.roydgar.obdservices.domain.dto.request.car.CreateCarRequest;
import tk.roydgar.obdservices.domain.dto.request.car.UpdateCarRequest;
import tk.roydgar.obdservices.domain.dto.response.CarDto;
import tk.roydgar.obdservices.mapper.response.CarResponseMapper;
import tk.roydgar.obdservices.service.CarService;

import java.util.List;

@RestController
@RequestMapping("api/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final CarResponseMapper responseMapper;


    @GetMapping("/{id}")
    public CarDto findById(@PathVariable Long id) {
        return responseMapper.toDto(carService.getById(id));
    }

    @GetMapping
    public List<CarDto> getAll(@RequestParam(value = "userId", required = false) Long userId) {
        List<Car> cars = userId == null ? carService.getAll() : carService.getAllByUserId(userId);
        return responseMapper.toDtoList(cars);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto create(CreateCarRequest request) {
        return responseMapper.toDto(carService.create(request));
    }

    @PutMapping
    public CarDto update(UpdateCarRequest request) {
        return responseMapper.toDto(carService.update(request));
    }

    @DeleteMapping("/{id}")
    public CarDto delete(@PathVariable Long id) {
        return responseMapper.toDto(carService.delete(id));
    }

}
