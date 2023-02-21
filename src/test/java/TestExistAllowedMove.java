package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestExistAllowedMove {
    @Test
    void noAllowedMoves() {
        int[][] noMoves = {{1, 1, 1, 1, 1, 1, 1, 1},
                {-1, -1, -1, -1, -1, -1, -1, 1},
                {-1, -1, 1, -1, 1, 1, -1, 1},
                {1, -1, 1, 1, -1, -1, -1, 1},
                {1, -1, 1, -1, -1, 1, -1, 1},
                {1, -1, 1, -1, 1, -1, -1, 1},
                {-1, 1, -1, -1, -1, -1, -1, 0},
                {1, 1, 1, 1, 1, 1, 1, 0}};

        Checkerboard checkerboard = new Checkerboard(noMoves);

        assertFalse(checkerboard.existAllowedPlace(-1));
    }

    @Test
    void allowedMoves() {
        int[][] noMoves = {{1, 1, 1, 1, 1, 1, 1, 1},
                {-1, -1, -1, -1, -1, -1, -1, 1},
                {-1, -1, 1, -1, 1, 1, -1, 1},
                {1, -1, 1, 1, -1, -1, -1, 1},
                {1, -1, 1, -1, -1, 1, -1, 1},
                {1, -1, 1, -1, 1, -1, -1, 1},
                {-1, 1, -1, -1, -1, -1, -1, 0},
                {1, 1, 1, 1, 1, 1, 1, 0}};

        Checkerboard checkerboard = new Checkerboard(noMoves);

        assertTrue(checkerboard.existAllowedPlace(1));
    }
}
