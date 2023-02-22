package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAllowPlace {
    @Test
    void defaultCheckerboard() {

        Checkerboard checkerboard = new Checkerboard();

        for (int i = 0; i < Checkerboard.SIZE ; i++) {
            for (int j = 0; j < Checkerboard.SIZE ; j++) {
                if (checkerboard.allowPlace(i, j, Checkerboard.B)) checkerboard.checkerboard[i][j] = Checkerboard.A;
            }
        }

        assertArrayEquals(CheckerboardUtility.simpleCheckerboardAllowedDisks, checkerboard.checkerboard);

    }

    @Test
    void complexCheckerboard() {

        ConsoleReversi view = new ConsoleReversi();

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.complexCheckerboard);

        for (int i = 0; i < Checkerboard.SIZE ; i++) {
            for (int j = 0; j < Checkerboard.SIZE ; j++) {
                if (checkerboard.allowPlace(i, j, Checkerboard.B)) checkerboard.checkerboard[i][j] = Checkerboard.A;
            }
        }

        assertArrayEquals(checkerboard.checkerboard, CheckerboardUtility.complexCheckerboardAllowedDisks);

    }


}
