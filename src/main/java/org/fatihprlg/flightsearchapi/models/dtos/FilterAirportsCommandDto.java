package org.fatihprlg.flightsearchapi.models.dtos;

import jakarta.annotation.Nullable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
@Nullable
public class FilterAirportsCommandDto {
    private Integer id;
    private String city;
}
