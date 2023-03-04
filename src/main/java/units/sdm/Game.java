package units.sdm;

public interface Game {
    public void start();

    public int getColourTurn();

    public Player getPlayerWhite();

    public Player getPlayerBlack();

    public Checkerboard getCheckerboard();

    public void turn();

    public void tryPlace(int x, int y);

    public void nextTurn();

    public String getCurrentPlayerName();

    public boolean isDraw();

    public String getWinnerName();

    public void validateAndTryPlace(String row, String column);
}
