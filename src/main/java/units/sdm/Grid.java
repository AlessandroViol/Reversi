package units.sdm;

public class Grid {
    int size = 8;
    int grid[][] = new int[size][size];

    int NumberOfWhites;
    int NumberOfBlacks;


    public Grid() {

        grid[3][3] = -1;
        grid[4][3] = 1;
        grid[3][4] = 1;
        grid[4][4] = -1;


    }

    public Grid(int[][] grid) {
        this.grid = grid;
    }

    public boolean AllowMove(int x, int y, int ColourTurn) {

        if (grid[x][y] != 0) return false;

        for (int i = -1; i < 2; i++) {
            if (x + i < 0 || x + i > 7) {
                continue;
            }
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;

                if (y + j < 0 || y + j > 7) {
                    continue;
                }
                if (grid[x + i][y + j] == -ColourTurn) {
                    if (CheckDirection(i, j, x + i, y + j, ColourTurn)) {
                        return true;

                    }
                }

            }
        }

        return false;

    }


    public boolean CheckDirection(int i, int j, int x, int y, int ColourTurn) {
        while (x + i > 0 && x + i < 8 && y + j > 0 && y + j < 8) { //
            if (grid[x + i][y + j] == ColourTurn) { //
                return true;

            } else if (grid[x + i][y + j] == 0 || grid[x + i][y + j] == 2) {
                return false;

            }
            x = x + i;
            y = y + j;

        }
        return false;

    }

    public boolean place(int x, int y, int colourTurn) {

        if (AllowMove(x, y, colourTurn)) {
            grid[x][y] = colourTurn;
            update_Grid(x, y, colourTurn);
        } else return false;

        return true;
    }

    public void update_Grid(int x, int y, int ColourTurn) {

        for (int i = -1; i < 2; i++) {
            if (x + i < 0 || x + i > 7) {
                continue;
            }
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;

                if (y + j < 0 || y + j > 7) {
                    continue;
                }
                if (grid[x + i][y + j] == -ColourTurn) {
                    if (CheckDirection(i, j, x + i, y + j, ColourTurn)) {
                        int xTemp = x;
                        int yTemp = y;
                        while (grid[xTemp + i][yTemp + j] == -ColourTurn) {
                            grid[xTemp + i][yTemp + j] = ColourTurn;
                            xTemp = x + i;
                            yTemp = yTemp + j;
                        }
                    }
                }
            }
        }
        return;
    }


    public boolean existAllowedMove(int ColourTurn) {
        if (ColourTurn == -1)
            return false;
        else
            return true;
    }

    public void PegsCount() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] == -1) {
                    NumberOfWhites += 1;
                } else if (grid[i][j] == 1) {
                    NumberOfBlacks += 1;
                }
            }
        }

    }
}

