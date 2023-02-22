package units.sdm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestUpdateCheckerboard {

    final static int B = Checkerboard.B;
    final static int W = Checkerboard.W;
    final static int N = Checkerboard.N;
    final static int A = Checkerboard.A;

    @Test
    void test(){
        
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
        checkerboard.updateCheckerboard(2,7,B);

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


}
