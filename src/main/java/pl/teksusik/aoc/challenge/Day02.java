package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

public class Day02 extends Challenge {
    public Day02(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        int depth = 0;
        int horizontalPosition = 0;
        for (int x = 0; x < this.getLines().size(); x++) {
            String[] line = this.getLines().get(x).split(" ");
            int value = Integer.parseInt(line[1]);

            switch (line[0]) {
                case "forward" -> horizontalPosition += value;
                case "down" -> depth += value;
                case "up" -> depth -= value;
            }
        }

        return String.valueOf(depth * horizontalPosition);
    }

    @Override
    public String solveSecondPart() {
        int depth = 0;
        int horizontalPosition = 0;
        int aim = 0;
        for (int x = 0; x < this.getLines().size(); x++) {
            String[] line = this.getLines().get(x).split(" ");
            int value = Integer.parseInt(line[1]);

            switch (line[0]) {
                case "forward" -> {
                    horizontalPosition += value;
                    depth += (aim * value);
                }
                case "down" -> aim += value;
                case "up" -> aim -= value;
            }
        }

        return String.valueOf(depth * horizontalPosition);
    }
}
