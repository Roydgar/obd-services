package tk.roydgar.obdservices.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "car_log")
public class CarLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_log_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

}
