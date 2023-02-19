package units.sdm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAllowMove {
    @Test
    void DefaultGrid(){

        Grid grid = new Grid();

        int[][] check = {{0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 2, 0, 0, 0, 0},
                         {0, 0, 2, -1, 1, 0, 0, 0},
                         {0, 0, 0, 1, -1, 2, 0, 0},
                         {0, 0, 0, 0, 2, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0}};

        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid.AllowMove(i, j, 1)) grid.grid[i][j] = 2;
            }
        }

        assertArrayEquals(check, grid.grid);

    }

    @Test
    void ComplexGrid(){

        ConsoleReversi view = new ConsoleReversi();

        int[][] s =  {{0, 0, -1, -1, -1, -1, -1, -1},
                      {0, 0, 1, -1, -1, -1, -1, -1},
                      {0, 0, 0, -1, -1, -1, -1, 0},
                      {0, 0, -1, -1, -1, -1, -1, 0},
                      {1, 1, -1, -1, 1, 1, -1, -1},
                      {0, -1, 1, -1, 0, 0, -1, 0},
                      {-1, 0, 0, 1, 0, 0, -1, 0},
                      {0, 0, 0, 0, 1, 0, 0, 0}};

        Grid grid= new Grid(s);

        int[][] check =  {{0, 0, -1, -1, -1, -1, -1, -1},
                          {0, 0, 1, -1, -1, -1, -1, -1},
                          {0, 0, 2, -1, -1, -1, -1, 2},
                          {0, 0, -1, -1, -1, -1, -1, 0},
                          {1, 1, -1, -1, 1, 1, -1, -1},
                          {2, -1, 1, -1, 2, 0, -1, 0},
                          {-1, 2, 2, 1, 0, 0, -1, 2},
                          {0, 0, 0, 0, 1, 0, 0, 0}};

        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid.AllowMove(i, j, 1)) grid.grid[i][j] = 2;
            }
        }

        assertArrayEquals(check, grid.grid);

    }


}
