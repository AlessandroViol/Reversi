package units.sdm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestUpdateGrid {

    @Test
    void Test(){

        int[][] Grid =
                {{0, 0, -1, -1, -1, -1, -1, -1},
                 {0, 0, 1, -1, -1, -1, -1, -1},
                 {0, 0, 0, -1, -1, -1, -1, 1},
                 {0, 0, -1, -1, -1, -1, -1, 0},
                 {1, 1, -1, -1, 1, 1, -1, -1},
                 {0, -1, 1, -1, 0, 0, -1, 0},
                 {-1, 0, 0, 1, 0, 0, -1, 0},
                 {0, 0, 0, 0, 1, 0, 0, 0}};

        Grid grid = new Grid(Grid);
        grid.update_Grid(2,7,1);

        int[][] GridNextMove =
                {{0, 0, -1, -1, -1, -1, -1, -1},
                 {0, 0, 1, -1, -1, -1, -1, -1},
                 {0, 0, 0, -1, -1, -1, -1, 1},
                 {0, 0, -1, -1, -1, -1, 1, 0},
                 {1, 1, -1, -1, 1, 1, -1, -1},
                 {0, -1, 1, -1, 0, 0, -1, 0},
                 {-1, 0, 0, 1, 0, 0, -1, 0},
                 {0, 0, 0, 0, 1, 0, 0, 0}};

        assertArrayEquals(GridNextMove, grid.grid);


   }


}
