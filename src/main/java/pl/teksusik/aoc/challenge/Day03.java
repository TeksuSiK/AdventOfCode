package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Day03 extends Challenge {
    public Day03(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        long sum = 0;

        for (String line : this.getLines()) {
            int maxVoltage = 0;
            int n = line.length();

            for (int i = 0; i < n - 1; i++) {
                int tens = Character.getNumericValue(line.charAt(i));

                for (int j = i + 1; j < n; j++) {
                    int ones = Character.getNumericValue(line.charAt(j));

                    int voltage = tens * 10 + ones;
                    if (voltage > maxVoltage) {
                        maxVoltage = voltage;
                    }
                }
            }

            sum += maxVoltage;
        }

        return String.valueOf(sum);
    }

    @Override
    public String solveSecondPart() {
        long sum = 0;

        for (String line : this.getLines()) {
            Deque<Character> stack = new ArrayDeque<>();
            int n = line.length();
            int toRemove = n - 12;

            for (char c : line.toCharArray()) {
                while (!stack.isEmpty() && toRemove > 0 && stack.peekLast() < c) {
                    stack.pollLast();
                    toRemove--;
                }

                stack.addLast(c);
            }

            while (stack.size() > 12) {
                stack.pollLast();
            }

            StringBuilder sb = new StringBuilder();
            for (char c : stack) {
                sb.append(c);
            }

            sum += Long.parseLong(sb.toString());
        }

        return String.valueOf(sum);
    }
}
