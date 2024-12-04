package pl.teksusik.aoc.challenge;

import pl.teksusik.aoc.Challenge;

public class Day04 extends Challenge {
    public Day04(int day, String input) {
        super(day, input);
    }

    @Override
    public String solveFirstPart() {
        char[][] grid = this.getLines().stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        int[][] directions = new int[][] {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1},
                {-1, -1},
                {1, -1},
                {-1, 1},
                {1, 1}
        };

        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 'X') {
                    for (int[] direction : directions) {
                        if (this.createsWord(grid, direction, row, col, "XMAS")) {
                            count++;
                        }
                    }
                }
            }
        }

        return String.valueOf(count);
    }

    @Override
    public String solveSecondPart() {
        char[][] grid = this.getLines().stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        char[][][] subgrids = {
                {
                        {'M', '.', 'M'},
                        {'.', 'A', '.'},
                        {'S', '.', 'S'}
                },
                {
                        {'S', '.', 'S'},
                        {'.', 'A', '.'},
                        {'M', '.', 'M'}
                },
                {
                        {'M', '.', 'S'},
                        {'.', 'A', '.'},
                        {'M', '.', 'S'}
                },
                {
                        {'S', '.', 'M'},
                        {'.', 'A', '.'},
                        {'S', '.', 'M'}
                }
        };

        int occurrences = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (char[][] subgrid : subgrids) {
            int subgridRows = subgrid.length;
            int subgridCols = subgrid[0].length;

            for (int startRow = 0; startRow <= rows - subgridRows; startRow++) {
                for (int startCol = 0; startCol <= cols - subgridCols; startCol++) {
                    if (this.subgridExists(grid, subgrid, startCol, startRow)) {
                        occurrences++;
                    }
                }
            }
        }

        return String.valueOf(occurrences);
    }

    public boolean subgridExists(char[][] grid, char[][] subgrid, int startRow, int startCol) {
        int subgridRows = subgrid.length;
        int subgridCols = subgrid[0].length;

        for (int row = 0; row < subgridRows; row++) {
            for (int col = 0; col < subgridCols; col++) {
                char subgridValue = subgrid[row][col];

                if (subgridValue == '.') {
                    continue;
                }

                if (grid[startCol + row][startRow + col] != subgridValue) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean createsWord(char[][] grid, int[] direction, int currentRow, int currentCol, String word) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < word.length(); i++) {
            int newRow = currentRow + direction[0] * i;
            int newCol = currentCol + direction[1] * i;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || grid[newRow][newCol] != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
