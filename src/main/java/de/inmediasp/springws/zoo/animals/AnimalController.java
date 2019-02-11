package de.inmediasp.springws.zoo.animals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("animal")
public class AnimalController {
    private final MonkeyRepository monkeyRepository;

    @Autowired
    public AnimalController(final MonkeyRepository monkeyRepository) {
        this.monkeyRepository = monkeyRepository;
    }

    @GetMapping("monkey")
    public List<Monkey> getMonkeys(final Predicate predicate) {
        return StreamSupport.stream(monkeyRepository.findAll(predicate).spliterator(), false).collect(Collectors.toList());
    }
}
