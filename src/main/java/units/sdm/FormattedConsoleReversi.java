package units.sdm;

import java.util.Scanner;

public class FormattedConsoleReversi extends ConsoleReversi{
    private final String BOLD =(char) 27 + "[1m";
    private final String LINE =(char) 27 + "[4m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\033[0;32m";
    private final String END =(char) 27 + "[0m";

    @Override
    public void show() {
        Scanner scan = new Scanner(System.in);

        System.out.println("What's your name player " + RED + BOLD + "Black" + END + "?");
        String nameBlack;
        nameBlack = scan.next();
        game.getPlayerBlack().setName(RED + BOLD + nameBlack + END);

        System.out.println("What's your name player " + BOLD + "White" + END + "?");
        String nameWhite;
        nameWhite = scan.next();
        game.getPlayerWhite().setName(BOLD + nameWhite + END);

        System.out.println("Are you ready?");

        scan.next();

        game.turn();
    }

    @Override
    public void displayCheckerboard() {
        Checkerboard checkerboard = game.getCheckerboard();
        String currentValue;

        System.out.print(LINE + "  ");
        for (int i = 0; i < Checkerboard.SIZE; i++) {
            System.out.print("|" + (char) (i + 65));
        }
        System.out.print(END + "|\n");

        for (int i = 0; i < Checkerboard.SIZE; i++) {
            System.out.print(LINE + (i + 1) + " ");
            for (int j = 0; j < Checkerboard.SIZE; j++) {
                currentValue = switch (checkerboard.getCheckerboard()[i][j]) {
                    case Checkerboard.W -> "o";
                    case Checkerboard.B -> RED + "o" + END + LINE;
                    case Checkerboard.A -> GREEN + "x" + END + LINE;
                    default -> " ";
                };
                System.out.print("|" + currentValue);
            }
            System.out.print(END + "|\n");
        }
    }

    @Override
    public void displayGameOver() {
        displayCheckerboard();
        System.out.println("\n" + game.getWinnerName() + BOLD + " wins!" + END);

        displayResults();
    }

    @Override
    public void displayDraw() {
        displayCheckerboard();
        System.out.println("\nThis is a " + BOLD + "Draw!" + END);

        displayResults();
    }
}
