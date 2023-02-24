package units.sdm;

public interface ReversiView {
    void show(Checkerboard checkerboard);

    void displayGameOver();
    void noMoves();
    void displayTurn();
}
