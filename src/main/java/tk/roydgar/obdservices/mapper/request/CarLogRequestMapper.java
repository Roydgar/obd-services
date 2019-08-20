package tk.roydgar.obdservices.mapper.request;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.domain.CarLog;
import tk.roydgar.obdservices.domain.dto.request.carlog.CreateCarLogRequest;

@Component
public class CarLogRequestMapper {

    public CarLog toEntity(CreateCarLogRequest request) {
        CarLog carLog = new CarLog();
        BeanUtils.copyProperties(request, carLog, "car");
        return carLog;
    }

}
