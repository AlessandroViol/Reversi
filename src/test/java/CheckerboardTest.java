package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckerboardTest {
    private final static int B = Checkerboard.B;
    private final static int W = Checkerboard.W;
    private final static int N = Checkerboard.N;
    private final static int A = Checkerboard.A;

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


        assertArrayEquals(referenceCheckerboard, checkerboard.checkerboard);

    }

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

        assertArrayEquals(referenceCheckerboard, checkerboard.checkerboard);

    }

    @Test
    void addAllowedDisks() {

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.complexCheckerboard);
        checkerboard.addAllowedDisks(Checkerboard.B);

        assertArrayEquals(checkerboard.checkerboard, CheckerboardUtility.complexCheckerboardAllowedDisks);

    }

    @Test
    void allowPlaceDefaultCheckerboard() {

        Checkerboard checkerboard = new Checkerboard();

        for (int i = 0; i < Checkerboard.SIZE; i++) {
            for (int j = 0; j < Checkerboard.SIZE; j++) {
                if (checkerboard.allowPlace(i, j, Checkerboard.B)) checkerboard.checkerboard[i][j] = Checkerboard.A;
            }
        }

        assertArrayEquals(CheckerboardUtility.simpleCheckerboardAllowedDisks, checkerboard.checkerboard);

    }

    @Test
    void allowPlaceComplexCheckerboard() {

        ConsoleReversi view = new ConsoleReversi();

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.complexCheckerboard);

        for (int i = 0; i < Checkerboard.SIZE; i++) {
            for (int j = 0; j < Checkerboard.SIZE; j++) {
                if (checkerboard.allowPlace(i, j, Checkerboard.B)) checkerboard.checkerboard[i][j] = Checkerboard.A;
            }
        }

        assertArrayEquals(checkerboard.checkerboard, CheckerboardUtility.complexCheckerboardAllowedDisks);

    }

    @Test
    void disksCount() {

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.complexCheckerboard);

        int NWhites = 29;
        int NBlacks = 8;

        checkerboard.disksCount();

        assertEquals(checkerboard.NumberOfWhites, NWhites);
        assertEquals(checkerboard.NumberOfBlacks, NBlacks);
    }

    @Test
    void placeWhite() {
        Checkerboard checkerboard = new Checkerboard();

        checkerboard.place(4, 2, W);
        checkerboard.place(2, 4, W);

        int[][] expectedMatrix = {{N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, W, N, N, N},
                {N, N, N, W, W, N, N, N},
                {N, N, W, W, W, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N}};

        assertArrayEquals(expectedMatrix, checkerboard.checkerboard);
    }

    @Test
    void placeBlack() {
        Checkerboard checkerboard = new Checkerboard();

        checkerboard.place(2, 3, B);
        checkerboard.place(4, 5, B);

        int[][] expectedMatrix = {{N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, B, N, N, N, N},
                {N, N, N, B, B, N, N, N},
                {N, N, N, B, B, B, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N}};

        assertArrayEquals(expectedMatrix, checkerboard.checkerboard);
    }

    @Test
    void placeAtBounds(){
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

        assertArrayEquals(expectedMatrix, checkerboard.checkerboard);
    }

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

    @Test
    void updateCheckerboard() {

        int[][] checkerboardMatrix =
                {{N, N, W, W, W, W, W, W},
                        {N, N, B, W, W, W, W, W},
                        {N, N, N, W, W, W, W, B},
                        {N, N, W, W, W, W, W, N},
                        {B, B, W, W, B, B, W, W},
                        {N, W, B, W, N, N, W, N},
                        {W, N, N, B, N, N, W, N},
                        {N, N, N, N, B, N, N, N}};

        Checkerboard checkerboard = new Checkerboard(checkerboardMatrix);
        checkerboard.updateCheckerboard(2, 7, B);

        int[][] referenceCheckerboard =
                {{N, N, W, W, W, W, W, W},
                        {N, N, B, W, W, W, W, W},
                        {N, N, N, W, W, W, W, B},
                        {N, N, W, W, W, W, B, N},
                        {B, B, W, W, B, B, W, W},
                        {N, W, B, W, N, N, W, N},
                        {W, N, N, B, N, N, W, N},
                        {N, N, N, N, B, N, N, N}};

        assertArrayEquals(referenceCheckerboard, checkerboard.checkerboard);

    }

    @Test
    void removeAllowedDisks(){

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.complexCheckerboardAllowedDisks);
        checkerboard.removeAllowedDisks();

        assertArrayEquals(checkerboard.checkerboard, CheckerboardUtility.complexCheckerboard);


    }
}
