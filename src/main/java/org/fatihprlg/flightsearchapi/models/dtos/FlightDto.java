package org.fatihprlg.flightsearchapi.models.dtos;

import java.util.Date;
import java.util.Objects;

public record FlightDto(Integer id, Integer departureAirportId, Integer landingAirportId, Date departureTime, Date landingTime, Float price) {
    public FlightDto {
        Objects.requireNonNull(id);
        Objects.requireNonNull(departureAirportId);
        Objects.requireNonNull(landingAirportId);
        Objects.requireNonNull(departureTime);
        Objects.requireNonNull(landingTime);
        Objects.requireNonNull(price);
    }
}
