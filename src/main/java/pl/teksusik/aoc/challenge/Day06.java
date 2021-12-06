package pl.teksusik.aoc.challenge;

import org.apache.commons.lang3.ArrayUtils;
import pl.teksusik.aoc.Challenge;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Day06 extends Challenge {
    public Day06(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        String input = this.getLines().get(0);
        long[] fishPopulationByDay = new long[9];

        return this.solve(input, fishPopulationByDay, 80);
    }

    @Override
    public String solveSecondPart() {
        String input = this.getLines().get(0);
        long[] fishPopulationByDay = new long[9];

        return this.solve(input, fishPopulationByDay, 256);
    }

    public String solve(String input, long[] fishPopulationByDay, int days) {
        Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .forEach(fish -> fishPopulationByDay[fish]++);

        IntStream.range(0, days).forEach(day -> {
            ArrayUtils.shift(fishPopulationByDay, -1);
            fishPopulationByDay[6] += fishPopulationByDay[8];
        });

        return String.valueOf(Arrays.stream(fishPopulationByDay).sum());
    }
}
