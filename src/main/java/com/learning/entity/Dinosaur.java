package com.learning.entity;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Dinosaur {

    private String name;
    private String species;
    private int age;
    private int health;
    private int popularity;

    public Dinosaur() {

    }
    public Dinosaur(String name, String species) {
        this.name = name;
        this.species = species;
        this.health = 100;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public int getHealth() {
        return health;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Dinosaur{" +
                "name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", health=" + health +
                ", popularity=" + popularity +
                '}';
    }

//    With these two annotations, no need for configuring the init-method and destroy-method attribute in the xml
    @PostConstruct
    public void init() {
        System.out.println("myInit method called for " + this.name + " " + this.getClass().getSimpleName());
    }
    @PreDestroy
    public void destruct() {
        System.out.println("destroy method called for " + this.name +  " " + this.getClass().getSimpleName());
    }
}
