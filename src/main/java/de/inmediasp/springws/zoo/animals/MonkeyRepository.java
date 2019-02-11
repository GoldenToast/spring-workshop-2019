package de.inmediasp.springws.zoo.animals;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.querydsl.core.types.dsl.StringExpression;

@RepositoryRestResource
public interface MonkeyRepository extends JpaRepository<Monkey, Long>, QuerydslPredicateExecutor<Monkey>, QuerydslBinderCustomizer<QMonkey> {
    Page<Monkey> findByAgeAndType(@Param("age") int age, @Param("type") Species type, Pageable pageable);

    @Override
    default void customize(final QuerydslBindings bindings, final QMonkey root) {
        bindings.bind(root.name).first(StringExpression::containsIgnoreCase);
    }
}
