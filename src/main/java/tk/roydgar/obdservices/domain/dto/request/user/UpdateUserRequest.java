package tk.roydgar.obdservices.domain.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest extends CreateUserRequest {

    private Long id;

}
