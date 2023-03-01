package units.sdm;

public class Checkerboard {
    public final static int SIZE = 8;
    private int[][] checkerboard;

    private int numberOfWhites;
    private int numberOfBlacks;

    //Values coding the values of the black and white pieces and empty and allowed places on the checkerboard
    public final static int B = 1;
    public final static int W = -1;
    public final static int N = 0;
    public final static int A = 2;


    //zero argument constructor that initialize the default checkerboard for reversi
    public Checkerboard() {
        checkerboard = new int[SIZE][SIZE];

        checkerboard[3][3] = W;
        checkerboard[4][3] = B;
        checkerboard[3][4] = B;
        checkerboard[4][4] = W;
    }

    //one argument constructor that copies the matrix of a given checkerboard
    public Checkerboard(int[][] checkerboard) {
        this.checkerboard = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                this.checkerboard[i][j] = checkerboard[i][j];
    }

    //attribute getters for class attributes
    public int[][] getCheckerboard() {
        return this.checkerboard;
    }

    public int getNumberOfWhites() {
        return numberOfWhites;
    }

    public int getNumberOfBlacks() {
        return numberOfBlacks;
    }

    //computes if a piece of the provided color can be placed in the specified square of the checkerboard
    public boolean allowPlace(int posX, int posY, int colorTurn) {
        //check that the specified square is empty
        if (checkerboard[posX][posY] != N && checkerboard[posX][posY] != A)
            return false;

        //look at the adjacent squares in different directions using vertical and horizontal offsets
        for (int offsetX = -1; offsetX < 2; offsetX++) {
            //check that the vertical adjacent squares are in-bound
            if (posX + offsetX >= 0 && posX + offsetX < SIZE) {
                for (int offsetY = -1; offsetY < 2; offsetY++) {
                    //skip if looking at the same square
                    if (offsetX == 0 && offsetY == 0)
                        continue;

                    //check that the horizontal adjacent squares are in bound
                    if (posY + offsetY >= 0 && posY + offsetY < SIZE) {
                        //if the adjacent square has a disk of opposite color checks if there is a full line of disks ending with a disk of the same color
                        if (checkerboard[posX + offsetX][posY + offsetY] == -colorTurn) {
                            if (checkDirection(offsetX, offsetY, posX + offsetX, posY + offsetY, colorTurn)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        //if none of the adjacent squares lead to available lines return false
        return false;

    }

    //check if in the specified direction there is a contiguous line of disks of the same color terminating with a disk of the opposite color
    private boolean checkDirection(int offsetX, int offsetY, int posX, int posY, int colourTurn) {
        //move along the squares in the specified direction while in-bound
        while (posX + offsetX >= 0 && posX + offsetX < SIZE && posY + offsetY >= 0 && posY + offsetY < SIZE) {
            //if a disk of the original colour is encountered then in this direction there is an allowed line, so return true
            if (checkerboard[posX + offsetX][posY + offsetY] == colourTurn) {
                return true;
            }
            //if an empty or allowed square is encountered then in this direction there isn't an allowed line, so return false
            else if (checkerboard[posX + offsetX][posY + offsetY] == N || checkerboard[posX + offsetX][posY + offsetY] == A) {
                return false;
            }
            //look at the next square
            posX = posX + offsetX;
            posY = posY + offsetY;
        }
        //when the border is reached it means this is not an allowed direction and return false
        return false;
    }

    //if the specified placement is allowed then place the disk and update the checkerboard
    protected boolean place(int posX, int posY, int colourTurn) {

        if (allowPlace(posX, posY, colourTurn)) {
            checkerboard[posX][posY] = colourTurn;
            updateCheckerboard(posX, posY, colourTurn);
        }
        else
            return false;

        return true;
    }

    //after a placement swap the color of all the adjacent disks of all the allowed lines
    protected void updateCheckerboard(int posX, int posY, int colourTurn) {
        //look at the adjacent squares in different directions using vertical and horizontal offsets
        for (int offsetX = -1; offsetX < 2; offsetX++) {
            //check that the vertical adjacent squares are in-bound
            if (posX + offsetX >= 0 && posX + offsetX < SIZE) {
                for (int offsetY = -1; offsetY < 2; offsetY++) {
                    //skip if looking at the same square
                    if (offsetX == 0 && offsetY == 0)
                        continue;

                    //check that the horizontal adjacent squares are in-bound
                    if (posY + offsetY >= 0 && posY + offsetY < SIZE) {
                        //check if the adjacent square is of different colour
                        if (checkerboard[posX + offsetX][posY + offsetY] == -colourTurn) {
                            //if in that direction there is an allowed line then swap all the disks between the two extremes
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
            }
        }
    }

    //return true if there is at least one possible move for the player, return false otherwise
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

    //refresh the counter of the placed disks of each color
    public void disksCount() {

        numberOfBlacks = 0;
        numberOfWhites = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkerboard[i][j] == W) {
                    numberOfWhites += 1;
                } else if (checkerboard[i][j] == B) {
                    numberOfBlacks += 1;
                }
            }
        }
    }

    //marks all the allowed squares for a disk color on the checkerboard
    protected void addAllowedDisks(int colourTurn) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (allowPlace(i, j, colourTurn)) {
                    checkerboard[i][j] = A;
                }
            }
        }
    }

    //removes the marking of all the allowed squares
    protected void removeAllowedDisks() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (checkerboard[i][j] == A) checkerboard[i][j] = N;

    }

    //return true if neither players have available placings and false otherwise
    public boolean gameOver() {
        if (!existAllowedPlace(B) && !existAllowedPlace(W)) {
            return true;
        }
        return false;
    }

}