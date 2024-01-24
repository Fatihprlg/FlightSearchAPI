package org.fatihprlg.flightsearchapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class SearchController {

    @GetMapping("/get_flight")
    public String getFlight(){
        return "test";
    }
}
