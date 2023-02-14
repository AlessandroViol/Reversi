package org.example;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TestGrid {

    @Test
    void DefaultGrid() {
        Grid grid = new Grid();

        int[][] TestGrid = {{0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, -1, 1, 0, 0, 0},
                {0, 0, 0, 1, -1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};


        assertArrayEquals(TestGrid, grid.grid);


    }


    @Test
    void CheckComplexGrid() {

        int[][] ComplexGrid = {{-1, -1, 1, -1, 0, 0, 0, -1},
                {1, 1, -1, 1, -1, 0, -1, -1},
                {1, 1, -1, -1, -1, -1, 1, -1},
                {0, 0, -1, -1, -1, -1, 1, -1},
                {0, 1, 1, 1, 1, 1, 1, -1},
                {0, 0, 0, -1, 1, -1, 1, -1},
                {0, 0, -1, -1, -1, 1, 1, -1},
                {0, 0, 0, -1, 0, 0, 1, -1}};

        Grid grid = new Grid(ComplexGrid);


        assertArrayEquals(ComplexGrid, grid.grid);


    }

}


