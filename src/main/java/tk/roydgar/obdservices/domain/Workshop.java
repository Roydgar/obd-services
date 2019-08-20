package tk.roydgar.obdservices.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String address;
    @Column
    private String phoneNumber;
    @Column
    private String email;

}
