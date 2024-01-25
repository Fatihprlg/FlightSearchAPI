package org.fatihprlg.flightsearchapi.models.dtos;

import io.micrometer.common.util.StringUtils;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@NonNull
@Builder
public class AirportDto {
    @Setter
    private Integer id;
    private String city;

    public void setCity(String city){
        if (StringUtils.isEmpty(city)){
            throw new IllegalArgumentException("City value cannot be null/empty/blank.");
        }
        this.city = city;
    }
}
