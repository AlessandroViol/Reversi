package units.sdm;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormattedConsoleReversiTest {
    private final String BOLD = (char) 27 + "[1m";
    private final String LINE = (char) 27 + "[4m";
    private final String RED = (char) 27 + "[31m";
    private final String GREEN = (char) 27 + "[32m";
    private final String WHITE = (char) 27 + "[97m";
    private final String END = (char) 27 + "[0m";

    @Test
    void consoleOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ConsoleReversi view = new FormattedConsoleReversi();
        ReversiGame reversiGame = new ReversiGame(view);

        view.installLogic(reversiGame);

        view.displayCheckerboard();

        String expectedOutput = LINE + WHITE + "  |A|B|C|D|E|F|G|H" + END + WHITE + "|\n" +
                                LINE + "1 | | | | | | | | " + END + WHITE  + "|\n" +
                                LINE + "2 | | | | | | | | " + END + WHITE + "|\n" +
                                LINE + "3 | | | | | | | | " + END + WHITE + "|\n" +
                                LINE + "4 | | | |o|" + RED + "o" + WHITE + "| | | " + END + WHITE + "|\n" +
                                LINE + "5 | | | |" + RED + "o" + WHITE + "|o| | | " + END + WHITE + "|\n" +
                                LINE + "6 | | | | | | | | " + END + WHITE + "|\n" +
                                LINE + "7 | | | | | | | | " + END + WHITE + "|\n" +
                                LINE + "8 | | | | | | | | " + END + WHITE + "|\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void consoleOutputAllowedPlace() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ConsoleReversi view = new FormattedConsoleReversi();
        ReversiGame reversiGame = new ReversiGame(view);

        view.installLogic(reversiGame);

        Checkerboard checkerboard = reversiGame.getCheckerboard();
        checkerboard.markAllowedPlacings(Checkerboard.B);

        view.displayCheckerboard();

        String expectedOutput = LINE + WHITE + "  |A|B|C|D|E|F|G|H" + END + WHITE + "|\n" +
                LINE + "1 | | | | | | | | " + END + WHITE  + "|\n" +
                LINE + "2 | | | | | | | | " + END + WHITE + "|\n" +
                LINE + "3 | | | |" + GREEN + "x" + WHITE + "| | | | " + END + WHITE + "|\n" +
                LINE + "4 | | |" + GREEN + "x" + WHITE + "|o|" + RED + "o" + WHITE + "| | | " + END + WHITE + "|\n" +
                LINE + "5 | | | |" + RED + "o" + WHITE + "|o|" + GREEN + "x" + WHITE + "| | " + END + WHITE + "|\n" +
                LINE + "6 | | | | |" + GREEN + "x" + WHITE + "| | | " + END + WHITE + "|\n" +
                LINE + "7 | | | | | | | | " + END + WHITE + "|\n" +
                LINE + "8 | | | | | | | | " + END + WHITE + "|\n";

        assertEquals(expectedOutput, outputStream.toString());
    }
}
