package de.inmediasp.springws.zoo.buildings;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.validation.annotation.Validated;

@RepositoryRestResource
public interface BuildingRepository extends JpaRepository<Building, Long> {

    Optional<Building> findByName(String id);
}
