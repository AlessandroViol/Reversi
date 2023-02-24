package units.sdm;

import org.junit.jupiter.api.Test;
import units.sdm.CheckerboardUtility;
import units.sdm.ConsoleReversi;
import units.sdm.Checkerboard;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleReversiTest {
    @Test
    void consoleOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ConsoleReversi view = new ConsoleReversi();
        view.displayTurn(new Checkerboard());

        String expectedOutput = (char) 27 + "[4m  |A|B|C|D|E|F|G|H" + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m1 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m2 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m3 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m4 | | | |o|\u001B[31mo\u001B[0m" + (char) 27 + "[4m| | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m5 | | | |\u001B[31mo\u001B[0m" + (char) 27 + "[4m|o| | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m6 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m7 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m8 | | | | | | | | " + (char) 27 + "[0m|\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void consoleOutputAllowedPlace() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ConsoleReversi view = new ConsoleReversi();

        Checkerboard checkerboard = new Checkerboard();
        checkerboard.addAllowedDisks(Checkerboard.B);
        view.displayTurn(checkerboard);

        String expectedOutput = (char) 27 + "[4m  |A|B|C|D|E|F|G|H" + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m1 | | | | | | | | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m2 | | | | | | | | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m3 | | | |x| | | | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m4 | | |x|o|\u001B[31mo\u001B[0m" + (char) 27 + "[4m| | | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m5 | | | |\u001B[31mo\u001B[0m" + (char) 27 + "[4m|o|x| | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m6 | | | | |x| | | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m7 | | | | | | | | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m8 | | | | | | | | " + (char) 27 + "[0m|\n";

        assertEquals(expectedOutput, outputStream.toString());
    }
}
