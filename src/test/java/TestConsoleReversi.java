import org.junit.jupiter.api.Test;
import units.sdm.ConsoleReversi;
import units.sdm.Grid;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConsoleReversi {
    @Test
    void consoleOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ConsoleReversi view = new ConsoleReversi();
        view.show(new Grid());

        String expectedOutput = "  |A|B|C|D|E|F|G|H|\n" +
                                "1 | | | | | | | | |\n" +
                                "2 | | | | | | | | |\n" +
                                "3 | | | | | | | | |\n" +
                                "4 | | | |w|b| | | |\n" +
                                "5 | | | |b|w| | | |\n" +
                                "6 | | | | | | | | |\n" +
                                "7 | | | | | | | | |\n" +
                                "8 | | | | | | | | |\n";

        assertEquals(expectedOutput, outputStream.toString());
    }
}
