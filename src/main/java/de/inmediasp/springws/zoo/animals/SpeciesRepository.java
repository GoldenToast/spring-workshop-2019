package de.inmediasp.springws.zoo.animals;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SpeciesRepository extends JpaRepository<Species, Long> {
    Optional<Species> findByName(String name);
}
