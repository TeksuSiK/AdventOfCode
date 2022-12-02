package pl.teksusik.aoc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Challenge {
    private final int day;
    private final File input;

    public Challenge(int day, String input) {
        this.day = day;
        this.input = resolveFile(input);
    }

    public abstract String solveFirstPart();

    public abstract String solveSecondPart();

    public int getDay() {
        return day;
    }


    public String getInput() {
        String input = null;
        try {
            input = Files.readString(this.input.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }

    public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public File resolveFile(String input) {
        ClassLoader classLoader = AdventOfCode.class.getClassLoader();

        File file = new File(classLoader.getResource("./" + input).getFile());

        if (!file.exists() || !file.isFile()) {
            throw new IllegalStateException("File " + input + " does not exists");
        }

        return file;
    }
}
