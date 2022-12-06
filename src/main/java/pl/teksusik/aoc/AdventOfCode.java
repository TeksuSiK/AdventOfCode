package pl.teksusik.aoc;

import pl.teksusik.aoc.challenge.*;

public class AdventOfCode {
    public static void main(String[] args) {
        ChallengeService challengeService = new ChallengeService();

        challengeService.addChallenge(new Day01(1, "01.txt"));
        challengeService.addChallenge(new Day02(2, "02.txt"));
        challengeService.addChallenge(new Day03(3, "03.txt"));
        challengeService.addChallenge(new Day04(4, "04.txt"));
        challengeService.addChallenge(new Day05(5, "05.txt"));
        challengeService.addChallenge(new Day06(6, "06.txt"));

        if (args.length == 0) {
            challengeService.runAllChallenges();
            return;
        }

        if (!isInteger(args[0])) {
            throw new IllegalArgumentException("Arguments must be integer");
        }

        int day = Integer.parseInt(args[0]);
        if (!challengeService.exists(day)) {
            throw new IllegalArgumentException("Provided challenge not exists");
        }

        challengeService.runChallenge(day);
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }
}
