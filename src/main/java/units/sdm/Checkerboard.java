package units.sdm;

public class Checkerboard {
    final int SIZE = 8;
    int [][] checkerboard;

    int NumberOfWhites;
    int NumberOfBlacks;


    public Checkerboard() {
        checkerboard = new int[SIZE][SIZE];

        checkerboard[3][3] = -1;
        checkerboard[4][3] = 1;
        checkerboard[3][4] = 1;
        checkerboard[4][4] = -1;
    }

    public Checkerboard(int[][] checkerboard) {
        this.checkerboard = checkerboard;
    }

    public boolean allowPlace(int posX, int posY, int colorTurn) {

        if (checkerboard[posX][posY] != 0) return false;

        for (int offsetX = -1; offsetX < 2; offsetX++) {
            if (posX + offsetX < 0 || posX + offsetX > 7) {
                continue;
            }
            for (int offsetY = -1; offsetY < 2; offsetY++) {
                if (offsetX == 0 && offsetY == 0) continue;

                if (posY + offsetY < 0 || posY + offsetY > 7) {
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
        while (posX + offsetX > 0 && posX + offsetX < 8 && posY + offsetY > 0 && posY + offsetY < 8) { //
            if (checkerboard[posX + offsetX][posY + offsetY] == colourTurn) { //
                return true;

            } else if (checkerboard[posX + offsetX][posY + offsetY] == 0 || checkerboard[posX + offsetX][posY + offsetY] == 2) {
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
            if (posX + offsetX < 0 || posX + offsetX > 7) {
                continue;
            }
            for (int offsetY = -1; offsetY < 2; offsetY++) {
                if (offsetX == 0 && offsetY == 0) continue;

                if (posY + offsetY < 0 || posY + offsetY > 7) {
                    continue;
                }
                if (checkerboard[posX + offsetX][posY + offsetY] == -colourTurn) {
                    if (checkDirection(offsetX, offsetY, posX + offsetX, posY + offsetY, colourTurn)) {
                        int xTemp = posX;
                        int yTemp = posY;
                        while (checkerboard[xTemp + offsetX][yTemp + offsetY] == -colourTurn) {
                            checkerboard[xTemp + offsetX][yTemp + offsetY] = colourTurn;
                            xTemp = posX + offsetX;
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkerboard[i][j] == -1) {
                    NumberOfWhites += 1;
                } else if (checkerboard[i][j] == 1) {
                    NumberOfBlacks += 1;
                }
            }
        }

    }

    public void addAllowedDisks(int colourTurn) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (allowPlace(i, j, colourTurn)) {
                    checkerboard[i][j] = 2;
                }
            }
        }

    }

    public void removeAllowedDisks() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (checkerboard[i][j] == 2) checkerboard[i][j] = 0;

    }

}

