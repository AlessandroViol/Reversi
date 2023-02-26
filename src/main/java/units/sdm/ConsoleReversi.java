package units.sdm;

import java.util.Scanner;

public class ConsoleReversi implements ReversiView {

    Game game;

    @Override
    public void installLogic(Game game) {
        this.game = game;
    }

    @Override
    public void show() {

        Scanner scan = new Scanner(System.in);

        System.out.println("What's your name player \u001B[31mBlack\u001B[0m?");
        String nameBlack;
        nameBlack = scan.next();
        game.getPlayerBlack().setName("\u001B[31m" + nameBlack + "\u001B[0m");

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

        String name;
        if (game.getColourTurn() == -1) name = game.getPlayerBlack().getName();
        else name = game.getPlayerWhite().getName();

        System.out.println(name + ", make your move, specify row (1-8):");
        int i = Integer.parseInt(scan.next()) - 1;

        System.out.println("Specify column (a-h):");
        int j = scan.next().toUpperCase().toCharArray()[0] - 65;

        game.tryPlace(i, j);
    }

    public void displayCheckerboard() {
        Checkerboard checkerboard = game.getCheckerboard();
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
                    case Checkerboard.A -> "\033[0;32mx\u001B[0m" + (char) 27 + "[4m";
                    default -> " ";
                };
                System.out.print("|" + currentValue);
            }
            System.out.print((char) 27 + "[0m|\n");
        }
    }

    @Override
    public void displayGameOver() {
        displayCheckerboard();
        if (game.getCheckerboard().getNumberOfWhites() > game.getCheckerboard().getNumberOfBlacks()) {
            System.out.println(game.getPlayerWhite().getName() + " wins!");
        } else {
            System.out.println(game.getPlayerBlack().getName() + " wins!");
        }

        System.out.println("White disks: " + game.getCheckerboard().getNumberOfWhites());
        System.out.println("Black disks: " + game.getCheckerboard().getNumberOfBlacks());

        System.out.println("Press any key to terminate");
        Scanner scan = new Scanner(System.in);
        scan.next();


    }

    @Override
    public void displayNoMoves() {
        System.out.println("No moves available!");
        System.out.println("Press any key to skip the turn");
        Scanner scan = new Scanner(System.in);
        scan.next();
        game.nextTurn();
    }

    @Override
    public void displayNotAllowed() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Invalid coordinates, specify new row:");
        int i = Integer.parseInt(scan.next()) - 1;

        System.out.println("Specify new column:");
        int j = scan.next().toUpperCase().toCharArray()[0] - 65;

        game.tryPlace(i, j);
    }
}
