package tk.roydgar.obdservices.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkshopDto {

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

}
