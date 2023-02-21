package units.sdm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestDisksCount {

    @Test
    void numberDisks() {

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.complexCheckerboard);

        int NWhites = 29;
        int NBlacks = 8;

        checkerboard.disksCount();

        assertEquals(checkerboard.NumberOfWhites, NWhites);
        assertEquals(checkerboard.NumberOfBlacks, NBlacks);
    }
}