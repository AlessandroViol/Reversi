package units.sdm;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TestCheckerboard {

    @Test
    void defaultGrid() {
        Checkerboard checkerboard = new Checkerboard();

        int[][] referenceCheckerboard = {{0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, -1, 1, 0, 0, 0},
                {0, 0, 0, 1, -1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};


        assertArrayEquals(referenceCheckerboard, checkerboard.checkerboard);


    }


    @Test
    void checkComplexCheckerboard() {

        int[][] referenceCheckerboard = {{-1, -1, 1, -1, 0, 0, 0, -1},
                {1, 1, -1, 1, -1, 0, -1, -1},
                {1, 1, -1, -1, -1, -1, 1, -1},
                {0, 0, -1, -1, -1, -1, 1, -1},
                {0, 1, 1, 1, 1, 1, 1, -1},
                {0, 0, 0, -1, 1, -1, 1, -1},
                {0, 0, -1, -1, -1, 1, 1, -1},
                {0, 0, 0, -1, 0, 0, 1, -1}};

        Checkerboard checkerboard = new Checkerboard(referenceCheckerboard);


        assertArrayEquals(referenceCheckerboard, checkerboard.checkerboard);


    }

}


