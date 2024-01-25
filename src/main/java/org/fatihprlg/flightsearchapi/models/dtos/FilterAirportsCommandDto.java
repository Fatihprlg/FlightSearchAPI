package org.fatihprlg.flightsearchapi.models.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class FilterAirportsCommandDto {
    private Integer id;
    private String city;
}
