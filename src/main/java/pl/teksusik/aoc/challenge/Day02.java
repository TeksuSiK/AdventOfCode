package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 extends Challenge {
    public Day02(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        long safeCount = this.getLines().stream()
                .map(line -> Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .toList()
                ).filter(this::isSafe)
                .count();

        return String.valueOf(safeCount);
    }

    @Override
    public String solveSecondPart() {
        long safeCountAfterRemove = this.getLines().stream()
                .map(line -> Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .toList()
                ).filter(report -> this.isSafe(report) || isSafeAfterRemove(report))
                .count();

        return String.valueOf(safeCountAfterRemove);
    }

    private boolean isSafe(List<Integer> report) {
        Integer previous = null;
        Integer difference = null;

        for (Integer value : report) {
             if (previous == null) {
                previous = value;
                continue;
             }

            int currentDifference = previous - value;
            if (difference != null && difference * currentDifference < 0) {
                return false;
            }

            if (Math.abs(currentDifference) == 0 || Math.abs(currentDifference) >= 4) {
                return false;
            }

            difference = currentDifference;
            previous = value;
        }

        return true;
    }

    private boolean isSafeAfterRemove(List<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            List<Integer> reportCopy = new ArrayList<>(report);
            reportCopy.remove(i);

            if (this.isSafe(reportCopy)) {
                return true;
            }
        }

        return false;
    }
}
