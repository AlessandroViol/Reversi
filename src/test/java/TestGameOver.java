package units.sdm;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestGameOver {
    final static int B = Checkerboard.B;
    final static int W = Checkerboard.W;
    final static int N = Checkerboard.N;
    final static int A = Checkerboard.A;

    @Test
    void endGameFull() {


        int[][] GameOverFull = {{W, W, W, B, B, B, B, B},
                                {W, W, W, W, W, W, W, B},
                                {W, W, W, W, W, W, W, W},
                                {W, W, W, W, W, B, W, B},
                                {W, W, W, W, B, W, B, B},
                                {W, W, B, B, B, B, B, B},
                                {W, B, B, W, W, W, W, B},
                                {B, B, B, W, W, W, W, B}};



        Checkerboard checkerboard = new Checkerboard(GameOverFull);
        assertTrue(checkerboard.gameOver());

    }
    @Test
    void endGame() {


        int[][] GameOverNotFull = {{W, W, W, W, W, W, W, W},
                                    {W, W, W, W, W, W, W, W},
                                    {W, W, W, W, W, W, W, W},
                                    {W, W, W, W, W, W, W, N},
                                    {W, W, W, W, W, W, N, N},
                                    {W, W, W, W, W, W, N, B},
                                    {W, W, W, W, W, W, W, N},
                                    {W, W, W, W, W, W, W, W}};



        Checkerboard checkerboard = new Checkerboard(GameOverNotFull);
        assertTrue(checkerboard.gameOver());
    }
    @Test
    void endGameFalse() {
        Checkerboard checkerboard = new Checkerboard();
        assertFalse(checkerboard.gameOver());
    }

}
