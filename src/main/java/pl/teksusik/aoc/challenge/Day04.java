package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import static java.lang.Integer.*;

public class Day04 extends Challenge {
    public Day04(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        long count = this.getLines()
                .stream()
                .map(line ->  line.split(","))
                .map(range -> new Pair<>(range[0].split("-"), range[1].split("-")))
                .filter(assignments -> (
                        parseInt(assignments.left()[0]) >= parseInt(assignments.right()[0]) && parseInt(assignments.left()[1]) <= parseInt(assignments.right()[1])
                ) || (
                        parseInt(assignments.left()[0]) <= parseInt(assignments.right()[0]) && parseInt(assignments.left()[1]) >= parseInt(assignments.right()[1])
                ))
                .count();

        return String.valueOf(count);
    }

    @Override
    public String solveSecondPart() {
        long count = this.getLines()
                .stream()
                .map(line ->  line.split(","))
                .map(range -> new Pair<>(range[0].split("-"), range[1].split("-")))
                .filter(assignments -> (
                        (parseInt(assignments.left()[0]) <= parseInt(assignments.right()[1])) &&
                        (parseInt(assignments.left()[1]) >= parseInt(assignments.right()[0]))
                ))
                .count();

        return String.valueOf(count);
    }

    public record Pair<T>(T left, T right) {
    }
}
