package de.inmediasp.springws.zoo.buildings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "buildingtypes")
public interface BuildingTypeRepository extends JpaRepository<BuildingType, Long> {

    Optional<BuildingType> findByName(String value);
}
