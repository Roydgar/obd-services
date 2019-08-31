package tk.roydgar.obdservices.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "car_error_log")
public class CarErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "car_error_log_id")
    private Long id;

    @Column
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
