package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.ArrayList;
import java.util.Stack;

public class Day10 extends Challenge {
    public Day10(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        int score = 0;
        for (String line : this.getLines()) {
            Stack<Character> stack = new Stack<>();
            for (char c : line.toCharArray()) {
                if (c == '(' || c == '[' || c == '{' || c == '<') {
                    stack.push(c);
                } else if (stack.isEmpty()) {
                    break;
                } else if (c == ')' && stack.peek() == '('
                        || c == ']' && stack.peek() == '['
                        || c == '}' && stack.peek() == '{'
                        || c == '>' && stack.peek() == '<') {
                    stack.pop();
                } else {
                    switch (c) {
                        case ')' -> score += 3;
                        case ']' -> score += 57;
                        case '}' -> score += 1197;
                        case '>' -> score += 25137;
                    }
                    break;
                }
            }
        }
        return String.valueOf(score);
    }

    @Override
    public String solveSecondPart() {
        ArrayList<Long> score = new ArrayList<>();
        for (String line : this.getLines()) {
            boolean corrupted = false;
            Stack<Character> stack = new Stack<>();
            for (char c : line.toCharArray()) {
                if (c == '(' || c == '[' || c == '{' || c == '<') {
                    stack.push(c);
                } else if (stack.isEmpty()) {
                    break;
                } else if (c == ')' && stack.peek() == '('
                        || c == ']' && stack.peek() == '['
                        || c == '}' && stack.peek() == '{'
                        || c == '>' && stack.peek() == '<') {
                    stack.pop();
                } else {
                    corrupted = true;
                    break;
                }
            }

            if (!corrupted) {
                long points = 0;
                while (!stack.isEmpty()) {
                    points *= 5;
                    switch (stack.pop()) {
                        case '(' -> points += 1;
                        case '[' -> points += 2;
                        case '{' -> points += 3;
                        case '<' -> points += 4;
                    }
                }
                score.add(points);
            }
        }
        score.sort(Long::compareTo);
        return String.valueOf(score.get(score.size() / 2));
    }
}
