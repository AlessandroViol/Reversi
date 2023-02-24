package units.sdm;

public interface ReversiView {

    void installLogic(Game game);

    void show();

    void displayTurn(Checkerboard checkerboard);

    void displayGameOver();

    void noMoves();
}

