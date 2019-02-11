package de.inmediasp.springws.zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.inmediasp.springws.zoo.animals.Monkey;
import de.inmediasp.springws.zoo.animals.Species;
import de.inmediasp.springws.zoo.animals.SpeciesRepository;
import de.inmediasp.springws.zoo.animals.MonkeyRepository;
import de.inmediasp.springws.zoo.buildings.Enclosure;
import de.inmediasp.springws.zoo.buildings.EnclosureRepository;
import de.inmediasp.springws.zoo.buildings.EnclosureType;
import de.inmediasp.springws.zoo.buildings.EnclosureTypeRepository;

@Component
public class InitialZooLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final SpeciesRepository speciesRepository;
    private final MonkeyRepository monkeyRepository;
    private final EnclosureRepository enclosureRepository;
    private final EnclosureTypeRepository enclosureTypeRepository;

    private boolean alreadySetup;

    @Autowired
    public InitialZooLoader(final SpeciesRepository speciesRepository, final MonkeyRepository monkeyRepository, final EnclosureRepository enclosureRepository,
            final EnclosureTypeRepository enclosureTypeRepository) {
        this.speciesRepository = speciesRepository;
        this.monkeyRepository = monkeyRepository;
        this.enclosureRepository = enclosureRepository;
        this.enclosureTypeRepository = enclosureTypeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        enclosureRepository.deleteAll();
        enclosureTypeRepository.deleteAll();

        createZoo();

        alreadySetup = true;
    }

    @Transactional
    public void createZoo() {
        createEnclosureTypes();
        createEnclosures();

        createAnimals();
    }

    @Transactional
    public void createAnimals() {
        createSpecies();
        createMonkeys();
    }

    @Transactional
    public void createMonkeys() {
        final Monkey kong = new Monkey();
        kong.setAge(15);
        kong.setColor("black");
        kong.setHeight(120);
        kong.setName("Kong");
        kong.setType(speciesRepository.findByName("gorilla").orElse(null));

        monkeyRepository.save(kong);
    }

    @Transactional
    public void createSpecies() {
        final Species orangutan = new Species();
        orangutan.setName("orangutan");
        speciesRepository.save(orangutan);

        final Species mandrill = new Species();
        mandrill.setName("mandrill");
        speciesRepository.save(mandrill);

        final Species gorilla = new Species();
        gorilla.setName("gorilla");
        speciesRepository.save(gorilla);

        final Species chimpanzee = new Species();
        chimpanzee.setName("chimpanzee");
        speciesRepository.save(chimpanzee);
    }

    @Transactional
    public void createEnclosures() {
        final Enclosure enclosure = new Enclosure();
        enclosure.setName("Ape House");
        enclosure.setLength(1);
        enclosure.setWidth(1);

        enclosure.setType(enclosureTypeRepository.findByName("Cage").orElse(null));

        enclosureRepository.save(enclosure);
    }

    @Transactional
    public void createEnclosureTypes() {
        final EnclosureType cage = new EnclosureType();
        cage.setName("Cage");

        enclosureTypeRepository.save(cage);
    }
}
