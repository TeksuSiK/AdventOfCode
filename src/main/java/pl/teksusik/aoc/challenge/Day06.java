package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.stream.IntStream;

public class Day06 extends Challenge {
    public Day06(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        return String.valueOf(findFirstMarker(4));
    }

    @Override
    public String solveSecondPart() {
        return String.valueOf(findFirstMarker(14));
    }

    private int findFirstMarker(int size) {
        return IntStream.range(size, this.getInput().length())
                .filter(i -> this.getInput().substring(i - size, i).chars().distinct().count() == size)
                .findFirst()
                .orElse(0);
    }
}
