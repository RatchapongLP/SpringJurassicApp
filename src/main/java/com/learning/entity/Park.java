package com.learning.entity;

import com.learning.WalkThroughPark;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;

public class Park implements InitializingBean, DisposableBean, ApplicationEventPublisherAware {

    private String name;
    private List<Cage> cages;
    private List<Dinosaur> dinosaurs;
    private int popularity;
    private MessageSource messageSource;
    private ApplicationEventPublisher publisher;

    public Park() {
    }
    public Park(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Cage> getCages() {
        return cages;
    }

    public List<Dinosaur> getDinosaurs() {
        return dinosaurs;
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

    public void setCages(List<Cage> cages) {
        this.cages = cages;
    }
    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void buildStats() {

        if (cages == null || cages.isEmpty()) return;
        int sum = 0;
        dinosaurs = new ArrayList<>();
        for (Cage cage : cages) {
            cage.checkStats();
            sum += cage.getPopularity();
            dinosaurs.addAll(cage.getDinosaurs());
        }
        popularity = (int) ((double) sum / cages.size());
    }

    @Override
    public String toString() {
        return messageSource.getMessage("park_sum", new Object[] {name, cages.size(), dinosaurs.size(), popularity}, "Hey yo from park", null);
    }

    public void listCages() {

        System.out.println(this.messageSource.getMessage("listing_cage", null, "Hey yo from park",null));
        if (cages == null || cages.isEmpty()) return;
        for (Cage cage : cages) {
            System.out.println(cage);
        }
        publisher.publishEvent(new WalkThroughPark(this));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean init method called for Park");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean destroy method called for Park");
    }

/*    This method will get called automatically by Spring, spring will pass the context as the parameter
    since the context also implements ApplicationEventPublisher interface */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
}
