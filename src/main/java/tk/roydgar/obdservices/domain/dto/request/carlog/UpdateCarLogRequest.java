package tk.roydgar.obdservices.domain.dto.request.carlog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarLogRequest extends CreateCarLogRequest {
    private Long id;
}
