package tk.roydgar.obdservices.mapper.response;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.domain.CarErrorLog;
import tk.roydgar.obdservices.domain.dto.response.CarErrorLogDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarErrorLogResponseMapper {

    public CarErrorLogDto toDto(CarErrorLog carErrorLog) {
        if (carErrorLog == null) {
            return null;
        }

        CarErrorLogDto carErrorLogDto = new CarErrorLogDto();
        BeanUtils.copyProperties(carErrorLog, carErrorLogDto, "car");
        carErrorLogDto.setCarId(carErrorLog.getCar().getId());
        return carErrorLogDto;
    }

    public List<CarErrorLogDto> toDtoList(List<CarErrorLog> carErrorLogs) {
        return carErrorLogs.stream().map(this::toDto).collect(Collectors.toList());
    }

}
