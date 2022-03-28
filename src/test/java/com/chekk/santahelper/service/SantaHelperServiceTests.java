package com.chekk.santahelper.service;


import com.chekk.santahelper.SantahelperApplicationTests;
import com.chekk.santahelper.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;


public class SantaHelperServiceTests extends SantahelperApplicationTests {

    @Autowired
    SantaHelperService santaHelperService;

    private static final Integer TEST_HOOD_WEIGHT = 41;

    @Test(enabled = true)
    public void testHoodFiller() throws CustomException {
        // Test hood weight & present weights creation
        List<Integer> presentWeights = Arrays.asList(2, 5, 10, 50, 100);

        // Test fill hood weights
        List<Integer> optimalHoodWeights = santaHelperService.hoodFiller(TEST_HOOD_WEIGHT, presentWeights);

        Assert.assertNotNull(optimalHoodWeights);
        Assert.assertEquals(optimalHoodWeights.size(), 7);
        List<Integer> expectedOptimalHoodWeights = Arrays.asList(10, 10, 10, 5, 2, 2, 2);
        Collections.sort(optimalHoodWeights);
        Collections.sort(expectedOptimalHoodWeights);
        Assert.assertEquals(optimalHoodWeights, expectedOptimalHoodWeights);
    }

}
