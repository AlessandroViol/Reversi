package units.sdm;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleReversiTest {
    private final String B = "\u001B[31mo\u001B[0m" + (char) 27 + "[4m";
    private final String A = "\033[0;32mx\u001B[0m" + (char) 27 + "[4m";

    @Test
    void consoleOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ConsoleReversi view = new ConsoleReversi();
        Game game = new Game(view);

        view.installLogic(game);

        view.displayCheckerboard();

        String expectedOutput = (char) 27 + "[4m  |A|B|C|D|E|F|G|H" + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m1 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m2 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m3 | | | | | | | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m4 | | | |o|" + B + "| | | " + (char) 27 + "[0m|\n" +
                                (char) 27 + "[4m5 | | | |" + B + "|o| | | " + (char) 27 + "[0m|\n" +
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
        Game game = new Game(view);

        view.installLogic(game);

        game.getCheckerboard().addAllowedDisks(Checkerboard.B);

        view.displayCheckerboard();

        String expectedOutput = (char) 27 + "[4m  |A|B|C|D|E|F|G|H" + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m1 | | | | | | | | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m2 | | | | | | | | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m3 | | | |" + A +"| | | | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m4 | | |" + A + "|o|" + B + "| | | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m5 | | | |" + B + "|o|" + A + "| | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m6 | | | | |" + A + "| | | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m7 | | | | | | | | " + (char) 27 + "[0m|\n" +
                (char) 27 + "[4m8 | | | | | | | | " + (char) 27 + "[0m|\n";

        assertEquals(expectedOutput, outputStream.toString());
    }
}
