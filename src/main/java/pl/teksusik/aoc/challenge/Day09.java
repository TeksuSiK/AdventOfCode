package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.*;
import java.util.stream.IntStream;

public class Day09 extends Challenge {
    public Day09(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        int visitedPositions = this.countVisitedPositions(2);

        return String.valueOf(visitedPositions);
    }

    @Override
    public String solveSecondPart() {
        int visitedPositions = this.countVisitedPositions(10);

        return String.valueOf(visitedPositions);
    }

    public int countVisitedPositions(int knotCount) {
        List<Move> moves = this.getLines()
                .stream()
                .map(line -> line.split(" "))
                .flatMap(move -> Collections.nCopies(Integer.parseInt(move[1]), Move.fromInput(move[0].charAt(0)))
                        .stream())
                .toList();
        LinkedList<Knot> knots = new LinkedList<>(IntStream.range(0, knotCount)
                .mapToObj(i -> new Knot(0, 0))
                .toList());
        Set<Knot> visited = new HashSet<>();

        for (Move move : moves) {
            Knot head = knots.get(0);
            knots.set(0, switch (move) {
                case UP -> head.up();
                case DOWN -> head.down();
                case LEFT -> head.left();
                case RIGHT -> head.right();
            });

            IntStream.range(1, knots.size()).forEach(i -> {
                Knot last = knots.get(i - 1);
                Knot current = knots.get(i);
                if (!current.isNextTo(last)) {
                    knots.set(i, current.moveTo(last));
                }
            });

            visited.add(knots.getLast());
        }

        return visited.size();
    }

    public enum Move {
        UP('U'), DOWN('D'), LEFT('L'), RIGHT('R');

        private final char character;

        Move(char character) {
            this.character = character;
        }

        public static Move fromInput(char character) {
            return Arrays.stream(Move.values())
                    .filter(move -> move.character == character)
                    .findFirst()
                    .orElse(null);
        }
    }

    public record Knot(int x, int y) {
        public Knot moveTo(Knot other) {
            int diffX = this.x - other.x;
            int diffY = this.y - other.y;

            return new Knot(this.x - Integer.signum(diffX), this.y - Integer.signum(diffY));
        }

        public boolean isNextTo(Knot other) {
            return Math.abs(this.x - other.x) <= 1 && Math.abs(this.y - other.y) <= 1;
        }

        public Knot up() {
            return new Knot(x, y + 1);
        }

        public Knot down() {
            return new Knot(x, y - 1);
        }

        public Knot left() {
            return new Knot(x - 1, y);
        }

        public Knot right() {
            return new Knot(x + 1, y);
        }
    }
}
