package tk.roydgar.obdservices.mapper.request;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.domain.Workshop;
import tk.roydgar.obdservices.domain.dto.request.workshop.CreateWorkshopRequest;

@Component
public class WorkshopRequestMapper {

    public Workshop toEntity(CreateWorkshopRequest request) {
        Workshop workshop = new Workshop();
        BeanUtils.copyProperties(request, workshop);
        return workshop;
    }

}
