package com.chekk.santahelper.controller.impl;


import com.chekk.santahelper.ApplicationConstants;
import com.chekk.santahelper.controller.SantaHelperController;
import com.chekk.santahelper.exceptions.CustomException;
import com.chekk.santahelper.service.SantaHelperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController("santaHelperControllerImpl")
public class SantaHelperControllerImpl implements SantaHelperController {

    @Autowired
    SantaHelperService santaHelperService;

    private static final Logger logger = LoggerFactory.getLogger(SantaHelperControllerImpl.class);

    @Override
    public HttpEntity<List<Integer>> hoodFiller(Integer hoodCapacity, List<Integer> presentWeights) {
        logger.debug(String.format("Request parameters received. Hood Capacity: %d, Present Weights: %s",
                hoodCapacity, presentWeights.toString()));

        try {
            List<Integer> optimalPresentsSelection = santaHelperService.hoodFiller(hoodCapacity, presentWeights);
            return new ResponseEntity(optimalPresentsSelection, HttpStatus.OK);
        } catch (CustomException ce) {
            return new ResponseEntity(ce.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(ApplicationConstants.UNEXPECTED_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
