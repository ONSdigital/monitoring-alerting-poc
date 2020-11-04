package com.mynotes.spring.micrometer.prometheus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.LogManager;

@RestController
public class SimpleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);


    @GetMapping("/hello")
    public String hello() {
        LOGGER.info( "This is hello Get call");
        return "Hello from test service!!";
    }

    @GetMapping("/hello400")
    public ResponseEntity hello400() {
        LOGGER.info( "This is 400 HTTP status call");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/hello500")
    public ResponseEntity hello500() {
        LOGGER.info( "This is 500 HTTP status call");
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/latency")
    public ResponseEntity latency() {
        int sleepFor = 11;
        try {
            Thread.sleep(1000 * sleepFor);
        } catch(InterruptedException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/createLogs")
    public ResponseEntity createLogs() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    LOGGER.info("Generating Suraj info logs");
                }
            }
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    LOGGER.debug("Generating Suraj debug logs");
                }
            }
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    LOGGER.trace("Generating Suraj trace logs");
                }
            }
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    LOGGER.warn("Generating Suraj warn logs");
                }
            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    LOGGER.error("Generating Suraj error logs");
                }
            }
        }).start();
        return new ResponseEntity(HttpStatus.OK);
    }
}
