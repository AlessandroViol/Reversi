package units.sdm;

public class ConsoleReversi implements GridView {
    @Override
    public void show(Grid grid) {
        System.out.print("  |A|B|C|D|E|F|G|H|\n" +
                        "1 | | | | | | | | |\n" +
                        "2 | | | | | | | | |\n" +
                        "3 | | | | | | | | |\n" +
                        "4 | | | |w|b| | | |\n" +
                        "5 | | | |b|w| | | |\n" +
                        "3 | | | | | | | | |\n" +
                        "3 | | | | | | | | |\n" +
                        "3 | | | | | | | | |\n");
    }
}
