package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestExistAllowedPlace {

    final static int B = Checkerboard.B;
    final static int W = Checkerboard.W;
    final static int N = Checkerboard.N;
    final static int A = Checkerboard.A;
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
}
