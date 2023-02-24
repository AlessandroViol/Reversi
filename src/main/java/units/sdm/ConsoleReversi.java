package units.sdm;

import java.util.Scanner;

public class ConsoleReversi implements ReversiView {

    Game game;

    @Override
    public void installLogic(Game game){
        this.game=game;
    }
    @Override
    public void show(){

        Scanner scan = new Scanner(System.in);

        System.out.print("What's your name player1?");
        String nameWhite;
        nameWhite = scan.next();
        game.getPlayerWhite().setName(nameWhite);

        System.out.print("What's your name player2?");
        String nameBlack;
        nameBlack = scan.next();
        game.getPlayerBlack().setName(nameBlack);

        System.out.print("Are you ready?");

        String start=scan.next();

        game.turn();
        
    }

    @Override
    public void displayTurn(Checkerboard checkerboard) {
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
}
