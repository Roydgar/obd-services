package tk.roydgar.obdservices.repository;

import org.springframework.data.repository.CrudRepository;
import tk.roydgar.obdservices.domain.CarErrorLog;

import java.util.List;

public interface CarErrorLogRepository extends CrudRepository<CarErrorLog, Long> {

    List<CarErrorLog> findAll();
    List<CarErrorLog> findAllByCarId(Long carId);

}
