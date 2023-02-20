package units.sdm;

import org.junit.jupiter.api.Test;
import units.sdm.Grid;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestPlace {
    @Test
    void placeWhite() {
        Grid grid = new Grid();

        grid.place(4, 2, -1);
        grid.place(2, 4, -1);

        int[][] expectedGrid = {{0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, -1, 0, 0, 0},
                {0, 0, 0, -1, -1, 0, 0, 0},
                {0, 0, -1, -1, -1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};

        assertArrayEquals(expectedGrid, grid.grid);
    }

    @Test
    void placeBlack() {
        Grid grid = new Grid();

        grid.place(2, 3, 1);
        grid.place(4, 5, 1);

        int[][] expectedGrid = {{0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};

        assertArrayEquals(expectedGrid, grid.grid);
    }
}
