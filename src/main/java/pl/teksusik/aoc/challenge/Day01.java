package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day01 extends Challenge {
    public Day01(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        List<String[]> parts = this.getLines().stream()
                .map(line -> line.split("   "))
                .toList();

        List<Integer> left = parts.stream()
                .map(part -> Integer.parseInt(part[0]))
                .sorted(Collections.reverseOrder())
                .toList();

        List<Integer> right = parts.stream()
                .map(part -> Integer.parseInt(part[1]))
                .sorted(Collections.reverseOrder())
                .toList();

        int sumOfDistances = IntStream.range(0, left.size())
                .map(i -> Math.abs(left.get(i) - right.get(i)))
                .sum();

        return String.valueOf(sumOfDistances);
    }

    @Override
    public String solveSecondPart() {
        List<String[]> parts = this.getLines().stream()
                .map(line -> line.split("   "))
                .toList();

        List<Integer> left = parts.stream()
                .map(part -> Integer.parseInt(part[0]))
                .sorted(Collections.reverseOrder())
                .toList();

        List<Integer> right = parts.stream()
                .map(part -> Integer.parseInt(part[1]))
                .sorted(Collections.reverseOrder())
                .toList();

        Map<Integer, Long> rightFrequency = right.stream()
                .collect(Collectors.groupingBy(value -> value, Collectors.counting()));

        long sumOfSimilaritiesScore = left.stream()
                .mapToLong(value -> value * rightFrequency.getOrDefault(value, 0L))
                .sum();

        return String.valueOf(sumOfSimilaritiesScore);
    }
}
