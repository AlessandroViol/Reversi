package units.sdm;

public class ReversiGame implements Game {
    private Player playerWhite;
    private Player playerBlack;

    private Checkerboard checkerboard;

    private ReversiView view;

    private int colourTurn = 1;

    public ReversiGame(ReversiView view) {
        this.playerBlack = new Player("Black", Checkerboard.B);
        this.playerWhite = new Player("White", Checkerboard.W);

        this.checkerboard = new Checkerboard();

        this.view = view;
    }

    public ReversiGame(ReversiView view, Checkerboard checkerboard) {
        this.playerBlack = new Player("Black", Checkerboard.B);
        this.playerWhite = new Player("White", Checkerboard.W);

        this.checkerboard = new Checkerboard(checkerboard.getCheckerboard());

        this.view = view;
    }

    @Override
    public void start() {
        view.show();
    }

    @Override
    public int getColourTurn() {
        return colourTurn;
    }

    @Override
    public Player getPlayerWhite() {
        return playerWhite;
    }

    @Override
    public Player getPlayerBlack() {
        return playerBlack;
    }

    @Override
    public Checkerboard getCheckerboard() {
        return checkerboard;
    }

    @Override
    public void turn() {
        if (checkerboard.gameOver())
            view.displayGameOver();
        else {
            if (!checkerboard.existAllowedPlace(colourTurn)) {
                view.displayNoMoves();
                return;
            }
            checkerboard.addAllowedDisks(colourTurn);
            view.displayTurn(checkerboard);
        }
    }

    @Override
    public void tryPlace(int x, int y) {
        if (x >= 0 && x < Checkerboard.SIZE && y >= 0 && y < Checkerboard.SIZE && checkerboard.allowPlace(x, y, colourTurn)) {
            checkerboard.place(x, y, colourTurn);
            checkerboard.disksCount();
            nextTurn();
        } else
            view.displayNotAllowed();
    }

    @Override
    public void nextTurn() {
        colourTurn = -colourTurn;
        checkerboard.removeAllowedDisks();
        turn();
    }

    @Override
    public String getCurrentPlayerName() {
        if (colourTurn == Checkerboard.B) {
            return playerBlack.getName();
        }
        return playerWhite.getName();
    }

    @Override
    public boolean isDraw() {
        return checkerboard.getNumberOfBlacks() == checkerboard.getNumberOfWhites();
    }

    @Override
    public String getWinnerName() {
        if (checkerboard.getNumberOfBlacks() > checkerboard.getNumberOfWhites()) {
            return playerBlack.getName();
        }
        return playerWhite.getName();
    }

    @Override
    public void validateAndTryPlace(String row, String column) {
        int rowIndex;
        try {
            rowIndex = Integer.parseInt(row) - 1;
        } catch (NumberFormatException ex) {
            view.displayNotAllowed();
            return;
        }
        int columnIndex = column.toUpperCase().toCharArray()[0] - 65;

        tryPlace(rowIndex, columnIndex);
    }
}
