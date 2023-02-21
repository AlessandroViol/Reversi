package units.sdm;

public class ConsoleReversi implements ReversiView {
    @Override
    public void show(Checkerboard checkerboard) {
        String currentValue;

        System.out.print((char) 27 + "[4m  ");
        for (int i = 0; i < checkerboard.SIZE; i++) {
            System.out.print("|" + (char) (i + 65));
        }
        System.out.print((char) 27 + "[0m|\n");

        for (int i = 0; i < checkerboard.SIZE; i++) {
            System.out.print((char) 27 + "[4m" + (i + 1) + " ");
            for (int j = 0; j < checkerboard.SIZE; j++) {
                switch (checkerboard.checkerboard[i][j]) {
                    case -1:
                        currentValue = "o";
                        break;
                    case 1:
                        currentValue = "\u001B[31mo\u001B[0m" + (char) 27 + "[4m";
                        break;
                    default:
                        currentValue = " ";
                        break;
                }
                System.out.print("|" + currentValue);
            }
            System.out.print((char) 27 + "[0m|\n");
        }
    }
}
