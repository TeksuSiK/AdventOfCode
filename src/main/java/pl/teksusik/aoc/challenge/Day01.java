package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Day01 extends Challenge {
    public Day01(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        int largestSum = Arrays.stream(this.getInput().split("\n\n"))
                .mapToInt(group -> Arrays.stream(group.split("\n"))
                        .mapToInt(Integer::parseInt)
                        .sum())
                .max()
                .orElse(0);
        return String.valueOf(largestSum);
    }

    @Override
    public String solveSecondPart() {
        int sumOfTopThree = Arrays.stream(this.getInput().split("\n\n"))
                .mapToInt(group -> Arrays.stream(group.split("\n"))
                        .mapToInt(Integer::parseInt)
                        .sum())
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .limit(3)
                .sum();


        return String.valueOf(sumOfTopThree);
    }
}
