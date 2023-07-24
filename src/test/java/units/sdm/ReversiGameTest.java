package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReversiGameTest {
    private static final int B = AbstractCheckerboard.B;
    private static final int W = AbstractCheckerboard.W;
    private static final int N = AbstractCheckerboard.N;
    private static final int A = AbstractCheckerboard.A;

    private class DummyView implements View {
        public String called;

        @Override
        public void installLogic(Game reversiGame) {
            called = "installLogic";
        }

        @Override
        public void show() {
            called = "show";
        }

        @Override
        public void displayGameStart() {
            called = "displayGameStart";
        }

        @Override
        public void displayTurn() {
            called = "displayTurn";
        }

        @Override
        public void displayGameOver() {
            called = "displayGameOver";
        }

        @Override
        public void displayNoMoves() {
            called = "displayNoMoves";
        }

        @Override
        public void displayNotAllowed() {
            called = "displayNotAllowed";
        }

        @Override
        public void displayDraw() {
            called = "displayDraw";
        }
    }

    @Test
    void setPlayerWhiteName() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.setPlayerWhite("Player1");

        assertEquals("Player1", reversiGame.getPlayerWhite());
    }

    @Test
    void setPlayerWhiteNameToEmpty() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.setPlayerWhite("");

        assertEquals("White", reversiGame.getPlayerWhite());
    }

    @Test
    void setPlayerBlackName() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.setPlayerBlack("Player2");

        assertEquals("Player2", reversiGame.getPlayerBlack());
    }

    @Test
    void setPlayerBlackNameToEmpty() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.setPlayerBlack("");

        assertEquals("Black", reversiGame.getPlayerBlack());
    }

    @Test
    void tryPlaceAllowed() {
        AbstractCheckerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        int[][] referenceCheckerboard = {
                {N, N, W, W, W, W, W, W},
                {N, N, B, W, W, W, W, W},
                {N, N, N, W, W, W, W, B},
                {N, N, W, W, W, W, B, N},
                {B, B, W, W, B, B, W, W},
                {N, W, B, W, N, N, W, N},
                {W, N, N, B, N, N, W, N},
                {N, N, N, N, B, N, N, N}};

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, checkerboard);

        reversiGame.tryPlace(2, 7);
        AbstractCheckerboard gameCheckerboard = reversiGame.getCheckerboard();
        gameCheckerboard.unmarkAllowedPlacings();

        assertArrayEquals(referenceCheckerboard, gameCheckerboard.getMatrix());
    }

    @Test
    void tryPlaceOccupied() {
        AbstractCheckerboard referenceCheckerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, referenceCheckerboard);

        reversiGame.tryPlace(0, 2);
        AbstractCheckerboard checkerboard = reversiGame.getCheckerboard();
        checkerboard.unmarkAllowedPlacings();

        assertArrayEquals(referenceCheckerboard.getMatrix(), checkerboard.getMatrix());
    }

    @Test
    void tryPlaceOutOfGrid() {
        AbstractCheckerboard referenceCheckerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, referenceCheckerboard);

        reversiGame.tryPlace(0, 9);
        reversiGame.tryPlace(9, 0);
        reversiGame.tryPlace(0, -1);
        reversiGame.tryPlace(-1, 0);

        reversiGame.tryPlace(9, 9);
        reversiGame.tryPlace(-1, -1);

        AbstractCheckerboard checkerboard = reversiGame.getCheckerboard();
        checkerboard.unmarkAllowedPlacings();

        assertArrayEquals(referenceCheckerboard.getMatrix(), checkerboard.getMatrix());
    }

    @Test
    void tryPlaceNotAllowed() {
        AbstractCheckerboard referenceCheckerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, referenceCheckerboard);

        reversiGame.tryPlace(0, 0);
        AbstractCheckerboard checkerboard = reversiGame.getCheckerboard();
        checkerboard.unmarkAllowedPlacings();

        assertArrayEquals(referenceCheckerboard.getMatrix(), checkerboard.getMatrix());
    }

    @Test
    void getCurrentPlayerBlack() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.start();
        reversiGame.turn();

        assertEquals("Black", reversiGame.getCurrentPlayerName());
    }

    @Test
    void getCurrentPlayerWhite() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.start();
        reversiGame.turn();
        reversiGame.nextTurn();

        assertEquals("White", reversiGame.getCurrentPlayerName());
    }

    @Test
    void nextTurnChangePlayer() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.start();
        reversiGame.turn();
        String initialPlayer = reversiGame.getCurrentPlayerName();
        reversiGame.nextTurn();
        String nextPlayer = reversiGame.getCurrentPlayerName();

        assertNotEquals(initialPlayer, nextPlayer);
    }

    @Test
    void endGameFullCheckerboard() {
        int[][] fullMatrix = {{W, W, W, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, B, W, B},
                {W, W, W, W, B, W, B, B},
                {W, W, B, B, B, B, B, B},
                {W, B, B, W, W, W, W, B},
                {B, B, B, W, W, W, W, B}};
        AbstractCheckerboard checkerboard = new Checkerboard(fullMatrix);

        DummyView dummy = new DummyView();
        Game game = new ReversiGame(dummy, checkerboard);

        assertTrue(game.isGameover());
    }

    @Test
    void endGameNoAllowedPlace() {
        int[][] notFullMatrix = {{W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, N},
                {W, W, W, W, W, W, N, N},
                {W, W, W, W, W, W, N, B},
                {W, W, W, W, W, W, W, N},
                {W, W, W, W, W, W, W, W}};
        AbstractCheckerboard checkerboard = new Checkerboard(notFullMatrix);

        DummyView dummy = new DummyView();
        Game game = new ReversiGame(dummy, checkerboard);

        assertTrue(game.isGameover());
    }

    @Test
    void endGameBothHaveAllowedPlaces() {
        AbstractCheckerboard checkerboard = new Checkerboard();


        DummyView dummy = new DummyView();
        Game game = new ReversiGame(dummy, checkerboard);

        assertFalse(game.isGameover());
    }

    @Test
    void endGameWhiteHasAllowedPlace() {
        int[][] whiteHasAPMatrix = {{B, B, B, B, B, B, B, W},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        AbstractCheckerboard checkerboard = new Checkerboard(whiteHasAPMatrix);

        DummyView dummy = new DummyView();
        Game game = new ReversiGame(dummy, checkerboard);

        assertFalse(game.isGameover());
    }

    @Test
    void endGameBlackHasAllowedPlace() {
        int[][] blackHasAPMatrix = {{B, B, B, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, B, B, W, B},
                {B, W, B, B, W, W, W, B},
                {B, W, B, W, W, B, W, B},
                {B, W, B, W, B, W, W, B},
                {W, B, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};
        AbstractCheckerboard checkerboard = new Checkerboard(blackHasAPMatrix);

        DummyView dummy = new DummyView();
        Game game = new ReversiGame(dummy, checkerboard);

        assertFalse(game.isGameover());
    }

    @Test
    void isDraw() {

        int[][] drawCheckerboard = {{W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {B, B, B, B, B, B, B, B},
                {B, B, B, B, B, B, B, B},
                {B, B, B, B, B, B, B, B},
                {W, W, W, W, B, B, B, B},
                {B, B, B, B, W, W, W, W}};

        AbstractCheckerboard checkerboard = new Checkerboard(drawCheckerboard);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, checkerboard);

        assertTrue(reversiGame.isDraw());
    }

    @Test
    void isNotDraw() {

        AbstractCheckerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, checkerboard);

        assertFalse(reversiGame.isDraw());

    }

    @Test
    void getWhiteWinnerName() {

        AbstractCheckerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, checkerboard);

        assertEquals("White", reversiGame.getWinnerName());
    }

    @Test
    void getBlackWinnerName() {

        int[][] grid = {{W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {W, B, B, B, B, B, B, W},
                {B, B, B, B, B, B, B, B},
                {B, B, B, B, B, B, B, B},
                {B, B, B, B, B, B, B, B},
                {W, W, W, W, B, B, B, B},
                {B, B, B, B, W, W, W, W}};

        AbstractCheckerboard checkerboard = new Checkerboard(grid);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, checkerboard);

        assertEquals("Black", reversiGame.getWinnerName());
    }

    @Test
    void startCallsShow() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.start();

        assertEquals("show", dummy.called);
    }

    @Test
    void turnGameOver() {
        int[][] fullMatrix = {{W, W, W, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, B, W, W, W, W, W},
                {W, W, W, W, W, B, W, B},
                {W, W, W, W, B, W, B, B},
                {W, W, B, B, B, B, B, B},
                {W, B, B, W, W, W, W, B},
                {B, B, B, W, W, W, W, B}};

        AbstractCheckerboard checkerboard = new Checkerboard(fullMatrix);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, checkerboard);
        reversiGame.turn();

        assertEquals("displayGameOver", dummy.called);
    }

    @Test
    void turnDraw() {
        int[][] drawMatrix = {{W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {B, B, B, B, B, B, B, B},
                {B, B, B, B, B, B, B, B},
                {B, B, B, B, B, B, B, B},
                {W, W, W, W, B, B, B, B},
                {B, B, B, B, W, W, W, W}};

        AbstractCheckerboard checkerboard = new Checkerboard(drawMatrix);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, checkerboard);
        reversiGame.turn();

        assertEquals("displayDraw", dummy.called);
    }

    @Test
    void turnNoMoves() {
        int[][] blackNoMovesMatrix = {{W, W, B, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, W, W, B, B, W, B},
                {B, W, B, W, W, W, W, B},
                {B, W, B, W, W, W, W, W},
                {B, W, B, W, B, W, W, B},
                {W, W, W, W, W, W, W, N},
                {B, B, B, B, B, B, B, N}};

        AbstractCheckerboard checkerboard = new Checkerboard(blackNoMovesMatrix);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, checkerboard);
        reversiGame.turn();

        assertEquals("displayNoMoves", dummy.called);
    }

    @Test
    void turnPlayed() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.turn();

        assertEquals("displayTurn", dummy.called);
    }

    @Test
    void turnAddsAllowed() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.turn();

        assertEquals("displayTurn", dummy.called);
    }

    @Test
    void validateAndTryPlaceEmptyRow() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.validateAndTryPlace("", "A");

        assertEquals("displayNotAllowed", dummy.called);
    }

    @Test
    void validateAndTryPlaceWrongRowFormat() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.validateAndTryPlace("TEST", "A");

        assertEquals("displayNotAllowed", dummy.called);
    }

    @Test
    void validateAndTryPlaceEmptyColumn() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.validateAndTryPlace("1", "");

        assertEquals("displayNotAllowed", dummy.called);
    }

    @Test
    void validateAndTryPlaceWrongColumnFormat() {
        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy);

        reversiGame.validateAndTryPlace("1", "TEST");

        assertEquals("displayNotAllowed", dummy.called);
    }
}
