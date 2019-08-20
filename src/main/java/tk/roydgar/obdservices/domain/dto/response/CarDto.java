package tk.roydgar.obdservices.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private String vinCode;
    private String engine;
    private String ecuId;
    private String firmwareVersion;
    private List<CarLogDto> carLogs;
    private List<CarErrorLogDto> carErrorLogs;

}
