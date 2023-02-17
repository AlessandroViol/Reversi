package units.sdm;
import java.util.Scanner;

public class Game {
    Player player1;
    Player player2;

    Grid grid;

    int ColourTurn = -1;

    public Game() {
        Scanner scan = new Scanner(System.in);

        System.out.print("What's your name player1?");
        String name1;
        name1 = scan.next();

        System.out.print("What's your name player2?");
        String name2;
        name2 = scan.next();


        this.player1 = new Player(name1, -1);
        this.player2 = new Player(name2, 1);

        this.grid = new Grid();

        scan.close();
    }

}
