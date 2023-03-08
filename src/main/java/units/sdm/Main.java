package units.sdm;

public class Main {
    public static void main(String[] args) {
        ReversiView view;

        if(args.length > 0 && args[0].equals("f"))
            view = new FormattedConsoleReversi();
        else if (args.length > 0 && args[0].equals("c"))
            view = new ConsoleReversi();
        else
            view = new SwingReversi();

        ReversiGame game = new ReversiGame(view);

        view.installLogic(game);
        game.start();
    }
}