package de.inmediasp.springws.zoo.buildings;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;
import org.springframework.hateoas.Identifiable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class Building implements Identifiable<Long> {

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Getter
    @ToString
    public static class Location {

        private @Range(min = -90l, max = 90l) int lat;
        private @Range(min = -180l, max = 180l) int lon;
    }

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Getter
    @ToString
    public static class Size {

        @Getter private @Positive int length;
        @Getter private @Positive int width;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;
    @Version private Long version;
    private final @NotEmpty String name;
    private final @Valid Size size;
    private final @Valid Location location;
    @OneToOne
    private final @Valid BuildingType buildingType;

    @Setter
    private boolean locked;

    public Building(String name, Location location, Size size, BuildingType buildingType) {
        this.id = null;
        this.name = name;
        this.location = location;
        this.size = size;
        this.buildingType = buildingType;
        this.locked = false;
    }

}
