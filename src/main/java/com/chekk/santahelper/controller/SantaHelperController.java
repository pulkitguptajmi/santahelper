package com.chekk.santahelper.controller;


import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequestMapping("/")
public interface SantaHelperController {

    @PostMapping("/hoodfiller")
    HttpEntity<List<Integer>> hoodFiller(@RequestParam(value = "hood_capacity") Integer hoodCapacity,
                                         @RequestParam(value = "present_weights") List<Integer> presentWeights);

}
