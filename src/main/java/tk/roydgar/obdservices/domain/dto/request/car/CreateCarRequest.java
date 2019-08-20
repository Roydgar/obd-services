package tk.roydgar.obdservices.domain.dto.request.car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCarRequest {

    private Long userId;
    private String brand;
    private String model;
    private String vinCode;
    private String engine;
    private String ecuId;
    private String firmwareVersion;

}
