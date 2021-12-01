package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

public class Day01 extends Challenge {
    public Day01(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        int increased = 0;
        for (int x = 1; x < this.getLines().size(); x++) {
            int xValue = Integer.parseInt(this.getLines().get(x));
            int previousValue = Integer.parseInt(this.getLines().get(x - 1));

            if (previousValue < xValue) {
                increased++;
            }
        }

        return String.valueOf(increased);
    }

    @Override
    public String solveSecondPart() {
        int increased = 0;
        int sum = Integer.parseInt(this.getLines().get(0)) + Integer.parseInt(this.getLines().get(1)) + Integer.parseInt(this.getLines().get(2));
        for (int x = 3; x < this.getLines().size(); x++) {
            int xValue = Integer.parseInt(this.getLines().get(x));
            int secondValue = Integer.parseInt(this.getLines().get(x - 1));
            int thirdValue = Integer.parseInt(this.getLines().get(x - 2));
            int newSum = xValue + secondValue + thirdValue;

            if (newSum > sum) {
                increased++;
            }

            sum = newSum;
        }

        return String.valueOf(increased);
    }
}
