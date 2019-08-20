package tk.roydgar.obdservices.mapper.request;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tk.roydgar.obdservices.domain.CarErrorLog;
import tk.roydgar.obdservices.domain.dto.request.carerrorlog.CreateCarErrorLogRequest;

@Component
public class CarErrorLogRequestMapper {

    public CarErrorLog toEntity(CreateCarErrorLogRequest request) {
        CarErrorLog carErrorLog = new CarErrorLog();
        BeanUtils.copyProperties(request, carErrorLog, "car");
        return carErrorLog;
    }

}
