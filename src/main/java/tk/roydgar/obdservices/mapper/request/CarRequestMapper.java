package tk.roydgar.obdservices.mapper.request;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.domain.Car;
import tk.roydgar.obdservices.domain.dto.request.car.CreateCarRequest;

@Component
public class CarRequestMapper {

    public Car toEntity(CreateCarRequest request) {
        Car car = new Car();
        BeanUtils.copyProperties(request, car, "userId");
        return car;
    }

}
