package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day05 extends Challenge {
    public Day05(int day, String input) {
        super(day, input);
    }

    private final List<Move> moves = Arrays.stream(this.getInput()
                    .split("\n\n")[1]
                    .split("\n"))
            .map(Move::parse)
            .toList();

    @Override
    public String solveFirstPart() {
        Stack<Character>[] stacks = this.parseStacks(this.getInput().split("\n\n")[0]);
        moves.forEach(move -> IntStream.range(0, move.count)
                .forEach(i -> stacks[move.to].push(stacks[move.from].pop())));

        return buildMessage(stacks);
    }

    @Override
    public String solveSecondPart() {
        Stack<Character>[] stacks = this.parseStacks(this.getInput().split("\n\n")[0]);
        moves.forEach(move -> {
            Character[] buffer = new Character[move.count];
            IntStream.range(0, move.count)
                    .forEach(i -> buffer[move.count - i - 1] = stacks[move.from].pop());
            IntStream.range(0, move.count)
                    .forEach(i -> stacks[move.to].push(buffer[i]));
        });

        return buildMessage(stacks);
    }

    public Stack<Character>[] parseStacks(String input) {
        Stack[] stacks = new Stack[9];
        String[] lines = input.split("\n");
        for (int i = lines.length - 1; i >= 0; i--) {
            String line = lines[i];
            IntStream.iterate(1, j -> j < line.length(), j -> j + 4)
                    .forEach(j -> {
                        char character = line.charAt(j);
                        if (character != ' ') {
                            if (stacks[j / 4] == null) {
                                stacks[j / 4] = new Stack<>();
                            }
                            stacks[j / 4].push(character);
                        }
                    });
        }

        return stacks;
    }

    public String buildMessage(Stack<Character>[] stacks) {
        return Arrays.stream(stacks)
                .map(stack -> String.valueOf(stack.peek()))
                .collect(Collectors.joining());
    }

    public record Move(int count, int from, int to){
        private static final Pattern PATTERN = Pattern.compile("move ([0-9]+) from ([0-9]+) to ([0-9]+)");

        public static Move parse(String input) {
            Matcher matcher = PATTERN.matcher(input);
            if (matcher.find()) {
                int count = Integer.parseInt(matcher.group(1));
                int from = Integer.parseInt(matcher.group(2)) - 1;
                int to = Integer.parseInt(matcher.group(3)) - 1;

                return new Move(count, from, to);
            }
            throw new IllegalArgumentException("Invalid move: " + input);
        }
    }
}
