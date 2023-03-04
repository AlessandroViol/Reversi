package units.sdm;

public class Main {
    public static void main(String[] args) {
        ConsoleReversi view = new ConsoleReversi();
        ReversiGame game = new ReversiGame(view);

        view.installLogic(game);
        game.start();
    }
}