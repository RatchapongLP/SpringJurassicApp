package com.learning.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;

public class Cage {

    private String name;
    private List<Dinosaur> dinosaurs;
    private int cleanness;
    private int popularity;
    private MessageSource messageSource;
    private boolean isSetup;

    public Cage() {
        cleanness = 100;
    }

    public String getName() {
        return name;
    }

    public List<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }

    public int getCleanness() {
        return cleanness;
    }

    public int getPopularity() {
        return popularity;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCleanness(int cleanness) {
        this.cleanness = cleanness;
    }

    public void setDinosaurs(List<Dinosaur> dinosaurs) {

        this.dinosaurs = dinosaurs;
        setup();
        isSetup = true;
    }
    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void addDino(Dinosaur dino) {

        popularity = (popularity * dinosaurs.size() + dino.getPopularity()) / (dinosaurs.size() + 1);
        dinosaurs.add(dino);
    }

    private void setup() {

        if (dinosaurs == null) dinosaurs = new ArrayList<>();
        int sum = 0;
        for (Dinosaur dino : dinosaurs) {
            sum += dino.getPopularity();
        }
        popularity = (int) ((double) sum / dinosaurs.size());
    }

    public void checkStats() {

        if (!isSetup) setup();
    }

    @Override
    public String toString() {
        return messageSource.getMessage("cage_sum", new Object[] {name, dinosaurs.size(), popularity, cleanness <= 70}, "Hey yo from cage", null);
    }

    public void myInit() {
        System.out.println("myInit method called for " + this.name + this.getClass().getSimpleName());
    }

    public void destroy() {
        System.out.println("destroy method called for " + this.name + this.getClass().getSimpleName());
    }
}
