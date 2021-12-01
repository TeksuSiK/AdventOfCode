package pl.teksusik.aoc;

public class AdventOfCode {
    public static void main(String[] args) {
        ChallengeService challengeService = new ChallengeService();

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
