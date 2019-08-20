package tk.roydgar.obdservices.domain.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    private String login;
    private String phoneNumber;
    private String email;
    private String password;

}
