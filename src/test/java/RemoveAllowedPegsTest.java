package units.sdm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class RemoveAllowedPegsTest {

    @Test
    void Remove(){

        int[][] GridAllowedMoves = {{0, 0, -1, -1, -1, -1, -1, -1},
                {0, 0, 1, -1, -1, -1, -1, -1},
                {0, 0, 2, -1, -1, -1, -1, 2},
                {0, 0, -1, -1, -1, -1, -1, 0},
                {1, 1, -1, -1, 1, 1, -1, -1},
                {2, -1, 1, -1, 2, 0, -1, 0},
                {-1, 2, 2, 1, 0, 0, -1, 2},
                {0, 0, 0, 0, 1, 0, 0, 0}};

        int[][] Grid = {{0, 0, -1, -1, -1, -1, -1, -1},
                {0, 0, 1, -1, -1, -1, -1, -1},
                {0, 0, 0, -1, -1, -1, -1, 0},
                {0, 0, -1, -1, -1, -1, -1, 0},
                {1, 1, -1, -1, 1, 1, -1, -1},
                {0, -1, 1, -1, 0, 0, -1, 0},
                {-1, 0, 0, 1, 0, 0, -1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0}};

        Grid grid = new Grid(GridAllowedMoves);
        grid.RemoveAllowedPegs();

        assertArrayEquals(grid.grid, Grid);


    }


}
