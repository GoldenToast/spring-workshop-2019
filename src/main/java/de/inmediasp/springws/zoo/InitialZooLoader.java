package de.inmediasp.springws.zoo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.inmediasp.springws.zoo.animals.Monkey;
import de.inmediasp.springws.zoo.animals.MonkeyRepository;
import de.inmediasp.springws.zoo.animals.Species;
import de.inmediasp.springws.zoo.animals.SpeciesRepository;
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

    private final List<String> species = Arrays.asList("gorilla", "mandrill", "orangutan", "chimpanzee");
    private final List<String> colors = Arrays.asList("black", "brown", "red", "grey");
    private final Random random = new Random();

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

        createRandomMonkeys(1000);
    }

    @Transactional
    public void createRandomMonkeys(final int numberOfRandomMonkeys) {
        for (int i = 0; i < numberOfRandomMonkeys; ++i) {
            final Monkey monkey = new Monkey();

            monkey.setAge(random.nextInt(100) + 1);
            monkey.setColor(colors.get(random.nextInt(4)));
            monkey.setHeight(random.nextInt(140) + 20);
            monkey.setType(speciesRepository.findByName(species.get(random.nextInt(4))).orElse(null));
            monkey.setName(createRandomWord());

            monkeyRepository.save(monkey);
        }
    }

    private String createRandomWord() {
        final char[] word = new char[random.nextInt(10) + 5];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }

        return new String(word);
    }

    @Transactional
    public void createSpecies() {
        species.forEach(s -> {
            final Species speciesEntity = new Species();
            speciesEntity.setName(s);
            speciesRepository.save(speciesEntity);
        });
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
