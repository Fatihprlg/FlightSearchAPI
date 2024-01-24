package org.fatihprlg.flightsearchapi.models.dtos;

import java.util.Objects;

public record AirportDto(Integer id, String city) {
    public AirportDto {
        Objects.requireNonNull(id);
        Objects.requireNonNull(city);
    }
}
