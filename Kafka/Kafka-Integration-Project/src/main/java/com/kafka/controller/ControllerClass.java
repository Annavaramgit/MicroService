package com.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping
    public ResponseEntity<String> testMethod(){
        return ResponseEntity.ok("working fine....");
    }

    @PostMapping("/kafka-producer")
    public void kafkaProducerMethod(){
        System.out.println("producer entered");
        kafkaTemplate.send("test","produced !!!");
        System.out.println("producer ended");

    }

    @KafkaListener(topics = "test",groupId = "test")
    public void kafkaConsumerMethod(String message){
        System.out.println("consumer entered");
        System.out.println("message is : "+message);
        System.out.println("producer ended");

    }

}
