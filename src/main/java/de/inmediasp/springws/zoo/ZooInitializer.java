package de.inmediasp.springws.zoo;

import de.inmediasp.springws.zoo.buildings.Building;
import de.inmediasp.springws.zoo.buildings.BuildingRepository;
import de.inmediasp.springws.zoo.buildings.BuildingType;
import de.inmediasp.springws.zoo.buildings.BuildingTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ZooInitializer {

    private final BuildingRepository buildingRepository;
    private final BuildingTypeRepository buildingTypeRepository;

    @Autowired
    public ZooInitializer(final BuildingRepository buildingRepository,
            final BuildingTypeRepository buildingTypeRepository) {
        this.buildingRepository = buildingRepository;
        this.buildingTypeRepository = buildingTypeRepository;
    }

    @EventListener
    public void init(ApplicationReadyEvent event) {
        if (buildingRepository.count() > 0) {
            return;
        }

        buildingRepository.deleteAll();
        buildingTypeRepository.deleteAll();

        createZoo();

    }

    @Transactional
    public void createZoo() {
        createEnclosureTypes();
        createEnclosures();
    }

    @Transactional
    public void createEnclosures() {
        BuildingType buildingType = buildingTypeRepository.findByName("Cage").orElse(null);
        Building.Location location = new Building.Location(0, 0);
        Building.Size size = new Building.Size(10, 10);
        final Building building = new Building("Ape House", location, size, buildingType);
        buildingRepository.save(building);
    }

    @Transactional
    public void createEnclosureTypes() {
        final BuildingType cage = new BuildingType("Cage");
        buildingTypeRepository.save(cage);
    }
}
