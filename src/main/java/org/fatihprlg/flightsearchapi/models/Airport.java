package org.fatihprlg.flightsearchapi.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "Airport")
public class Airport
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "city")
    @Setter
    private String city;

}
