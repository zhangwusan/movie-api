package com.api.movie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ContentController {

    @GetMapping
    public String indexPage() {
        return "hello to index page";
    }
}
