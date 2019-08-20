package tk.roydgar.obdservices.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String login;
    private String phoneNumber;
    private String email;
    private List<CarDto> cars;

}
