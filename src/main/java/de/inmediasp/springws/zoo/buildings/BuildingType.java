package de.inmediasp.springws.zoo.buildings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * AVIARY, CAGE, TERRARIUM, AQUARIUM
 */
@Entity
@Getter
@EqualsAndHashCode(of="id")
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class BuildingType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private final @NotEmpty String name;
}
