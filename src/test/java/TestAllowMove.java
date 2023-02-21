package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAllowMove {
    @Test
    void defaultCheckerboard() {

        Checkerboard checkerboard = new Checkerboard();

        int[][] referenceCheckerboard = {{0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0, 0},
                {0, 0, 2, -1, 1, 0, 0, 0},
                {0, 0, 0, 1, -1, 2, 0, 0},
                {0, 0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkerboard.allowPlace(i, j, 1)) checkerboard.checkerboard[i][j] = 2;
            }
        }

        assertArrayEquals(referenceCheckerboard, checkerboard.checkerboard);

    }

    @Test
    void complexCheckerboard() {

        ConsoleReversi view = new ConsoleReversi();

        int[][] checkerboardMatrix = {{0, 0, -1, -1, -1, -1, -1, -1},
                {0, 0, 1, -1, -1, -1, -1, -1},
                {0, 0, 0, -1, -1, -1, -1, 0},
                {0, 0, -1, -1, -1, -1, -1, 0},
                {1, 1, -1, -1, 1, 1, -1, -1},
                {0, -1, 1, -1, 0, 0, -1, 0},
                {-1, 0, 0, 1, 0, 0, -1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0}};

        Checkerboard checkerboard = new Checkerboard(checkerboardMatrix);

        int[][] referenceCheckerboard = {{0, 0, -1, -1, -1, -1, -1, -1},
                {0, 0, 1, -1, -1, -1, -1, -1},
                {0, 0, 2, -1, -1, -1, -1, 2},
                {0, 0, -1, -1, -1, -1, -1, 0},
                {1, 1, -1, -1, 1, 1, -1, -1},
                {2, -1, 1, -1, 2, 0, -1, 0},
                {-1, 2, 2, 1, 0, 0, -1, 2},
                {0, 0, 0, 0, 1, 0, 0, 0}};

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkerboard.allowPlace(i, j, 1)) checkerboard.checkerboard[i][j] = 2;
            }
        }

        assertArrayEquals(referenceCheckerboard, checkerboard.checkerboard);

    }


}
