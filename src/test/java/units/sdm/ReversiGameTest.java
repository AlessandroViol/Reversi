package units.sdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReversiGameTest {
    private final static int B = Checkerboard.B;
    private final static int W = Checkerboard.W;
    private final static int N = Checkerboard.N;
    private final static int A = Checkerboard.A;

    public class DummyView implements ReversiView {

        @Override
        public void installLogic(Game reversiGame) {
            return;
        }

        @Override
        public void show() {
            return;
        }

        @Override
        public void displayTurn(Checkerboard checkerboard) {
            return;
        }

        @Override
        public void displayGameOver() {
            return;
        }

        @Override
        public void displayNoMoves() {
            return;
        }

        @Override
        public void displayNotAllowed() {
            return;
        }

        @Override
        public void displayDraw() {}
    }

    @Test
    void endGameFull() {

        int[][] GameOverFull = {{W, W, W, B, B, B, B, B},
                {W, W, W, W, W, W, W, B},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, B, W, B},
                {W, W, W, W, B, W, B, B},
                {W, W, B, B, B, B, B, B},
                {W, B, B, W, W, W, W, B},
                {B, B, B, W, W, W, W, B}};

        Checkerboard checkerboard = new Checkerboard(GameOverFull);
        assertTrue(checkerboard.gameOver());

    }

    @Test
    void endGame() {

        int[][] GameOverNotFull = {{W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, N},
                {W, W, W, W, W, W, N, N},
                {W, W, W, W, W, W, N, B},
                {W, W, W, W, W, W, W, N},
                {W, W, W, W, W, W, W, W}};

        Checkerboard checkerboard = new Checkerboard(GameOverNotFull);
        assertTrue(checkerboard.gameOver());
    }

    @Test
    void endGameFalse() {
        Checkerboard checkerboard = new Checkerboard();
        assertFalse(checkerboard.gameOver());
    }

    @Test
    void tryPlaceAllowed() {

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        int[][] referenceCheckerboard = {{N, N, W, W, W, W, W, W},
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
        reversiGame.getCheckerboard().removeAllowedDisks();

        assertArrayEquals(referenceCheckerboard, reversiGame.getCheckerboard().getCheckerboard());

    }

    @Test
    void tryPlaceNotAllowed() {

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, checkerboard);

        reversiGame.tryPlace(0, 2);
        reversiGame.getCheckerboard().removeAllowedDisks();

        assertArrayEquals(checkerboard.getCheckerboard(), reversiGame.getCheckerboard().getCheckerboard());

    }

    @Test
    void tryPlaceOutOfGrid() {

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, checkerboard);

        reversiGame.tryPlace(-1, 9);
        reversiGame.getCheckerboard().removeAllowedDisks();

        assertArrayEquals(checkerboard.getCheckerboard(), reversiGame.getCheckerboard().getCheckerboard());

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

        Checkerboard checkerboard = new Checkerboard(drawCheckerboard);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, checkerboard);

        reversiGame.getCheckerboard().disksCount();

        assertTrue(reversiGame.isDraw());

    }

    @Test
    void isNotDraw() {

        Checkerboard checkerboard = new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        DummyView dummy = new DummyView();
        ReversiGame reversiGame = new ReversiGame(dummy, checkerboard);

        reversiGame.getCheckerboard().disksCount();

        assertFalse(reversiGame.isDraw());

    }

    @Test
    void getWhiteWinnerName(){

        Checkerboard checkerboard=new Checkerboard(CheckerboardUtility.COMPLEX_CHECKERBOARD);

        DummyView dummy=new DummyView();
        ReversiGame reversiGame =new ReversiGame(dummy, checkerboard);

        reversiGame.getCheckerboard().disksCount();
        assertEquals(reversiGame.getWinnerName(), "White");
    }

    @Test
    void getBlackWinnerName(){

        int[][] grid = {{W, W, W, W, W, W, W, W},
                {W, W, W, W, W, W, W, W},
                {W, B, B, B, B, B, B, W},
                {B, B, B, B, B, B, B, B},
                {B, B, B, B, B, B, B, B},
                {B, B, B, B, B, B, B, B},
                {W, W, W, W, B, B, B, B},
                {B, B, B, B, W, W, W, W}};

        Checkerboard checkerboard=new Checkerboard(grid);

        DummyView dummy=new DummyView();
        ReversiGame reversiGame =new ReversiGame(dummy, checkerboard);

        reversiGame.getCheckerboard().disksCount();
        assertEquals(reversiGame.getWinnerName(), "Black");
    }

}
