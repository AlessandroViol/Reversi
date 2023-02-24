package units.sdm;

public class Main {
    public static void main(String[] args) {
        ConsoleReversi view = new ConsoleReversi();
        Game game = new Game(view);

        view.installLogic(game);
        game.start();
    }
}