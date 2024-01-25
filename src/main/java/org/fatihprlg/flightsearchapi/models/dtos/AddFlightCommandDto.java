package org.fatihprlg.flightsearchapi.models.dtos;

import lombok.*;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class AddFlightCommandDto {
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
