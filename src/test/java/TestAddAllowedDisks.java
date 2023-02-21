package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestAddAllowedDisks {

    @Test
    void addPegs() {

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.complexCheckerboard);
        checkerboard.addAllowedDisks(1);

        assertArrayEquals(checkerboard.checkerboard, CheckerboardUtility.complexCheckerboardAllowedDisks);

    }


}