package units.sdm;

import java.util.Scanner;

public class Game {
    private Player playerWhite;
    private Player playerBlack;

    private Checkerboard checkerboard;

    private ReversiView view;

    private int colourTurn = 1;

    private Game() {
        this.playerWhite = new Player("Black", Checkerboard.B);
        this.playerBlack = new Player("White", Checkerboard.W);

        this.checkerboard = new Checkerboard();
    }

    public Game(ReversiView view) {
        this();
        this.view = view;
    }

    public void start(){
        view.show();
    }

    public int getColourTurn() {
        return colourTurn;
    }

    public Player getPlayerWhite() {
        return playerWhite;
    }

    public Player getPlayerBlack() {
        return playerBlack;
    }

    public Checkerboard getCheckerboard() {
        return checkerboard;
    }


    public void turn() {
        if (checkerboard.gameOver())
            view.displayGameOver();
        else {
            if (!checkerboard.existAllowedPlace(colourTurn)) {
                view.displayNoMoves();
            }
            checkerboard.addAllowedDisks(colourTurn);
            view.displayTurn(checkerboard);
        }

    }

    public void tryPlace(int x, int y) {
        if (checkerboard.allowPlace(x, y, colourTurn)) {
            checkerboard.place(x, y, colourTurn);
            checkerboard.disksCount();
            nextTurn();
        }
    }

    public void nextTurn(){
        colourTurn = -colourTurn;
        checkerboard.removeAllowedDisks();
        turn();
    }
}
