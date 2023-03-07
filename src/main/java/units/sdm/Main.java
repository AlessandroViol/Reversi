package units.sdm;

public class Main {
    public static void main(String[] args) {
        ConsoleReversi view;

        if(args.length > 0 && args[0].equals("f"))
            view = new FormattedConsoleReversi();
        else
            view = new ConsoleReversi();

        ReversiGame game = new ReversiGame(view);

        view.installLogic(game);
        game.start();
    }
}