package com.mke.meetup.services;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String getMessage() {
        try {
            // Simulate long running process
            Thread.sleep(30 * 1000);

            return "Hello, ";
        } catch (Exception e) {
            return "crashed!";
        }
    }
}
