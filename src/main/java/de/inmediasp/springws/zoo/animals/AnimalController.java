package de.inmediasp.springws.zoo.animals;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

@RestController
@RequestMapping("animal")
public class AnimalController {
    private EntityManager entityManager;

    @Autowired
    public AnimalController(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("monkey")
    public List<Monkey> getMonkeysByName(final String value) {
        final JPAQuery<Void> jpaQuery = new JPAQuery<>(entityManager);
        final QMonkey monkey = QMonkey.monkey;

        return getByNameWithPredicate(jpaQuery, monkey, monkey.name.eq(value));
    }

    private List<Monkey> getByNameWithPredicate(final JPAQuery<Void> jpaQuery, final QMonkey qMonkey, final Predicate predicate) {
        //@formatter:off
        return jpaQuery
                .select(qMonkey)
                .from(qMonkey)
                .where(predicate)
                .fetch();
        //@formatter:on
    }
}
