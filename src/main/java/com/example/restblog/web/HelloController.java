package com.example.restblog.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        log.info("Howdy");
        return "Hello from Spring!";
    }

    @GetMapping("/hello/{personName}")
    @ResponseBody
    public String sayHello(@PathVariable String personName) {
        return "Hello, " + personName + "!";
    }

    @GetMapping("/increment/{number}")
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }
}
