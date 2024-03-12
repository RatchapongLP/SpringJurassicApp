package com.learning;

import com.learning.entity.Park;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JurassicParkApp {

    public static void main(String[] args) {

        // An abstract class possessing an ability to destroy all the beans at app shutdown, if configured to
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        context.registerShutdownHook();

        System.out.println();
        System.out.println(context.getMessage("greeting", null, "Hi, how are things?", null));

        Park park = context.getBean(Park.class);
        park.buildStats();
        System.out.println(park);
        park.listCages();
        System.out.println();
    }
}
