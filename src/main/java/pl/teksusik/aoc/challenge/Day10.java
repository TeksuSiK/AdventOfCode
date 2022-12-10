package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Day10 extends Challenge {
    public Day10(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        int signalStrengthSum = this.performCommands(this::signalStrength)
                .mapToInt(e -> e)
                .sum();

        return String.valueOf(signalStrengthSum);
    }

    @Override
    public String solveSecondPart() {
        return this.performCommands(this::drawOutput).collect(Collectors.joining());
    }

    public int signalStrength(int x, int cycle) {
        return (cycle + 20) % 40 == 0 ? x * cycle : 0;
    }

    public boolean isSpriteVisible(int x, int cycle) {
        return Math.abs(x - cycle % 40 + 1) <= 1;
    }

    public String drawOutput(int x, int cycle) {
        return (isSpriteVisible(x, cycle) ? "#" : ".") + (cycle % 40 == 0 ? "\n" : "");
    }

    public <T> Stream<T> performCommands(BiFunction<Integer, Integer, T> function) {
        int x = 1;
        int cycle = 0;
        List<T> state = new ArrayList<>();
        for (String command : this.getLines()) {
            String[] operation = command.split(" ");

            cycle++;
            state.add(function.apply(x, cycle));
            if (operation[0].equals("addx")) {
                cycle++;
                state.add(function.apply(x, cycle));
                x += parseInt(operation[1]);
            }
        }

        return state.stream();
    }
}
