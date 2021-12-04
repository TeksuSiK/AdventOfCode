package pl.teksusik.aoc.challenge;

import org.apache.commons.lang3.StringUtils;
import pl.teksusik.aoc.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day04 extends Challenge {
    public Day04(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        int[] numbers = Arrays.stream(this.getLines().get(0).split(",")).mapToInt(Integer::parseInt).toArray();

        BingoGame bingoGame = new BingoGame();
        Optional<Board> winner = bingoGame.play(this.getLines(), numbers);

        return winner.map(Board::getFinalScore).map(String::valueOf).orElse("0");
    }

    @Override
    public String solveSecondPart() {
        int[] numbers = Arrays.stream(this.getLines().get(0).split(",")).mapToInt(Integer::parseInt).toArray();

        BingoGame bingoGame = new LastWinnerBingoGame();
        Optional<Board> winner = bingoGame.play(this.getLines(), numbers);

        return winner.map(Board::getFinalScore).map(String::valueOf).orElse("0");
    }

    public class BingoGame {
        protected final List<Board> boards = new ArrayList<>();

        public Optional<Board> play(List<String> input, int[] numbers) {
            createBoards(input);

            for (int number : numbers) {
                Optional<Board> winner = this.drawNumber(number);

                if (winner.isPresent()) {
                    return winner;
                }
            }

            return Optional.empty();
        }

        protected Optional<Board> drawNumber(int number) {
            for (Board board : this.boards) {
                if (board.addNumber(number)) {
                    return Optional.of(board);
                }
            }

            return Optional.empty();
        }

        private void createBoards(List<String> input) {
            int fromLine = 2;

            while (fromLine + 4 < input.size()) {
                this.createBoard(Arrays.copyOfRange(input.toArray(new String[0]), fromLine, fromLine += 5));
                fromLine++;
            }
        }

        private void createBoard(String[] input) {
            Board board = new Board();

            Arrays.stream(input)
                    .map(StringUtils::normalizeSpace)
                    .forEach(row -> board.addRow(this.parseNumbers(row)));

            this.boards.add(board);
        }

        private Integer[] parseNumbers(String input) {
            return Arrays.stream(input.split(" "))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
        }
    }

    public class LastWinnerBingoGame extends BingoGame {
        @Override
        public Optional<Board> drawNumber(int number) {
            List<Board> pendingBoards = this.boards.stream()
                    .filter(Predicate.not(Board::isCompleted))
                    .collect(Collectors.toList());

            return this.drawNumber(number, pendingBoards);
        }

        private Optional<Board> drawNumber(int number, List<Board> boards) {
            List<Board> winners = boards.stream()
                    .filter(board -> board.addNumber(number))
                    .collect(Collectors.toList());

            return this.pickWinner(boards, winners);
        }

        private Optional<Board> pickWinner(List<Board> boards, List<Board> winners) {
            if (winners.size() == boards.size()) {
                return Optional.of(winners.get(winners.size() - 1));
            }

            return Optional.empty();
        }
    }

    public class Board {
        private final List<BoardPosition> positions = new ArrayList<>();
        private int lastNumber;
        private int rows;

        public void addRow(Integer[] numbers) {
            for (int x = 0; x < numbers.length; x++) {
                this.positions.add(new BoardPosition(numbers[x], x, rows, false));
            }

            rows++;
        }

        public boolean addNumber(int number) {
            this.setLastNumber(number);
            this.matchBoardPosition(number);

            return this.isCompleted();
        }

        private void matchBoardPosition(int number) {
            this.positions.stream()
                    .filter(boardPosition -> boardPosition.getValue() == number)
                    .findFirst()
                    .ifPresent(BoardPosition::mark);
        }

        private boolean isCompleted(Function<BoardPosition, Integer> grouper) {
            return this.positions.stream()
                    .filter(BoardPosition::isMarked)
                    .collect(Collectors.groupingBy(grouper))
                    .values().stream().anyMatch(match -> match.size() == rows);
        }


        private boolean isCompleted() {
            return this.isCompleted(BoardPosition::getColumn) || this.isCompleted(BoardPosition::getRow);
        }

        private void setLastNumber(int number) {
            this.lastNumber = number;
        }

        private int sumOfUnmarked() {
            return this.positions.stream().filter(Predicate.not(BoardPosition::isMarked)).mapToInt(BoardPosition::getValue).sum();
        }

        public int getFinalScore() {
            return this.sumOfUnmarked() * this.lastNumber;
        }
    }

    public class BoardPosition {
        private final int value;
        private final int column;
        private final int row;
        private boolean marked;

        public BoardPosition(int value, int column, int row, boolean marked) {
            this.value = value;
            this.column = column;
            this.row = row;
            this.marked = marked;
        }

        public int getValue() {
            return value;
        }

        public int getColumn() {
            return column;
        }

        public int getRow() {
            return row;
        }

        public boolean isMarked() {
            return marked;
        }

        public void mark() {
            this.marked = true;
        }
    }
}
