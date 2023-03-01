package units.sdm;

import java.util.Scanner;

public class ConsoleReversi implements ReversiView {

    Game game;

    private final String BOLD =(char) 27 + "[1m";
    private final String LINE =(char) 27 + "[4m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\033[0;32m";
    private final String END =(char) 27 + "[0m";

    @Override
    public void installLogic(Game game) {
        this.game = game;
    }

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
    public void displayTurn(Checkerboard checkerboard) {
        displayCheckerboard();

        Scanner scan = new Scanner(System.in);

        System.out.println(game.getCurrentPlayerName() + ", make your move, specify row (1-8):");
        String row = scan.next();

        System.out.println("Specify column (a-h):");
        String column = scan.next();

        game.validateAndTryPlace(row, column);
    }

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

        if (game.isDraw())
            System.out.println("\nThis is a " + BOLD + "Draw!" + END);
        else
            System.out.println("\n" + game.getWinnerName() + BOLD + " wins!" + END);

        System.out.println(game.getPlayerBlack().getName() + " disks: " + game.getCheckerboard().getNumberOfBlacks());
        System.out.println(game.getPlayerWhite().getName() + " disks: " + game.getCheckerboard().getNumberOfWhites());

        System.out.println("Press any key to terminate");
        Scanner scan = new Scanner(System.in);
        scan.next();
    }

    @Override
    public void displayNoMoves() {
        displayCheckerboard();
        System.out.println(game.getCurrentPlayerName() + " no moves available!");
        System.out.println("Press any key to skip the turn");
        Scanner scan = new Scanner(System.in);
        scan.next();
        game.nextTurn();
    }

    @Override
    public void displayNotAllowed() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Invalid coordinates, specify new row (1-8):");
        String row = scan.next();

        System.out.println("Specify new column (a-h):");
        String column = scan.next();

        game.validateAndTryPlace(row, column);
    }
}
