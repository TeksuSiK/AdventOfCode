package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02 extends Challenge {
    private static final int RED = 12;
    private static final int GREEN = 13;
    private static final int BLUE = 14;

    private static final Pattern GAME_PATTERN = Pattern.compile("Game (\\d+): (.*)");
    private static final Pattern RED_PATTERN = Pattern.compile("(\\d+) red");
    private static final Pattern GREEN_PATTERN = Pattern.compile("(\\d+) green");
    private static final Pattern BLUE_PATTERN = Pattern.compile("(\\d+) blue");

    public Day02(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        int sumOfPossibleGamesIds = this.getLines().stream()
                .map(this::parseGame)
                .filter(Game::isPossible)
                .mapToInt(Game::getGameId)
                .sum();
        return String.valueOf(sumOfPossibleGamesIds);
    }

    @Override
    public String solveSecondPart() {
        int powerOfGames = this.getLines().stream()
                .map(this::parseGame)
                .mapToInt(Game::power)
                .sum();
        return String.valueOf(powerOfGames);
    }

    private Game parseGame(String line) {
        Matcher gameMatcher = GAME_PATTERN.matcher(line);
        gameMatcher.find();
        int gameId = Integer.parseInt(gameMatcher.group(1));
        Game game = new Game(gameId);

        for (String play : gameMatcher.group(2).split("; ")) {
            game.addRound(new Round(play));
        }

        return game;
    }

    public class Game {
        private final int gameId;
        private final List<Round> rounds = new ArrayList<>();

        public Game(int gameId) {
            this.gameId = gameId;
        }

        public void addRound(Round round) {
            rounds.add(round);
        }

        public boolean isPossible() {
            for (Round round : rounds) {
                if (!round.isPossible()) {
                    return false;
                }
            }

            return true;
        }

        public int power() {
            int red = 0;
            int green = 0;
            int blue = 0;
            for (Round round : rounds) {
                red = Math.max(red, round.getRed());
                green = Math.max(green, round.getGreen());
                blue = Math.max(blue, round.getBlue());
            }

            return red * green * blue;
        }

        public int getGameId() {
            return gameId;
        }
    }

    public class Round {
        private int red;
        private int green;
        private int blue;

        public Round(String input) {
            String[] parts = input.split(", ");
            for (String part : parts) {
                Matcher redMatcher = RED_PATTERN.matcher(part);
                if (redMatcher.matches()) {
                    red = Integer.parseInt(redMatcher.group(1));
                }

                Matcher blueMatcher = BLUE_PATTERN.matcher(part);
                if (blueMatcher.matches()) {
                    blue = Integer.parseInt(blueMatcher.group(1));
                }

                Matcher greenMatcher = GREEN_PATTERN.matcher(part);
                if (greenMatcher.matches()) {
                    green = Integer.parseInt(greenMatcher.group(1));
                }
            }
        }

        public boolean isPossible() {
            return RED >= this.red && GREEN >= this.green && BLUE >= this.blue;
        }

        public int getRed() {
            return red;
        }

        public int getGreen() {
            return green;
        }

        public int getBlue() {
            return blue;
        }
    }
}
