package units.sdm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestUpdateCheckerboard {

    @Test
    void test(){

        int[][] checkerboardMatrix =
                {{0, 0, -1, -1, -1, -1, -1, -1},
                 {0, 0, 1, -1, -1, -1, -1, -1},
                 {0, 0, 0, -1, -1, -1, -1, 1},
                 {0, 0, -1, -1, -1, -1, -1, 0},
                 {1, 1, -1, -1, 1, 1, -1, -1},
                 {0, -1, 1, -1, 0, 0, -1, 0},
                 {-1, 0, 0, 1, 0, 0, -1, 0},
                 {0, 0, 0, 0, 1, 0, 0, 0}};

        Checkerboard checkerboard = new Checkerboard(checkerboardMatrix);
        checkerboard.updateCheckerboard(2,7,1);

        int[][] referenceCheckerboard =
                {{0, 0, -1, -1, -1, -1, -1, -1},
                 {0, 0, 1, -1, -1, -1, -1, -1},
                 {0, 0, 0, -1, -1, -1, -1, 1},
                 {0, 0, -1, -1, -1, -1, 1, 0},
                 {1, 1, -1, -1, 1, 1, -1, -1},
                 {0, -1, 1, -1, 0, 0, -1, 0},
                 {-1, 0, 0, 1, 0, 0, -1, 0},
                 {0, 0, 0, 0, 1, 0, 0, 0}};

        assertArrayEquals(referenceCheckerboard, checkerboard.checkerboard);


   }


}
