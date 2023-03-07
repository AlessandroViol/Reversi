package units.sdm;

public interface Game {
    void start();

    String getPlayerWhite();

    String getPlayerBlack();

    void setPlayerWhite(String name);

    void setPlayerBlack(String name);

    Checkerboard getCheckerboard();

    void turn();

    void tryPlace(int x, int y);

    void nextTurn();

    String getCurrentPlayerName();

    boolean isDraw();

    String getWinnerName();

    void validateAndTryPlace(String row, String column);
}
