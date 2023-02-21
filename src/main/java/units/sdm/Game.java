package units.sdm;
import java.util.Scanner;

public class Game {
    Player playerWhite;
    Player playerBlack;

    Checkerboard checkerboard;

    ReversiView view;

    int colourTurn = -1;

    public Game() {
        Scanner scan = new Scanner(System.in);

        System.out.print("What's your name player1?");
        String nameWhite;
        nameWhite = scan.next();

        System.out.print("What's your name player2?");
        String nameBlack;
        nameBlack = scan.next();


        this.playerWhite = new Player(nameWhite, -1);
        this.playerBlack = new Player(nameBlack, 1);

        this.checkerboard = new Checkerboard();

        scan.close();
    }

    public Game(ReversiView view) {
        this();
        this.view = view;

        view.show(checkerboard);
    }

}
