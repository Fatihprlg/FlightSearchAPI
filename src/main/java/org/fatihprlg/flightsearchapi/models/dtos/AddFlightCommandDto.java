package org.fatihprlg.flightsearchapi.models.dtos;

import java.util.Date;
import java.util.Objects;

public record AddFlightCommandDto(Integer departureAirportId, Integer landingAirportId, Date departureTime, Date landingTime, Float price) {
    public AddFlightCommandDto {
        Objects.requireNonNull(departureAirportId);
        Objects.requireNonNull(landingAirportId);
        Objects.requireNonNull(departureTime);
        Objects.requireNonNull(landingTime);
        Objects.requireNonNull(price);
    }
}
