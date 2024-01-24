package org.fatihprlg.flightsearchapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "Flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Setter
    @Getter
    @Column(name = "departureAirportId")
    private Integer departureAirportId;
    @Setter
    @Getter
    @Column(name = "landingAirportId")
    private Integer landingAirportId;
    @Setter
    @Getter
    @Column(name = "departureTime")
    private Date  departureTime;
    @Setter
    @Getter
    @Column(name = "landingTime")
    private Date  landingTime;
    @Setter
    @Getter
    @Column(name = "price")
    private Float price;

    public int getId() {
        return id;
    }

}
