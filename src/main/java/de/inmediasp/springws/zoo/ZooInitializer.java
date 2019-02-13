package de.inmediasp.springws.zoo;

import de.inmediasp.springws.zoo.animals.Animal;
import de.inmediasp.springws.zoo.animals.AnimalRepository;
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
    private final AnimalRepository animalRepository;

    @Autowired
    public ZooInitializer(final BuildingRepository buildingRepository,
            final BuildingTypeRepository buildingTypeRepository,
            final AnimalRepository animalRepository) {
        this.buildingRepository = buildingRepository;
        this.buildingTypeRepository = buildingTypeRepository;
        this.animalRepository = animalRepository;
    }

    @EventListener
    public void init(ApplicationReadyEvent event) {
        if (buildingRepository.count() > 0) {
            return;
        }

        buildingRepository.deleteAll();
        buildingTypeRepository.deleteAll();
        animalRepository.deleteAll();

        createZoo();

    }

    @Transactional
    public void createZoo() {
        createEnclosureTypes();
        createEnclosures();
        createAnimals();
    }

    @Transactional
    private void createAnimals() {
        animalRepository.save(new Animal("Affe"));
        animalRepository.save(new Animal("Kuhfisch"));
        animalRepository.save(new Animal("Glasfrosch"));
        animalRepository.save(new Animal("Sägerochen"));
        animalRepository.save(new Animal("Spiegeleiqualle"));
        animalRepository.save(new Animal("Streifentenrek"));
        animalRepository.save(new Animal("Seefledermaus"));
        animalRepository.save(new Animal("Saiga"));
        animalRepository.save(new Animal("Gespensterfisch"));
        animalRepository.save(new Animal("Glasflügler"));
        animalRepository.save(new Animal("Moschustier"));
        animalRepository.save(new Animal("Dugong"));
        animalRepository.save(new Animal("Ozeanschnecke"));
        animalRepository.save(new Animal("Riesenassel"));
        animalRepository.save(new Animal("Fetzenfisch"));
        animalRepository.save(new Animal("Blattschwanzgecko"));

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
