package units.sdm;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TestCheckerboard {

    final static int B = Checkerboard.B;
    final static int W = Checkerboard.W;
    final static int N = Checkerboard.N;
    final static int A = Checkerboard.A;

    @Test
    void defaultGrid() {
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
    void checkComplexCheckerboard() {

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

}


