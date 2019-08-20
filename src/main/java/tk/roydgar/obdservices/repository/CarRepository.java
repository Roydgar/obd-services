package tk.roydgar.obdservices.repository;

import org.springframework.data.repository.CrudRepository;
import tk.roydgar.obdservices.domain.Car;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAll();
    List<Car> findAllByUserId(Long userId);
}
