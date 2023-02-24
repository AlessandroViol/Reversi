package units.sdm;

public class ConsoleReversi implements ReversiView {
    @Override
    public void show(Checkerboard checkerboard) {
        String currentValue;

        System.out.print((char) 27 + "[4m  ");
        for (int i = 0; i < Checkerboard.SIZE; i++) {
            System.out.print("|" + (char) (i + 65));
        }
        System.out.print((char) 27 + "[0m|\n");

        for (int i = 0; i < Checkerboard.SIZE; i++) {
            System.out.print((char) 27 + "[4m" + (i + 1) + " ");
            for (int j = 0; j < Checkerboard.SIZE; j++) {
                currentValue = switch (checkerboard.getCheckerboard()[i][j]) {
                    case Checkerboard.W -> "o";
                    case Checkerboard.B -> "\u001B[31mo\u001B[0m" + (char) 27 + "[4m";
                    case Checkerboard.A -> "x";
                    default -> " ";
                };
                System.out.print("|" + currentValue);
            }
            System.out.print((char) 27 + "[0m|\n");
        }
    }
    @Override
    public void displayGameOver(){}
    @Override
    public void noMoves(){}
    @Override
    public void displayTurn(){}
}
