package tk.roydgar.obdservices.domain.dto.request.car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarRequest extends CreateCarRequest {
    private Long id;
}
