package de.inmediasp.springws.zoo.animals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public Page<Animal> findAll(Pageable pageable){
        return animalRepository.findAll(pageable);
    }
}
