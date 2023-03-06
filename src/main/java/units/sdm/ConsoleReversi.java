package units.sdm;

import java.util.Scanner;

public class ConsoleReversi implements ReversiView {

    protected Game game;

    @Override
    public void installLogic(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        Scanner scan = new Scanner(System.in);

        System.out.println("What's your name player Black?");
        String nameBlack;
        nameBlack = scan.next();
        game.getPlayerBlack().setName(nameBlack);

        System.out.println("What's your name player White?");
        String nameWhite;
        nameWhite = scan.next();
        game.getPlayerWhite().setName(nameWhite);

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

        System.out.print("   ");
        for (int i = 0; i < Checkerboard.SIZE; i++) {
            System.out.print("[" + (char) (i + 65) + "]");
        }
        System.out.print("\n");

        for (int i = 0; i < Checkerboard.SIZE; i++) {
            System.out.print("[" + (i + 1) + "]");
            for (int j = 0; j < Checkerboard.SIZE; j++) {
                currentValue = switch (checkerboard.getCheckerboard()[i][j]) {
                    case Checkerboard.W -> "[W]";
                    case Checkerboard.B -> "[B]";
                    case Checkerboard.A -> "[A]";
                    default -> "[ ]";
                };
                System.out.print(currentValue);
            }
            System.out.print("\n");
        }
    }

    @Override
    public void displayGameOver() {
        displayCheckerboard();

        if (game.isDraw())
            System.out.println("\nThis is a Draw!");
        else
            System.out.println("\n" + game.getWinnerName() + " wins!");

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
