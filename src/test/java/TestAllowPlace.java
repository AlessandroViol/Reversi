package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAllowPlace {
    @Test
    void defaultCheckerboard() {

        Checkerboard checkerboard = new Checkerboard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkerboard.allowPlace(i, j, 1)) checkerboard.checkerboard[i][j] = 2;
            }
        }

        assertArrayEquals(CheckerboardUtility.simpleCheckerboardAllowedDisks, checkerboard.checkerboard);

    }

    @Test
    void complexCheckerboard() {

        ConsoleReversi view = new ConsoleReversi();

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.complexCheckerboard);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkerboard.allowPlace(i, j, 1)) checkerboard.checkerboard[i][j] = 2;
            }
        }

        assertArrayEquals(checkerboard.checkerboard, CheckerboardUtility.complexCheckerboardAllowedDisks);

    }


}
