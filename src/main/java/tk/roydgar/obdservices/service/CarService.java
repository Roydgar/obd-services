package tk.roydgar.obdservices.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.roydgar.obdservices.domain.Car;
import tk.roydgar.obdservices.domain.User;
import tk.roydgar.obdservices.domain.dto.request.car.CreateCarRequest;
import tk.roydgar.obdservices.domain.dto.request.car.UpdateCarRequest;
import tk.roydgar.obdservices.exception.ExceptionFactory;
import tk.roydgar.obdservices.mapper.request.CarRequestMapper;
import tk.roydgar.obdservices.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final UserService userService;
    private final CarRequestMapper carRequestMapper;

    public Car getById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.carNotFoundException(id));
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public List<Car> getAllByUserId(Long userId) {
        return carRepository.findAllByUserId(userId);
    }

    public Car create(CreateCarRequest createCarRequest) {
        Car car = carRequestMapper.toEntity(createCarRequest);
        User user = userService.getById(createCarRequest.getUserId());

        car.setUser(user);
        return carRepository.save(car);
    }

    public Car update(UpdateCarRequest updateCarRequest) {
        Car car = carRequestMapper.toEntity(updateCarRequest);
        User user = userService.getById(updateCarRequest.getUserId());

        car.setUser(user);
        return carRepository.save(car);
    }

    @Transactional
    public Car delete(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.carNotFoundException(id));

        carRepository.delete(car);
        return car;
    }

}
