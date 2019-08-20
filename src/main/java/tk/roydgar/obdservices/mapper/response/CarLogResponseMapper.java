package tk.roydgar.obdservices.mapper.response;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.domain.CarLog;
import tk.roydgar.obdservices.domain.dto.response.CarLogDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarLogResponseMapper {

    public CarLogDto toDto(CarLog carLog) {
        if (carLog == null) {
            return null;
        }
        CarLogDto carLogDto = new CarLogDto();
        BeanUtils.copyProperties(carLog, carLogDto, "car");
        carLogDto.setCarId(carLog.getCar().getId());
        return carLogDto;
    }

    public List<CarLogDto> toDtoList(List<CarLog> carErrorLogs) {
        return carErrorLogs.stream().map(this::toDto).collect(Collectors.toList());
    }

}
