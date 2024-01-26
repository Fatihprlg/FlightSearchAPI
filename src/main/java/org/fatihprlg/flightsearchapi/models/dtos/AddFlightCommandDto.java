package org.fatihprlg.flightsearchapi.models.dtos;

import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;


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

    public void setLandingAirportId(Integer landingAirportId){
        if(Objects.equals(landingAirportId, this.departureAirportId)){
            throw new IllegalArgumentException("Landing airport cannot be same with departure airport.");
        }
        this.landingAirportId = landingAirportId;
    }

    public void setReturnTime(Timestamp returnTime){
        if(returnTime.compareTo(this.departureTime) <= 0){
            throw new IllegalArgumentException("Return time cannot be same with or before than departure time.");
        }
        this.returnTime = returnTime;
    }
}
