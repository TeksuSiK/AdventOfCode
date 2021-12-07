package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.Arrays;

public class Day07 extends Challenge {
    public Day07(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        Integer[] crabs = Arrays.stream(this.getLines().get(0).split(","))
                .map(Integer::parseInt)
                .sorted()
                .toArray(Integer[]::new);
        int median = crabs[crabs.length / 2];

        return String.valueOf(Arrays.stream(crabs)
                .reduce(0, (a, b) -> a + Math.abs(median - b)));
    }

    @Override
    public String solveSecondPart() {
        Integer[] crabs = Arrays.stream(this.getLines().get(0).split(","))
                .map(Integer::parseInt)
                .sorted()
                .toArray(Integer[]::new);
        int mean = Arrays.stream(crabs).mapToInt(Integer::intValue).sum() / crabs.length;

        return String.valueOf(Arrays.stream(crabs)
                .reduce(0, (a, b) -> a + (Math.abs(mean - b) * (Math.abs(mean - b) + 1)) / 2));
    }
}
