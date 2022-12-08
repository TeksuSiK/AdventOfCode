package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Day08 extends Challenge {
    public Day08(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        long visibleTrees = Grid.of(this.getInput())
                .getAllFields()
                .filter(this::isVisible)
                .count();

        return String.valueOf(visibleTrees);
    }

    @Override
    public String solveSecondPart() {
        long highestScenicScore = Grid.of(this.getInput())
                .getAllFields()
                .filter(this::isVisible)
                .mapToLong(this::scenicScore)
                .max()
                .orElse(0);

        return String.valueOf(highestScenicScore);
    }

    private boolean isVisible(Grid.Field tree, int maxHeight, Function<Grid.Field, Optional<Grid.Field>> direction) {
        return direction.apply(tree)
                .map(next -> next.getValue() < maxHeight && isVisible(next, maxHeight, direction))
                .orElse(true);
    }

    private boolean isVisible(Grid.Field tree) {
        return isVisible(tree, tree.getValue(), Grid.Field::fromLeft)
                || isVisible(tree, tree.getValue(), Grid.Field::fromTop)
                || isVisible(tree, tree.getValue(), Grid.Field::fromRight)
                || isVisible(tree, tree.getValue(), Grid.Field::fromBottom);
    }

    private int visibleTrees(Grid.Field tree, int maxHeight, Function<Grid.Field, Optional<Grid.Field>> direction) {
        return direction.apply(tree).map(next -> {
            if (next.getValue() < maxHeight) {
                return 1 + visibleTrees(next, maxHeight, direction);
            } else {
                return 1;
            }
        }).orElse(0);
    }

    private int scenicScore(Grid.Field tree) {
        return visibleTrees(tree, tree.getValue(), Grid.Field::fromLeft)
                * visibleTrees(tree, tree.getValue(), Grid.Field::fromTop)
                * visibleTrees(tree, tree.getValue(), Grid.Field::fromRight)
                * visibleTrees(tree, tree.getValue(), Grid.Field::fromBottom);
    }

    public record Grid(List<List<Integer>> fields) {
        public static Grid of(String input) {
            return new Grid(new LinkedList<>(Arrays.stream(input.split("\n"))
                    .map(line -> Arrays.stream(line.split(""))
                            .map(Integer::parseInt)
                    )
                    .map(s -> new LinkedList<>(s.toList())).toList()
            ));
        }
        
        public Stream<Field> getAllFields() {
            List<Field> fields = new LinkedList<>();
            for (int x = 0; x < this.fields.size(); x++) {
                for (int y = 0; y < this.fields.size(); y++) {
                    fields.add(new Field(x, y));
                }
            }
            return fields.stream();
        }

        public class Field {
            private final int x;
            private final int y;

            public Field(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getValue() {
                return fields.get(x).get(y);
            }

            public Optional<Field> fromLeft() {
                return x - 1 >= 0 ? Optional.of(new Field(x - 1, y)) : Optional.empty();
            }

            public Optional<Field> fromTop() {
                return y - 1 >= 0 ? Optional.of(new Field(x, y - 1)) : Optional.empty();
            }

            public Optional<Field> fromRight() {
                return x + 1 < fields.size() ? Optional.of(new Field(x + 1, y)) : Optional.empty();
            }

            public Optional<Field> fromBottom() {
                return y + 1 < fields.size() ? Optional.of(new Field(x, y + 1)) : Optional.empty();
            }
        }
    }
}
