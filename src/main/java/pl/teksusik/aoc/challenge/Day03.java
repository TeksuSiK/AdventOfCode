package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.List;
import java.util.stream.IntStream;

public class Day03 extends Challenge {
    public Day03(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        int sum = this.getLines()
                .stream()
                .map(line -> new String[]{ line.substring(0, line.length() / 2), line.substring(line.length() / 2) })
                .mapToInt(parts -> getPriorities(parts[0])
                        .filter(firstPart -> getPriorities(parts[1])
                                .anyMatch(secondPart -> secondPart == firstPart))
                        .findFirst()
                        .orElse(0))
                .sum();

        return String.valueOf(sum);
    }

    @Override
    public String solveSecondPart() {
        List<String> lines = this.getLines();
        int sum = IntStream.range(0, lines.size() / 3)
                .map(line -> line * 3)
                .map(line -> getPriorities(lines.get(line))
                        .filter(first ->
                                getPriorities(lines.get(line + 1)).anyMatch(second -> second == first) &&
                                getPriorities(lines.get(line + 2)).anyMatch(third -> third == first))
                        .findFirst()
                        .orElse(0))
                .sum();

        return String.valueOf(sum);
    }

    private IntStream getPriorities(String input) {
        return input.chars()
                .map(character -> character >= 'a' && character <= 'z' ? character - 'a' + 1 : character - 'A' + 27);
    }
}
