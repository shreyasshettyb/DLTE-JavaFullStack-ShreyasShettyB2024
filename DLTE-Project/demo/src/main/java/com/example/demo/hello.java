package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class hello {
    public hello(){
        printter();
    }
    void printter(){
        System.out.println("hello");
    }

}
