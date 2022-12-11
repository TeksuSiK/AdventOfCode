package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 extends Challenge {
    public Day11(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        List<Monkey> monkeys = this.produceMonkeys();
        this.executeRounds(monkeys, 20, true);

        long level = monkeys.stream()
                .map(Monkey::getInspectedItems)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce((a, b) -> a * b)
                .orElse(0L);

        return String.valueOf(level);
    }

    @Override
    public String solveSecondPart() {
        List<Monkey> monkeys = this.produceMonkeys();
        this.executeRounds(monkeys, 10000, false);

         long level = monkeys.stream()
                .map(Monkey::getInspectedItems)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce((a, b) -> a * b)
                .orElse(0L);

        return String.valueOf(level);
    }

    public List<Monkey> produceMonkeys() {
        return Arrays.stream(this.getInput().split("\n\n"))
                .map(Monkey::new)
                .collect(Collectors.toList());
    }

    public void executeRounds(List<Monkey> monkeys, int rounds, boolean changeWorryLevel) {
        int prod = 1;
        for (Monkey monkey : monkeys) {
            prod *= monkey.getDivisibleBy();
        }

        for (int round = 1; round <= rounds; round++) {
            for (Monkey monkey : monkeys) {
                for (long item : monkey.getItems()) {
                    monkey.setInspectedItems(monkey.getInspectedItems() + 1);

                    long operationParameter = monkey.getOperationParameter().equals("old") ? item : Long.parseLong(monkey.getOperationParameter());

                    switch (monkey.getOperationCharacter()) {
                        case '+' -> item = item + operationParameter;
                        case '*' -> item = item * operationParameter;
                    }

                    if (changeWorryLevel) {
                        item /= 3;
                    }

                    int target = item % monkey.getDivisibleBy() == 0 ? monkey.getTrueTarget() : monkey.getFalseTarget();
                    monkeys.get(target).getItems().add(item % prod);
                }

                monkey.getItems().clear();
            }
        }
    }

    public class Monkey {
        private final List<Long> items;
        private final char operationCharacter;
        private final String operationParameter;
        private final int divisibleBy;
        private final int trueTarget;
        private final int falseTarget;
        private long inspectedItems;

        public Monkey(String input) {
            String[] lines = input.split("\n");
            this.items = Arrays.stream(lines[1].split(": ")[1].split(","))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toCollection(ArrayList::new));
            this.operationCharacter = lines[2].split("new = old ")[1].charAt(0);
            this.operationParameter = lines[2].split("new = old ")[1].substring(2);
            this.divisibleBy = Integer.parseInt(lines[3].split("Test: divisible by ")[1]);
            this.trueTarget = Integer.parseInt(lines[4].split("throw to monkey ")[1]);
            this.falseTarget = Integer.parseInt(lines[5].split("throw to monkey ")[1]);
        }

        public List<Long> getItems() {
            return items;
        }

        public char getOperationCharacter() {
            return operationCharacter;
        }

        public String getOperationParameter() {
            return operationParameter;
        }

        public int getDivisibleBy() {
            return divisibleBy;
        }

        public int getTrueTarget() {
            return trueTarget;
        }

        public int getFalseTarget() {
            return falseTarget;
        }

        public long getInspectedItems() {
            return inspectedItems;
        }

        public void setInspectedItems(long inspectedItems) {
            this.inspectedItems = inspectedItems;
        }
    }
}
