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
            switch (line[0]) {
                case "forward" -> horizontalPosition = horizontalPosition + Integer.parseInt(line[1]);
                case "down" -> depth = depth + Integer.parseInt(line[1]);
                case "up" -> depth = depth - Integer.parseInt(line[1]);
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
            switch (line[0]) {
                case "forward" -> {
                    horizontalPosition = horizontalPosition + Integer.parseInt(line[1]);
                    depth = depth + (aim * Integer.parseInt(line[1]));
                }
                case "down" -> aim = aim + Integer.parseInt(line[1]);
                case "up" -> aim = aim - Integer.parseInt(line[1]);
            }
        }

        return String.valueOf(depth * horizontalPosition);
    }
}
