package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AddAllowedPegsTest {

    @Test
    void AddPegs() {

        int[][] OperationGrid = {{0, 0, -1, -1, -1, -1, -1, -1},
                                {0, 0, 1, -1, -1, -1, -1, -1},
                                {0, 0, 0, -1, -1, -1, -1, 0},
                                {0, 0, -1, -1, -1, -1, -1, 0},
                                {1, 1, -1, -1, 1, 1, -1, -1},
                                {0, -1, 1, -1, 0, 0, -1, 0},
                                {-1, 0, 0, 1, 0, 0, -1, 0},
                                {0, 0, 0, 0, 1, 0, 0, 0}};

        int[][] ReferenceGrid = {{0, 0, -1, -1, -1, -1, -1, -1},
                                {0, 0, 1, -1, -1, -1, -1, -1},
                                {0, 0, 2, -1, -1, -1, -1, 2},
                                {0, 0, -1, -1, -1, -1, -1, 0},
                                {1, 1, -1, -1, 1, 1, -1, -1},
                                {2, -1, 1, -1, 2, 0, -1, 0},
                                {-1, 2, 2, 1, 0, 0, -1, 2},
                                {0, 0, 0, 0, 1, 0, 0, 0}};

        Grid grid = new Grid(OperationGrid);
        grid.AddAllowedPegs(1);

        assertArrayEquals(grid.grid, ReferenceGrid);

    }


}