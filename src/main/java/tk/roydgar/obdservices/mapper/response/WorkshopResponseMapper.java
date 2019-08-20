package tk.roydgar.obdservices.mapper.response;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.domain.Workshop;
import tk.roydgar.obdservices.domain.dto.response.WorkshopDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkshopResponseMapper {

    public WorkshopDto toDto(Workshop workshop) {
        if (workshop == null) {
            return null;
        }
        WorkshopDto workshopDto = new WorkshopDto();
        BeanUtils.copyProperties(workshop, workshopDto, "password");
        return workshopDto;
    }

    public List<WorkshopDto> toDtoList(List<Workshop> cars) {
        return cars.stream().map(this::toDto).collect(Collectors.toList());
    }
}
