package tk.roydgar.obdservices.mapper.response;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.domain.Car;
import tk.roydgar.obdservices.domain.dto.response.CarDto;
import tk.roydgar.obdservices.domain.dto.response.CarErrorLogDto;
import tk.roydgar.obdservices.domain.dto.response.CarLogDto;
import tk.roydgar.obdservices.util.StreamUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CarResponseMapper {

    private final CarLogResponseMapper carLogResponseMapper;
    private final CarErrorLogResponseMapper carErrorLogResponseMapper;

    public CarDto toDto(Car car) {
        if (car == null) {
            return null;
        }
        CarDto carDto = new CarDto();
        BeanUtils.copyProperties(car, carDto, "carLog", "carErrorLog");

        List<CarLogDto> carLogDtos = StreamUtils.ofNullable(car.getCarLogs())
                .map(carLogResponseMapper::toDto)
                .collect(Collectors.toList());

        List<CarErrorLogDto> carErrorLogDtos = StreamUtils.ofNullable(car.getCarErrorLogs())
                .map(carErrorLogResponseMapper::toDto)
                .collect(Collectors.toList());

        carDto.setCarLogs(carLogDtos);
        carDto.setCarErrorLogs(carErrorLogDtos);
        return carDto;
    }

    public List<CarDto> toDtoList(List<Car> cars) {
        return cars.stream().map(this::toDto).collect(Collectors.toList());
    }

}
