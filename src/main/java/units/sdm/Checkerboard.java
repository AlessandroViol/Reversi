package units.sdm;

public class Checkerboard {
    final static int SIZE = 8;
    int[][] checkerboard;

    int NumberOfWhites;
    int NumberOfBlacks;

    public final static int B = 1;
    public final static int W = -1;
    public final static int N = 0;
    public final static int A = 2;


    public Checkerboard() {
        checkerboard = new int[SIZE][SIZE];

        checkerboard[3][3] = W;
        checkerboard[4][3] = B;
        checkerboard[3][4] = B;
        checkerboard[4][4] = W;
    }

    public Checkerboard(int[][] checkerboard) {
        this.checkerboard = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                this.checkerboard[i][j] = checkerboard[i][j];
    }

    public boolean allowPlace(int posX, int posY, int colorTurn) {
        if (checkerboard[posX][posY] != N && checkerboard[posX][posY] != A) return false;

        for (int offsetX = -1; offsetX < 2; offsetX++) {
            if (posX + offsetX < 0 || posX + offsetX > SIZE - 1) {
                continue;
            }
            for (int offsetY = -1; offsetY < 2; offsetY++) {
                if (offsetX == 0 && offsetY == 0) continue;
                if (posY + offsetY < 0 || posY + offsetY > SIZE - 1) {
                    continue;
                }
                if (checkerboard[posX + offsetX][posY + offsetY] == -colorTurn) {
                    if (checkDirection(offsetX, offsetY, posX + offsetX, posY + offsetY, colorTurn)) {
                        return true;

                    }
                }

            }
        }

        return false;

    }

    public boolean checkDirection(int offsetX, int offsetY, int posX, int posY, int colourTurn) {
        while (posX + offsetX >= 0 && posX + offsetX < SIZE && posY + offsetY >= 0 && posY + offsetY < SIZE) { //

            if (checkerboard[posX + offsetX][posY + offsetY] == colourTurn) { //
                return true;

            } else if (checkerboard[posX + offsetX][posY + offsetY] == N || checkerboard[posX + offsetX][posY + offsetY] == A) {
                return false;

            }
            posX = posX + offsetX;
            posY = posY + offsetY;

        }
        return false;
    }

    public boolean place(int posX, int posY, int colourTurn) {

        if (allowPlace(posX, posY, colourTurn)) {
            checkerboard[posX][posY] = colourTurn;
            updateCheckerboard(posX, posY, colourTurn);
        } else return false;

        return true;
    }

    public void updateCheckerboard(int posX, int posY, int colourTurn) {

        for (int offsetX = -1; offsetX < 2; offsetX++) {
            if (posX + offsetX < 0 || posX + offsetX > SIZE - 1) {
                continue;
            }
            for (int offsetY = -1; offsetY < 2; offsetY++) {
                if (offsetX == 0 && offsetY == 0) continue;

                if (posY + offsetY < 0 || posY + offsetY > SIZE - 1) {
                    continue;
                }
                if (checkerboard[posX + offsetX][posY + offsetY] == -colourTurn) {
                    if (checkDirection(offsetX, offsetY, posX + offsetX, posY + offsetY, colourTurn)) {
                        int xTemp = posX;
                        int yTemp = posY;
                        while (checkerboard[xTemp + offsetX][yTemp + offsetY] == -colourTurn) {
                            checkerboard[xTemp + offsetX][yTemp + offsetY] = colourTurn;
                            xTemp = xTemp + offsetX;
                            yTemp = yTemp + offsetY;
                        }
                    }
                }
            }
        }
        return;
    }

    public boolean existAllowedPlace(int colourTurn) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (allowPlace(i, j, colourTurn)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void disksCount() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkerboard[i][j] == W) {
                    NumberOfWhites += 1;
                } else if (checkerboard[i][j] == B) {
                    NumberOfBlacks += 1;
                }
            }
        }

    }

    public void addAllowedDisks(int colourTurn) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (allowPlace(i, j, colourTurn)) {
                    checkerboard[i][j] = A;
                }
            }
        }

    }

    public void removeAllowedDisks() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (checkerboard[i][j] == A) checkerboard[i][j] = N;

    }


    public boolean gameOver() {
        if (!existAllowedPlace(B) && !existAllowedPlace(W)) {
            return true;
        }
        return false;
    }

}