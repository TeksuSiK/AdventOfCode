package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 extends Challenge {
    public Day03(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        int sumOfMultiplications = this.getLines().stream()
                .flatMap(line -> {
                    Matcher matcher = pattern.matcher(line);

                    return matcher.results().map(result -> {
                        int x = Integer.parseInt(result.group(1));
                        int y = Integer.parseInt(result.group(2));

                        return x * y;
                    });
                }).reduce(0, Integer::sum);

        return String.valueOf(sumOfMultiplications);
    }

    @Override
    public String solveSecondPart() {
        Pattern pattern = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\))");

        int sumOfEnabled = 0;
        boolean enabled = true;

        for (String line : this.getLines()) {
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                String operation = matcher.group(1);
                if (operation.contains("mul") && enabled) {
                    int x = Integer.parseInt(matcher.group(2));
                    int y = Integer.parseInt(matcher.group(3));
                    sumOfEnabled += (x * y);
                } else if ("do()".equals(operation)) {
                    enabled = true;
                } else if ("don't()".equals(operation)) {
                    enabled = false;
                }
            }
        }

        return String.valueOf(sumOfEnabled);
    }
}
