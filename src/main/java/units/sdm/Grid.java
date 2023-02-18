package units.sdm;

public class Grid {
    int size = 8;
    int grid[][] = new int[size][size];

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

        return false;

    }


    public boolean CheckDirection(int i, int j, int x, int y, int ColourTurn) {
        while (x + i > 0 && x + i < 8 && y + j > 0 && y + j < 8) { //
            if (grid[x + i][y + j] == ColourTurn) { //
                return true;

            } else if (grid[x + i][y + j] == 0) {
                return false;

            }
            x = x + i;
            y = y + j;

        }
        return false;


    }
}