package pl.teksusik.aoc;

import java.util.ArrayList;
import java.util.List;

public class ChallengeService {
    private final List<Challenge> challengeList;

    public ChallengeService() {
        this.challengeList = new ArrayList<>();
    }

    public void addChallenge(Challenge challenge) {
        this.challengeList.add(challenge);
    }

    public boolean exists(int day) {
        return this.challengeList.stream()
                .anyMatch(challenge -> challenge.getDay() == day);
    }

    private void runChallenge(Challenge challenge) {
        System.out.println("Challenge day: " + challenge.getDay());
        System.out.println("    Part 1: " + challenge.solveFirstPart());
        System.out.println("    Part 2: " + challenge.solveSecondPart());
        System.out.println(" ");
    }

    public void runChallenge(int day) {
        this.challengeList.stream()
                .filter(challenge -> challenge.getDay() == day)
                .forEach(this::runChallenge);
    }

    public void runAllChallenges() {
        this.challengeList.forEach(this::runChallenge);
    }
}
