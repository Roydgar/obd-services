package tk.roydgar.obdservices.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "car_id")
    private Long id;

    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private String vinCode;
    @Column
    private String engine;
    @Column
    private String ecuId;
    @Column
    private String firmwareVersion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<CarLog> carLogs;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<CarErrorLog> carErrorLogs;

}
