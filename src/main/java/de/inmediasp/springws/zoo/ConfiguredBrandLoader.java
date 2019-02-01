package de.inmediasp.springws.zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.inmediasp.springws.zoo.buildings.Enclosure;
import de.inmediasp.springws.zoo.buildings.EnclosureRepository;

@Component
public class ConfiguredBrandLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final EnclosureRepository enclosureRepository;

    private boolean alreadySetup;

    @Autowired
    public ConfiguredBrandLoader(final EnclosureRepository enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        deleteExistingBrandFromRepo();
        createBrands();

        alreadySetup = true;
    }

    @Transactional
    public void createBrands() {
        final Enclosure enclosure = new Enclosure();
        enclosure.setName("Affenhaus");
        enclosure.setLength(1);
        enclosure.setWidth(1);

        enclosureRepository.save(enclosure);
    }

    private void deleteExistingBrandFromRepo() {
        enclosureRepository.deleteAll();
    }
}
