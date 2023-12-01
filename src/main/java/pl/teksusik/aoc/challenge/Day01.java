package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.Map;

public class Day01 extends Challenge {
    public Day01(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        int sumOfCalibrationValues = this.getLines()
                .stream()
                .map(String::chars)
                .map(chars -> chars.filter(Character::isDigit).toArray())
                .map(number -> (char) number[0] + "" + (char) number[number.length - 1])
                .mapToInt(Integer::parseInt)
                .sum();
        return String.valueOf(sumOfCalibrationValues);
    }

    @Override
    public String solveSecondPart() {
        Map<String, String> spelling = Map.of(
                "one", "o1e",
                "two", "t2o",
                "three", "th3ee",
                "four", "f4ur",
                "five", "fi5e",
                "six", "s6x",
                "seven", "se7en",
                "eight", "ei8ht",
                "nine", "n9ne"
        );

        int sumOfCalibrationValues = this.getLines()
                .stream()
                .map(line -> {
                    for (Map.Entry<String, String> entry : spelling.entrySet()) {
                        line = line.replace(entry.getKey(), entry.getValue());
                    }

                    return line;
                })
                .map(String::chars)
                .map(chars -> chars.filter(Character::isDigit).toArray())
                .map(number -> (char) number[0] + "" + (char) number[number.length - 1])
                .mapToInt(Integer::parseInt)
                .sum();
        return String.valueOf(sumOfCalibrationValues);
    }
}
