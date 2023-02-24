package units.sdm;
import java.util.Scanner;

public class Game {
    private Player playerWhite;
    private Player playerBlack;

    private Checkerboard checkerboard;

    private ReversiView view;

    private int colourTurn = -1;

    private Game() {
        Scanner scan = new Scanner(System.in);

        System.out.print("What's your name player1?");
        String nameWhite;
        nameWhite = scan.next();

        System.out.print("What's your name player2?");
        String nameBlack;
        nameBlack = scan.next();


        this.playerWhite = new Player(nameWhite, Checkerboard.W);
        this.playerBlack = new Player(nameBlack, Checkerboard.B);

        this.checkerboard = new Checkerboard();

        scan.close();
    }

    public Game(ReversiView view) {
        this();
        this.view = view;

        view.displayTurn(checkerboard);
    }

    public int getColourTurn(){
        return colourTurn;
    }

    public Player getPlayerWhite(){
        return playerWhite;
    }

    public Player getPlayerBlack(){
        return playerBlack;
    }

    public Checkerboard getCheckerboard(){
        return checkerboard;
    }


    public void turn(){
        if(checkerboard.gameOver())
            view.displayGameOver();
        else{
            if(checkerboard.existAllowedPlace(colourTurn)){
                view.noMoves();
            }
            checkerboard.addAllowedDisks(colourTurn);
            view.displayTurn();
        }



    }

}
