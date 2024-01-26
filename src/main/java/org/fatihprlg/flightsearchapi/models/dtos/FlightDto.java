package org.fatihprlg.flightsearchapi.models.dtos;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class FlightDto {
    @NonNull
    private Integer id;
    @NonNull
    private Integer departureAirportId;
    @NonNull
    private Integer landingAirportId;
    @NonNull
    private Timestamp departureTime;

    private Timestamp returnTime;
    @NonNull
    private Float price;
}
