package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Day05 extends Challenge {
    public Day05(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        return String.valueOf(this.getLines().stream()
                .map(Line::of)
                .filter(Line::isStraightLine)
                .flatMap(line -> line.getCoords().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .filter(value -> value > 1)
                .count());
    }

    @Override
    public String solveSecondPart() {
        return String.valueOf(this.getLines().stream()
                .map(Line::of)
                .flatMap(line -> line.getCoords().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .filter(value -> value > 1)
                .count());
    }

    public record Line(int x1, int y1, int x2, int y2) {
        private static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");

        public static Line of(String input) {
            Matcher matcher = PATTERN.matcher(input);
            if (matcher.find()) {
                int x1 = Integer.parseInt(matcher.group(1));
                int y1 = Integer.parseInt(matcher.group(2));
                int x2 = Integer.parseInt(matcher.group(3));
                int y2 = Integer.parseInt(matcher.group(4));
                return new Line(x1, y1, x2, y2);
            }
            throw new IllegalStateException();
        }

        public boolean isStraightLine() {
            return x1 == x2 || y1 == y2;
        }

        public Set<Vector2D> getCoords() {
            HashSet<Vector2D> coords = new HashSet<>();
            int signumX = Integer.signum(x2 - x1);
            int signumY = Integer.signum(y2 - y1);
            int x = x1;
            int y = y1;
            while (x != x2 + signumX || y != y2 + signumY) {
                coords.add(new Vector2D(x, y));
                x += signumX;
                y += signumY;
            }
            return coords;
        }
    }

    public record Vector2D(int x, int y) {
    }
}
