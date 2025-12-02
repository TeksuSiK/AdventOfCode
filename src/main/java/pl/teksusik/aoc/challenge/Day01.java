package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

public class Day01 extends Challenge {
    public Day01(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        int zeroesCounter = 0;
        int currentPosition = 50;

        for (String line : this.getLines()) {
            char direction = line.charAt(0);
            int amount = Integer.parseInt(line.substring(1));

            int delta = (direction == 'R') ? amount : -amount;

            currentPosition = Math.floorMod(currentPosition + delta, 100);
            if (currentPosition == 0) {
                zeroesCounter++;
            }
        }

        return String.valueOf(zeroesCounter);
    }

    @Override
    public String solveSecondPart() {
        int zeroesCounter = 0;
        int currentPosition = 50;

        for (String line : this.getLines()) {
            char direction = line.charAt(0);
            int amount = Integer.parseInt(line.substring(1));

            zeroesCounter += amount / 100;

            int step = (direction == 'R') ? 1 : -1;
            int remaining = amount % 100;

            for (int i = 0; i < remaining; i++) {
                currentPosition = Math.floorMod(currentPosition + step, 100);
                if (currentPosition == 0) {
                    zeroesCounter++;
                }
            }
        }

        return String.valueOf(zeroesCounter);
    }
}
