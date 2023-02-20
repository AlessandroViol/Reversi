package units.sdm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestPegsCount {

    @Test
    void NumberPegs() {

        int[][] count = {{0, 0, -1, -1, -1, -1, -1, -1},
                        {0, 0, 1, -1, -1, -1, -1, -1},
                        {0, 0, 0, -1, -1, -1, -1, 0},
                        {0, 0, -1, -1, -1, -1, -1, 0},
                        {1, 1, -1, -1, 1, 1, -1, -1},
                        {0, -1, 1, -1, 0, 0, -1, 0},
                        {-1, 0, 0, 1, 0, 0, -1, 0},
                        {0, 0, 0, 0, 1, 0, 0, 0}};

        Grid grid = new Grid(count);

        int NWhites = 29;
        int NBlacks = 8;

        grid.PegsCount();

        assertEquals(grid.NumberOfWhites, NWhites);
        assertEquals(grid.NumberOfBlacks, NBlacks);
    }
}