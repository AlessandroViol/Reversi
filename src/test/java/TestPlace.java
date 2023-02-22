package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestPlace {

    final static int B = Checkerboard.B;
    final static int W = Checkerboard.W;
    final static int N = Checkerboard.N;
    final static int A = Checkerboard.A;
    @Test
    void placeWhite() {
        Checkerboard checkerboard = new Checkerboard();

        checkerboard.place(4, 2, W);
        checkerboard.place(2, 4, W);

        int[][] expectedGrid = {{N, N, N, N, N, N, N, N},
                                {N, N, N, N, N, N, N, N},
                                {N, N, N, N, W, N, N, N},
                                {N, N, N, W, W, N, N, N},
                                {N, N, W, W, W, N, N, N},
                                {N, N, N, N, N, N, N, N},
                                {N, N, N, N, N, N, N, N},
                                {N, N, N, N, N, N, N, N}};

        assertArrayEquals(expectedGrid, checkerboard.checkerboard);
    }

    @Test
    void placeBlack() {
        Checkerboard checkerboard = new Checkerboard();

        checkerboard.place(2, 3, B);
        checkerboard.place(4, 5, B);

        int[][] expectedGrid = {{N, N, N, N, N, N, N, N},
                                {N, N, N, N, N, N, N, N},
                                {N, N, N, B, N, N, N, N},
                                {N, N, N, B, B, N, N, N},
                                {N, N, N, B, B, B, N, N},
                                {N, N, N, N, N, N, N, N},
                                {N, N, N, N, N, N, N, N},
                                {N, N, N, N, N, N, N, N}};

        assertArrayEquals(expectedGrid, checkerboard.checkerboard);
    }
}
