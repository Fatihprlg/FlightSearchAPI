package org.fatihprlg.flightsearchapi.models.dtos;

import lombok.*;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class FilterFlightsCommandDto {
    private Integer id;
    private Integer departureAirportId;
    private Integer landingAirportId;
    private Timestamp departureTime;
    private Timestamp returnTime;
    private Float price;
}
