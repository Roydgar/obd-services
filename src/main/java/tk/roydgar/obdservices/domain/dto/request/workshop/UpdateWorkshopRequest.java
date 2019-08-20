package tk.roydgar.obdservices.domain.dto.request.workshop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateWorkshopRequest extends CreateWorkshopRequest {

    private Long id;

}
