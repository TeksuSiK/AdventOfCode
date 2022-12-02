package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day02 extends Challenge {
    public Day02(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        List<Long> scores = new ArrayList<>();
        this.getLines().forEach(line -> {
            String[] moves = line.split(" ");
            String opponent = moves[0];
            String selection = moves[1];

            Game game = new Game(opponent);
            game.setSelection(Game.getShape(selection));
            scores.add(game.getScore());
        });

        long sum = scores.stream().mapToLong(Long::longValue).sum();
        return String.valueOf(sum);
    }

    @Override
    public String solveSecondPart() {
        List<Long> scores = new ArrayList<>();
        this.getLines().forEach(line -> {
            String[] moves = line.split(" ");
            String opponent = moves[0];
            String result = moves[1];

            Game game = new Game(opponent);
            game.setResult(result);
            game.chooseShape();
            scores.add(game.getScore());
        });

        long sum = scores.stream().mapToLong(Long::longValue).sum();
        return String.valueOf(sum);
    }

    public enum Shape {
        ROCK, PAPER, SCISSORS;
    }

    public enum Result {
        LOST, DRAW, WON
    }

    public class Game {
        private final Shape opponent;
        private Shape selection;
        private Result result;

        public Game(String opponent) {
            this.opponent = getShape(opponent);
        }

        public static Shape getShape(String input) {
            return switch (input) {
                case "A", "X" -> Shape.ROCK;
                case "B", "Y" -> Shape.PAPER;
                case "C", "Z" -> Shape.SCISSORS;
                default -> throw new UnsupportedOperationException("No such shape for input: " + input);
            };
        }

        public Result getResult() {
            if (this.selection == this.opponent) {
                return Result.DRAW;
            } else if (this.opponent.ordinal() == (this.selection.ordinal() + 1) % Shape.values().length) {
                return Result.LOST;
            } else {
                return Result.WON;
            }
        }

        public long getScore() {
            int baseScore = this.selection.ordinal() + 1;
            return switch (this.getResult()) {
                case LOST -> baseScore;
                case DRAW -> baseScore + 3;
                case WON -> baseScore + 6;
            };
        }

        public void chooseShape() {
            this.selection = Shape.values()[(opponent.ordinal() + result.ordinal() + 2) % Shape.values().length];
        }

        public void setSelection(Shape selection) {
            this.selection = selection;
        }

        public void setResult(String result) {
            if (result.equals("X")) {
                this.result = Result.LOST;
            } else if (result.equals("Y")) {
                this.result = Result.DRAW;
            } else {
                this.result = Result.WON;
            }
        }
    }
}
