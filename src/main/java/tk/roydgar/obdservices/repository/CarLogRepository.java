package tk.roydgar.obdservices.repository;

import org.springframework.data.repository.CrudRepository;
import tk.roydgar.obdservices.domain.CarLog;

import java.util.List;

public interface CarLogRepository extends CrudRepository<CarLog, Long> {

    List<CarLog> findAll();
    List<CarLog> findAllByCarId(Long carId);

}
