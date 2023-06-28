package com.caonam.qlbn.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "hello";
    }
}
