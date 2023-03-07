package units.sdm;

public interface Game {
    public void start();

    public int getColourTurn();

    public String getPlayerWhite();

    public String getPlayerBlack();

    public void setPlayerWhite(String name);

    public void setPlayerBlack(String name);

    public Checkerboard getCheckerboard();

    public void turn();

    public void tryPlace(int x, int y);

    public void nextTurn();

    public String getCurrentPlayerName();

    public boolean isDraw();

    public String getWinnerName();

    public void validateAndTryPlace(String row, String column);
}
