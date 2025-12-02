package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.Arrays;
import java.util.List;

public class Day02 extends Challenge {
    public Day02(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        List<String> ranges = Arrays.stream(this.getInput().split(",")).toList();

        long invalidIdsSum = 0;

        for (String range : ranges) {
            String[] values = range.split("-");
            long lower = Long.parseLong(values[0]);
            long upper = Long.parseLong(values[1]);

            for (long i = lower; i <= upper; i++) {
                String number = Long.toString(i);
                if (number.length() % 2 != 0) {
                    continue;
                }

                int midPosition = number.length() / 2;
                String first = number.substring(0, midPosition);
                String second = number.substring(midPosition);

                if (first.equals(second)) {
                    invalidIdsSum += i;
                }
            }
        }

        return String.valueOf(invalidIdsSum);
    }

    @Override
    public String solveSecondPart() {
        List<String> ranges = Arrays.stream(this.getInput().split(",")).toList();

        long invalidIdsSum = 0;

        for (String range : ranges) {
            String[] values = range.split("-");
            long lower = Long.parseLong(values[0]);
            long upper = Long.parseLong(values[1]);

            for (long i = lower; i <= upper; i++) {
                String number = Long.toString(i);
                int length = number.length();

                for (int substringLength = 1; substringLength <= length / 2; substringLength++) {
                    if (length % substringLength != 0) {
                        continue;
                    }

                    String substring = number.substring(0, substringLength);

                    int times = length / substringLength;

                    StringBuilder repeated = new StringBuilder();
                    repeated.append(substring.repeat(times));

                    if (repeated.toString().equals(number)) {
                        invalidIdsSum += i;
                        break;
                    }
                }
            }
        }

        return String.valueOf(invalidIdsSum);
    }
}
