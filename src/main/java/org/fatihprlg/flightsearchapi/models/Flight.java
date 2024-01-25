package org.fatihprlg.flightsearchapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "Flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Setter
    @Column(name = "departureAirportId")
    private Integer departureAirportId;
    @Setter
    @Column(name = "landingAirportId")
    private Integer landingAirportId;
    @Setter
    @Column(name = "departureTime")
    private Timestamp  departureTime;
    @Setter
    @Column(name = "returnTime")
    private Timestamp returnTime;
    @Setter
    @Column(name = "price")
    private Float price;

}
