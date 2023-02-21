package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestPlace {
    @Test
    void placeWhite() {
        Checkerboard checkerboard = new Checkerboard();

        checkerboard.place(4, 2, -1);
        checkerboard.place(2, 4, -1);

        int[][] expectedGrid = {{0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, -1, 0, 0, 0},
                {0, 0, 0, -1, -1, 0, 0, 0},
                {0, 0, -1, -1, -1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};

        assertArrayEquals(expectedGrid, checkerboard.checkerboard);
    }

    @Test
    void placeBlack() {
        Checkerboard checkerboard = new Checkerboard();

        checkerboard.place(2, 3, 1);
        checkerboard.place(4, 5, 1);

        int[][] expectedGrid = {{0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};

        assertArrayEquals(expectedGrid, checkerboard.checkerboard);
    }
}
