package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckerboardTest {
    //define some shorthand constants
    private final static int B = Checkerboard.B;
    private final static int W = Checkerboard.W;
    private final static int N = Checkerboard.N;
    private final static int A = Checkerboard.A;

    //the zero arguments constructor should set the checkerboard matrix to the starting reversi layout
    @Test
    void createDefaultCheckerboard() {
        Checkerboard checkerboard = new Checkerboard();

        int[][] referenceCheckerboard = {{N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, W, B, N, N, N},
                {N, N, N, B, W, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N}};

        assertArrayEquals(referenceCheckerboard, checkerboard.getCheckerboard());
    }

    //the one argument constructor should set the checkerboard matrix be a copy of the provided one
    @Test
    void createComplexCheckerboard() {
        int[][] referenceCheckerboard = {{W, W, B, W, N, N, N, W},
                {B, B, W, B, W, N, W, W},
                {B, B, W, W, W, W, B, W},
                {N, N, W, W, W, W, B, W},
                {N, B, B, B, B, B, B, W},
                {N, N, N, W, B, W, B, W},
                {N, N, W, W, W, B, B, W},
                {N, N, N, W, N, N, B, W}};

        Checkerboard checkerboard = new Checkerboard(referenceCheckerboard);

        assertArrayEquals(referenceCheckerboard, checkerboard.getCheckerboard());
    }

    //the addAllowedDisks method should mark all the allowed placings in a generic board layout
    @Test
    void addAllowedDisks() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        checkerboard.addAllowedDisks(Checkerboard.B);

        assertArrayEquals(checkerboard.getCheckerboard(), CheckerboardUtility.COMPLEX_CHECKERBOARD_ALLOWED_DISKS);
    }

    //the addAllowedDisks method should mark all the allowed placings in the starting layout
    @Test
    void allowPlaceDefaultCheckerboard() {
        Checkerboard checkerboard = new Checkerboard();

        for (int i = 0; i < Checkerboard.SIZE; i++) {
            for (int j = 0; j < Checkerboard.SIZE; j++) {
                if (checkerboard.allowPlace(i, j, Checkerboard.B))
                    checkerboard.getCheckerboard()[i][j] = Checkerboard.A;
            }
        }

        assertArrayEquals(CheckerboardUtility.SIMPLE_CHECKERBOARD_ALLOWED_DISKS, checkerboard.getCheckerboard());
    }

    //the method allowPlace should be able to recognize all the allowed placings in a generic board layout
    @Test
    void allowPlaceComplexCheckerboard() {
        ConsoleReversi view = new ConsoleReversi();
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        for (int i = 0; i < Checkerboard.SIZE; i++) {
            for (int j = 0; j < Checkerboard.SIZE; j++) {
                if (checkerboard.allowPlace(i, j, Checkerboard.B))
                    checkerboard.getCheckerboard()[i][j] = Checkerboard.A;
            }
        }

        assertArrayEquals(checkerboard.getCheckerboard(), CheckerboardUtility.COMPLEX_CHECKERBOARD_ALLOWED_DISKS);
    }

    //The method disksCount should count the correct number of black disks
    @Test
    void disksCountBlack() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        int numBlacks = 8;

        checkerboard.disksCount();

        assertEquals(checkerboard.getNumberOfBlacks(), numBlacks);
    }

    //the method disksCount should count the correct number of white disks
    @Test
    void disksCountWhite() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        int numWhites = 29;

        checkerboard.disksCount();

        assertEquals(checkerboard.getNumberOfWhites(), numWhites);
    }

    //when placing a white disk in an allowed position the values of the matrix elements should be set to white according to Reversi rules
    @Test
    void placeWhite() {
        Checkerboard checkerboard = new Checkerboard();

        int[][] expectedMatrix = {{N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, W, N, N, N},
                {N, N, N, W, W, N, N, N},
                {N, N, W, W, W, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N}};

        checkerboard.place(4, 2, W);
        checkerboard.place(2, 4, W);

        assertArrayEquals(expectedMatrix, checkerboard.getCheckerboard());
    }

    //when placing a black disk in an allowed position the values of the matrix elements should be set to black according to Reversi rules
    @Test
    void placeBlack() {
        Checkerboard checkerboard = new Checkerboard();

        int[][] expectedMatrix = {{N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, B, N, N, N, N},
                {N, N, N, B, B, N, N, N},
                {N, N, N, B, B, B, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N}};

        checkerboard.place(2, 3, B);
        checkerboard.place(4, 5, B);

        assertArrayEquals(expectedMatrix, checkerboard.getCheckerboard());
    }

    //the place method should work in all directions and be able to handle the border squares
    @Test
    void placeAtBounds() {
        int[][] borderMatrix = {{B, N, N, N, B, N, N, B},
                {N, W, N, N, W, N, W, N},
                {N, N, W, N, W, W, N, N},
                {B, W, W, N, N, W, W, B},
                {N, N, N, N, N, N, N, N},
                {N, N, W, N, W, W, N, N},
                {N, W, N, N, W, N, W, N},
                {B, N, N, N, B, N, N, B}};
        Checkerboard checkerboard = new Checkerboard(borderMatrix);

        int[][] expectedMatrix = {{B, N, N, N, B, N, N, B},
                {N, B, N, N, B, N, B, N},
                {N, N, B, N, B, B, N, N},
                {B, B, B, B, B, B, B, B},
                {N, N, N, B, B, N, N, N},
                {N, N, B, N, B, B, N, N},
                {N, B, N, N, B, N, B, N},
                {B, N, N, N, B, N, N, B}};

        checkerboard.place(3, 3, B);
        checkerboard.place(3, 4, B);
        checkerboard.place(4, 3, B);
        checkerboard.place(4, 4, B);

        assertArrayEquals(expectedMatrix, checkerboard.getCheckerboard());
    }

    //the method existAllowPlace should return false if the white player doesn't have any allowed placing
    @Test
    void noAllowedPlaces() {
        int[][] noPlaces = {{B, B, B, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(noPlaces);

        assertFalse(checkerboard.existAllowedPlace(Checkerboard.W));
    }

    //the method existAllowPlace should return false if the white player doesn't have any allowed placing
    @Test
    void allowedPlaces() {
        int[][] noPlaces = {{B, B, B, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(noPlaces);

        assertTrue(checkerboard.existAllowedPlace(Checkerboard.B));
    }

    //the updateCheckerboard method should be able to swap the color of the disks in an allowed direction
    @Test
    void updateCheckerboard() {
        int[][] checkerboardMatrix = {{N, N, W, W, W, W, W, W},
                {N, N, B, W, W, W, W, W},
                {N, N, N, W, W, W, W, B},
                {N, N, W, W, W, W, W, N},
                {B, B, W, W, B, B, W, W},
                {N, W, B, W, N, N, W, N},
                {W, N, N, B, N, N, W, N},
                {N, N, N, N, B, N, N, N}};
        Checkerboard checkerboard = new Checkerboard(checkerboardMatrix);

        int[][] referenceCheckerboard = {{N, N, W, W, W, W, W, W},
                {N, N, B, W, W, W, W, W},
                {N, N, N, W, W, W, W, B},
                {N, N, W, W, W, W, B, N},
                {B, B, W, W, B, B, W, W},
                {N, W, B, W, N, N, W, N},
                {W, N, N, B, N, N, W, N},
                {N, N, N, N, B, N, N, N}};

        checkerboard.updateCheckerboard(2, 7, B);

        assertArrayEquals(referenceCheckerboard, checkerboard.getCheckerboard());
    }

    //the removeAllowedDisks method should remove the marks of allowed placings
    @Test
    void removeAllowedDisks() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD_ALLOWED_DISKS);
        checkerboard.removeAllowedDisks();

        assertArrayEquals(checkerboard.getCheckerboard(), CheckerboardUtility.COMPLEX_CHECKERBOARD);
    }
}