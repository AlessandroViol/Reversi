package units.sdm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestDisksCount {

    @Test
    void numberDisks() {

        int[][] count = {{0, 0, -1, -1, -1, -1, -1, -1},
                        {0, 0, 1, -1, -1, -1, -1, -1},
                        {0, 0, 0, -1, -1, -1, -1, 0},
                        {0, 0, -1, -1, -1, -1, -1, 0},
                        {1, 1, -1, -1, 1, 1, -1, -1},
                        {0, -1, 1, -1, 0, 0, -1, 0},
                        {-1, 0, 0, 1, 0, 0, -1, 0},
                        {0, 0, 0, 0, 1, 0, 0, 0}};

        Checkerboard checkerboard = new Checkerboard(count);

        int NWhites = 29;
        int NBlacks = 8;

        checkerboard.disksCount();

        assertEquals(checkerboard.NumberOfWhites, NWhites);
        assertEquals(checkerboard.NumberOfBlacks, NBlacks);
    }
}