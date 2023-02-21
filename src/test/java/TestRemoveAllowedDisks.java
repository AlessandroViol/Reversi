package units.sdm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestRemoveAllowedDisks {

    @Test
    void remove(){

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.complexCheckerboardAllowedDisks);
        checkerboard.removeAllowedDisks();

        assertArrayEquals(checkerboard.checkerboard, CheckerboardUtility.complexCheckerboard);


    }

}
