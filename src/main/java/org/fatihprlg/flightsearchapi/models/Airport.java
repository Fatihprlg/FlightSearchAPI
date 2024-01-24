package org.fatihprlg.flightsearchapi.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "Airport")
public class Airport
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Setter
    @Getter
    @Column(name = "city")
    private String city;

    public int getId() {
        return id;
    }
}
