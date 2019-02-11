package de.inmediasp.springws.zoo.animals;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Monkey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int age;
    private int height;

    private String name;
    private String color;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species type;
}
