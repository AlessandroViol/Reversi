package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestExistAllowedPlace {
    @Test
    void noAllowedPlaces() {
        int[][] noPlaces = {{1, 1, 1, 1, 1, 1, 1, 1},
                {-1, -1, -1, -1, -1, -1, -1, 1},
                {-1, -1, 1, -1, 1, 1, -1, 1},
                {1, -1, 1, 1, -1, -1, -1, 1},
                {1, -1, 1, -1, -1, 1, -1, 1},
                {1, -1, 1, -1, 1, -1, -1, 1},
                {-1, 1, -1, -1, -1, -1, -1, 0},
                {1, 1, 1, 1, 1, 1, 1, 0}};

        Checkerboard checkerboard = new Checkerboard(noPlaces);

        assertFalse(checkerboard.existAllowedPlace(-1));
    }

    @Test
    void allowedPlaces() {
        int[][] noPlaces = {{1, 1, 1, 1, 1, 1, 1, 1},
                {-1, -1, -1, -1, -1, -1, -1, 1},
                {-1, -1, 1, -1, 1, 1, -1, 1},
                {1, -1, 1, 1, -1, -1, -1, 1},
                {1, -1, 1, -1, -1, 1, -1, 1},
                {1, -1, 1, -1, 1, -1, -1, 1},
                {-1, 1, -1, -1, -1, -1, -1, 0},
                {1, 1, 1, 1, 1, 1, 1, 0}};

        Checkerboard checkerboard = new Checkerboard(noPlaces);

        assertTrue(checkerboard.existAllowedPlace(1));
    }
}
