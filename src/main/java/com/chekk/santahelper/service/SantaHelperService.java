package com.chekk.santahelper.service;


import com.chekk.santahelper.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@Service("santaHelperService")
public class SantaHelperService {

    private static final Logger logger = LoggerFactory.getLogger(SantaHelperService.class);

    // Assuming the maximum capacity of the hood can be 100,000 weight units (Reasonable assumption)
    private static final int MAX_HOOD_CAPACITY = 100000;

    public List<Integer> hoodFiller(Integer hoodWeight,
                                    List<Integer> presentWeights) throws CustomException {
        validateBeforeCalculation(hoodWeight, presentWeights);

        // Create a memoization array
        int[] memoizationArray = new int[MAX_HOOD_CAPACITY + 1];
        // Holds information on the filled presents (if filling is possible)
        List<Integer> filledHoodWeights = new LinkedList<>();
        // Decide if filling the hood is possible and if yes, return optimally filled hood
        hoodFillerHelper(hoodWeight, presentWeights, memoizationArray, filledHoodWeights);

        return filledHoodWeights;
    }

    private void hoodFillerHelper(Integer hoodWeight, List<Integer> presentWeights,
                                  int[] memoizationArray, List<Integer> filledHoodWeights) throws CustomException {
        // Initialize memoization array with -1
        Arrays.fill(memoizationArray, -1);

        // Determine if filling the hood is possible with the provided present weights
        int isFillingHoodPossible = countMinPresents(hoodWeight, presentWeights, memoizationArray);
        if (isFillingHoodPossible == Integer.MAX_VALUE) { // Filling Not possible
            logger.error("Filling the hood with the present weights was found to be not possible");
            throw new CustomException("Filling the hood with the given weights is not possible");
        }

        // Filling is possible. Find optimal way to fill the hood
        findHoodWeights(hoodWeight, presentWeights, memoizationArray, filledHoodWeights);
    }

    private void findHoodWeights(Integer hoodWeight, List<Integer> presentWeights, int[] memoizationArray,
                                 List<Integer> filledHoodWeights) {
        // Base Case (hood has no capacity)
        if (hoodWeight == 0) {
            return;
        }

        for (int idx = 0; idx < presentWeights.size(); idx++) {
            // Try every present that has weight smaller than hood weight
            int weightDifference = hoodWeight - presentWeights.get(idx);
            if (weightDifference >= 0 &&
                    (memoizationArray[weightDifference] + 1 == memoizationArray[hoodWeight])) {
                // Add current present weight to the hood
                filledHoodWeights.add(presentWeights.get(idx));

                // Backtracking
                findHoodWeights(weightDifference, presentWeights, memoizationArray, filledHoodWeights);
                break;
            }
        }
    }

    private Integer countMinPresents(Integer hoodWeight, List<Integer> presentWeights,
                                     int[] memoizationArray) {
        // Base case (hood has no capacity)
        if (hoodWeight == 0) {
            memoizationArray[0] = 0;
            return 0;
        }

        // If previously computed, return previously computed result (memoization)
        if (memoizationArray[hoodWeight] != -1) {
            return memoizationArray[hoodWeight];
        }

        Integer decision = Integer.MAX_VALUE;
        // Try every present that has smaller weight than the hood weight
        for (int idx = 0; idx < presentWeights.size(); idx++) {
            if (presentWeights.get(idx) <= hoodWeight) {
                int remainingHoodWeightToFill = (hoodWeight - presentWeights.get(idx));
                int intermediaryDecision = countMinPresents(remainingHoodWeightToFill, presentWeights,
                        memoizationArray);

                // The below step prevents overflow and minimises the decision value
                if (intermediaryDecision != Integer.MAX_VALUE)
                    decision = Math.min(decision, 1 + intermediaryDecision);
            }
        }

        // Memoizing value of current weight state
        memoizationArray[hoodWeight] = decision;
        return decision;
    }

    private void validateBeforeCalculation(Integer hoodWeight,
                                           List<Integer> presentWeights) throws CustomException {
        if (ObjectUtils.isEmpty(hoodWeight)) {
            throw new CustomException("Please provide a valid hood weight");
        }

        if (ObjectUtils.isEmpty(presentWeights)) {
            throw new CustomException("Please provide valid present weights");
        }
    }

}
