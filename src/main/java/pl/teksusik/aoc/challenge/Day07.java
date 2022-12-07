package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Day07 extends Challenge {
    public Day07(int day, String input) {
        super(day, input);
    }

    private final Directory root = Directory.of("/", null);
    private Directory current = null;

    {
        for (String command : this.getLines()) {
            if (command.charAt(0) == '$') {
                if (command.equals("$ cd /")) {
                    this.current = this.root;
                } else if (command.equals("$ cd ..")) {
                    this.current = this.current.parent();
                } else if (command.startsWith("$ cd ")) {
                    this.current = this.current.getChildren(command.substring(5));
                }
                continue;
            }

            this.current.children().add(Node.of(command, current));
        }
    }

    @Override
    public String solveFirstPart() {
        long sum = root.getSubDirectoriesRecursively()
                .mapToLong(Directory::size)
                .filter(size -> size <= 100000L)
                .sum();

        return String.valueOf(sum);
    }

    @Override
    public String solveSecondPart() {
        final long FILESYSTEM_SIZE = 70000000L;
        final long SIZE_NEEDED_FOR_UPDATE = 30000000L;

        long freeSpace = FILESYSTEM_SIZE - this.root.size();
        long spaceNeeded = SIZE_NEEDED_FOR_UPDATE - freeSpace;

        long sizeToDelete = this.root.getSubDirectoriesRecursively()
                .mapToLong(Directory::size)
                .filter(size -> size >= spaceNeeded)
                .min()
                .orElse(0);

        return String.valueOf(sizeToDelete);
    }

    public interface Node {
        String name();
        long size();
        boolean isDirectory();

        static Node of(String input, Directory parent) {
            if (input.startsWith("dir")) {
                return Directory.of(input.substring(4), parent);
            }

            String[] file = input.split(" ");
            return new File(file[1], Long.parseLong(file[0]));
        }
    }

    public record Directory(String name, Directory parent, List<Node> children) implements Node {
        public static Directory of(String input, Directory parent) {
            return new Directory(input, parent, new LinkedList<>());
        }

        @Override
        public long size() {
            return this.children.stream().mapToLong(Node::size).sum();
        }

        @Override
        public boolean isDirectory() {
            return true;
        }

        public Directory getChildren(String name) {
            return this.getSubDirectories()
                    .filter(directory -> directory.name().equals(name))
                    .findFirst()
                    .orElse(null);
        }

        public Stream<Directory> getSubDirectories() {
            return children.stream().filter(Node::isDirectory).map(directory -> (Directory) directory);
        }

        public Stream<Directory> getSubDirectoriesRecursively() {
            return Stream.concat(this.getSubDirectories(), this.getSubDirectories().flatMap(Directory::getSubDirectoriesRecursively));
        }
    }

    public record File(String name, long size) implements Node {
        @Override
        public boolean isDirectory() {
            return false;
        }
    }
}
