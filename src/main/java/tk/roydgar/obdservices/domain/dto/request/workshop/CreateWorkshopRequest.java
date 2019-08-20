package tk.roydgar.obdservices.domain.dto.request.workshop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWorkshopRequest {

    private String name;
    private String password;
    private String address;
    private String phoneNumber;
    private String email;

}
