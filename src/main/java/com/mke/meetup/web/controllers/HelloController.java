package com.mke.meetup.web.controllers;

import com.mke.meetup.domain.Person;
import com.mke.meetup.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class HelloController {

    private final HelloService helloService;
    private final AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

//    @GetMapping(path = "/", produces = "text/plain")
    @RequestMapping(path = "/", method = RequestMethod.GET, produces = "text/plain")
    public String world() {
        return "Hello, world!";
    }

    @GetMapping(path = "/name", produces = "text/plain")
    public String hello(@RequestParam("name") String name) {
        return helloService.getMessage() + " " + name;
    }

    @PostMapping(path = "/person", produces = "application/json")
    public Person createPerson(@RequestBody Person person) {
        person.setId(counter.incrementAndGet());

        return person;
    }

}
