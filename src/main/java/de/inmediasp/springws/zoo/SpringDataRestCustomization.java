package de.inmediasp.springws.zoo;

import de.inmediasp.springws.zoo.buildings.Building;
import de.inmediasp.springws.zoo.buildings.BuildingRepository;
import de.inmediasp.springws.zoo.buildings.BuildingType;
import de.inmediasp.springws.zoo.buildings.BuildingTypeRepository;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;


@Component
public class SpringDataRestCustomization implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(final RepositoryRestConfiguration config) {
        config.withEntityLookup().forRepository(BuildingRepository.class, Building::getName, BuildingRepository::findByName);

        config.withEntityLookup()
                .forValueRepository(BuildingTypeRepository.class, BuildingType::getName, BuildingTypeRepository::findByName);
    }
}
