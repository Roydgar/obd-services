package tk.roydgar.obdservices.repository;

import org.springframework.data.repository.CrudRepository;
import tk.roydgar.obdservices.domain.Workshop;

import java.util.List;

public interface WorkshopRepository extends CrudRepository<Workshop, Long> {
    List<Workshop> findAll();
}
