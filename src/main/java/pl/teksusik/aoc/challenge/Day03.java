package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day03 extends Challenge {
    public Day03(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (int x = 0; x < this.getLines().get(0).length(); x++) {
            int zeros = 0;
            int ones = 0;
            for (String line : this.getLines()) {
                char character = line.charAt(x);

                if (character == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }

            if (zeros > ones) {
                gamma.append('1');
                epsilon.append('0');
            } else {
                gamma.append('0');
                epsilon.append('1');
            }
        }

        int gammaValue = Integer.parseInt(gamma.toString(), 2);
        int epsilonValue = Integer.parseInt(epsilon.toString(), 2);
        return String.valueOf(gammaValue * epsilonValue);
    }

    @Override
    public String solveSecondPart() {
        List<String> oxygenRating = new ArrayList<>(this.getLines());
        List<String> co2Rating = new ArrayList<>(this.getLines());

        solveSecondPart(oxygenRating, '1', '0');
        solveSecondPart(co2Rating, '0', '1');

        int oxygenRatingValue = Integer.parseInt(oxygenRating.get(0), 2);
        int co2RatingValue = Integer.parseInt(co2Rating.get(0), 2);
        return String.valueOf(oxygenRatingValue * co2RatingValue);
    }

    private void solveSecondPart(List<String> input, char firstBit, char secondBit) {
        for (int x = 0; x < this.getLines().get(0).length(); x++) {
            if (input.size() == 1) {
                break;
            }

            int zeros = 0;
            int ones = 0;
            for (String line : input) {
                char character = line.charAt(x);

                if (character == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }

            int finalX = x;
            if (zeros > ones) {
                input.removeIf(line -> line.charAt(finalX) == firstBit);
            } else {
                input.removeIf(line -> line.charAt(finalX) == secondBit);
            }
        }
    }
}
