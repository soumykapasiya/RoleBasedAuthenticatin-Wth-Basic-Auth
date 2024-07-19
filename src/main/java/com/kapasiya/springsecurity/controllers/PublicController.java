package com.kapasiya.springsecurity.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/public")
public class PublicController
{
    @GetMapping("/home")
    public ResponseEntity<ModelAndView> publicHome()
    {
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("message", "Hello World");
        return ResponseEntity.ok(mav);
    }
}
