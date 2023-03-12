package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckerboardTest {
    //define some shorthand constants
    private static final int B = Checkerboard.B;
    private static final int W = Checkerboard.W;
    private static final int N = Checkerboard.N;
    private static final int A = Checkerboard.A;

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

        assertArrayEquals(referenceCheckerboard, checkerboard.getMatrix());
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

        assertArrayEquals(referenceCheckerboard, checkerboard.getMatrix());
    }

    //the addAllowedDisks method should mark all the allowed placings in a generic board layout
    @Test
    void addAllowedDisks() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        checkerboard.addAllowedDisks(B);

        assertArrayEquals(CheckerboardUtility.COMPLEX_CHECKERBOARD_ALLOWED_DISKS, checkerboard.getMatrix());
    }

    //the addAllowedDisks method should mark all the allowed placings in the starting layout
    @Test
    void allowPlaceDefaultCheckerboard() {
        Checkerboard checkerboard = new Checkerboard();

        for (int i = 0; i < Checkerboard.SIZE; i++) {
            for (int j = 0; j < Checkerboard.SIZE; j++) {
                if (checkerboard.allowPlace(i, j, B))
                    checkerboard.getMatrix()[i][j] = A;
            }
        }

        assertArrayEquals(CheckerboardUtility.SIMPLE_CHECKERBOARD_ALLOWED_DISKS, checkerboard.getMatrix());
    }

    //the method allowPlace should be able to recognize all the allowed placings in a generic board layout
    @Test
    void allowPlaceComplexCheckerboard() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        for (int i = 0; i < Checkerboard.SIZE; i++) {
            for (int j = 0; j < Checkerboard.SIZE; j++) {
                if (checkerboard.allowPlace(i, j, B))
                    checkerboard.getMatrix()[i][j] = A;
            }
        }

        assertArrayEquals(CheckerboardUtility.COMPLEX_CHECKERBOARD_ALLOWED_DISKS, checkerboard.getMatrix());
    }

    //The method disksCount should count the correct number of black disks
    @Test
    void disksCountBlack() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        int numBlacks = 8;

        checkerboard.disksCount();

        assertEquals(numBlacks, checkerboard.getNumberOfBlacks());
    }

    //the method disksCount should count the correct number of white disks
    @Test
    void disksCountWhite() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        int numWhites = 29;

        checkerboard.disksCount();

        assertEquals(numWhites, checkerboard.getNumberOfWhites());
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

        assertArrayEquals(expectedMatrix, checkerboard.getMatrix());
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

        assertArrayEquals(expectedMatrix, checkerboard.getMatrix());
    }

    //the place method for black disks should work in all directions and be able to handle the border squares
    @Test
    void blackPlaceAtBounds() {
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

        assertArrayEquals(expectedMatrix, checkerboard.getMatrix());
    }

    //the place method for white disks should work in all directions and be able to handle the border squares
    @Test
    void whitePlaceAtBounds() {
        int[][] borderMatrix = {{W, N, N, N, W, N, N, W},
                {N, B, N, N, B, N, B, N},
                {N, N, B, N, B, B, N, N},
                {W, B, B, N, N, B, B, W},
                {N, N, N, N, N, N, N, N},
                {N, N, B, N, B, B, N, N},
                {N, B, N, N, B, N, B, N},
                {W, N, N, N, W, N, N, W}};
        Checkerboard checkerboard = new Checkerboard(borderMatrix);

        int[][] expectedMatrix = {{W, N, N, N, W, N, N, W},
                {N, W, N, N, W, N, W, N},
                {N, N, W, N, W, W, N, N},
                {W, W, W, W, W, W, W, W},
                {N, N, N, W, W, N, N, N},
                {N, N, W, N, W, W, N, N},
                {N, W, N, N, W, N, W, N},
                {W, N, N, N, W, N, N, W}};

        checkerboard.place(3, 3, W);
        checkerboard.place(3, 4, W);
        checkerboard.place(4, 3, W);
        checkerboard.place(4, 4, W);

        assertArrayEquals(expectedMatrix, checkerboard.getMatrix());
    }

    //the place method shouldn't edit the matrix if the specified position is not allowed for white
    @Test
    void whitePlaceNotAllowed() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        checkerboard.addAllowedDisks(B);

        Checkerboard referenceCheckerboard = new Checkerboard(checkerboard.getMatrix());

        //empty square with no adjacent
        checkerboard.place(0, 0, W);
        //square marked as allowed for black, but not allowed for white
        checkerboard.place(6, 7, W);
        //square that is allowed for white but is occupied by black
        checkerboard.place(4, 4, W);
        //square that is allowed for white but is occupied by white
        checkerboard.place(4, 6, W);

        assertArrayEquals(referenceCheckerboard.getMatrix(), checkerboard.getMatrix());
    }

    //the place method shouldn't edit the matrix if the specified position is not allowed for black
    @Test
    void blackPlaceNotAllowed() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);
        checkerboard.addAllowedDisks(W);

        Checkerboard referenceCheckerboard = new Checkerboard(checkerboard.getMatrix());

        //empty square with no adjacent
        checkerboard.place(0, 0, B);
        //square marked as allowed for white, but not allowed for black
        checkerboard.place(0, 1, B);
        //square that is allowed for black but is occupied by white
        checkerboard.place(1, 6, B);
        //square that is allowed for black but is occupied by black
        checkerboard.place(4, 4, B);

        assertArrayEquals(referenceCheckerboard.getMatrix(), checkerboard.getMatrix());
    }

    //the method existAllowPlace should return false if the white player doesn't have any allowed placing
    @Test
    void whiteHasNoAllowedPlacings() {
        int[][] whiteHasNoPlaces = {{B, B, B, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(whiteHasNoPlaces);

        assertFalse(checkerboard.existAllowedPlace(W));
    }

    //the method existAllowPlace should return true if the white player have any allowed placing
    @Test
    void whiteHasAllowedPlacings() {
        int[][] whiteHasPlaces = {{B, B, B, B, B, B, B, W},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(whiteHasPlaces);

        assertTrue(checkerboard.existAllowedPlace(W));
    }

    //the method existAllowPlace should return false if the black player doesn't have any allowed placing
    @Test
    void blackHasNoAllowedPlacings() {
        int[][] blackHasNoPlaces = {{W, W, B, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, W, W, B, B, W, B},
                {B, W, B, W, W, W, W, B},
                {B, W, B, W, W, W, W, B},
                {B, W, B, W, B, W, W, B},
                {W, W, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(blackHasNoPlaces);

        assertFalse(checkerboard.existAllowedPlace(B));
    }

    //the method existAllowPlace should return true if the black player have any allowed placing
    @Test
    void blackHasAllowedPlacings() {
        int[][] blackHasPlaces = {{B, B, B, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(blackHasPlaces);

        assertTrue(checkerboard.existAllowedPlace(B));
    }

    //if the checkerboard is full then both players have no available placings gameOver should return true
    @Test
    void endGameFullCheckerboard() {
        int[][] fullMatrix = {{W, W, W, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, B, W, B},
                {W, W, W, W, B, W, B, B},
                {W, W, B, B, B, B, B, B},
                {W, B, B, W, W, W, W, B},
                {B, B, B, W, W, W, W, B}};
        Checkerboard checkerboard = new Checkerboard(fullMatrix);

        assertTrue(checkerboard.gameOver());
    }

    //if both players have no available placings gameOver should return true
    @Test
    void endGameNoAllowedPlace() {
        int[][] notFullMatrix = {{W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, N},
                {W, W, W, W, W, W, N, N},
                {W, W, W, W, W, W, N, B},
                {W, W, W, W, W, W, W, N},
                {W, W, W, W, W, W, W, W}};
        Checkerboard checkerboard = new Checkerboard(notFullMatrix);

        assertTrue(checkerboard.gameOver());
    }

    //if both players have available moves gameOver should return false
    @Test
    void endGameBothHaveAllowedPlaces() {
        Checkerboard checkerboard = new Checkerboard();

        assertFalse(checkerboard.gameOver());
    }

    //if white player has available placings gameOver should return false
    @Test
    void endGameWhiteHasAllowedPlace() {
        int[][] whiteHasAPMatrix = {{B, B, B, B, B, B, B, W},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(whiteHasAPMatrix);

        assertFalse(checkerboard.gameOver());
    }

    //if black player has available placings gameOver should return false
    @Test
    void endGameBlackHasAllowedPlace() {
        int[][] blackHasAPMatrix = {{B, B, B, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        Checkerboard checkerboard = new Checkerboard(blackHasAPMatrix);

        assertFalse(checkerboard.gameOver());
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

        assertArrayEquals(referenceCheckerboard, checkerboard.getMatrix());
    }

    //the removeAllowedDisks method should remove the marks of allowed placings
    @Test
    void removeAllowedDisks() {
        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD_ALLOWED_DISKS);
        checkerboard.removeAllowedDisks();

        assertArrayEquals(CheckerboardUtility.COMPLEX_CHECKERBOARD, checkerboard.getMatrix());
    }

    //the toString method should return the string representation of the default checherboard matrix as expected
    @Test
    void defaultCheckerboardToString() {
        Checkerboard checkerboard = new Checkerboard();

        String referenceString = "   [A][B][C][D][E][F][G][H]\n" +
                "[1][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[2][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[3][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[4][ ][ ][ ][W][B][ ][ ][ ]\n" +
                "[5][ ][ ][ ][B][W][ ][ ][ ]\n" +
                "[6][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[7][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[8][ ][ ][ ][ ][ ][ ][ ][ ]\n";

        assertEquals(referenceString, checkerboard.toString());
    }


    //the toString method should return the string representation of the default checherboard matrix with allowed placings as expected
    @Test
    void defaultCheckerboardAllowedPlaceToString() {
        Checkerboard checkerboard = new Checkerboard();

        String referenceString = "   [A][B][C][D][E][F][G][H]\n" +
                "[1][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[2][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[3][ ][ ][ ][A][ ][ ][ ][ ]\n" +
                "[4][ ][ ][A][W][B][ ][ ][ ]\n" +
                "[5][ ][ ][ ][B][W][A][ ][ ]\n" +
                "[6][ ][ ][ ][ ][A][ ][ ][ ]\n" +
                "[7][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
                "[8][ ][ ][ ][ ][ ][ ][ ][ ]\n";

        checkerboard.addAllowedDisks(B);

        assertEquals(referenceString, checkerboard.toString());
    }
}