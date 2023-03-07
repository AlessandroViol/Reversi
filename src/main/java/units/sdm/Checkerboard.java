package units.sdm;

public class Checkerboard {
    public static final int SIZE = 8;
    private int[][] matrix;

    private int numberOfWhites;
    private int numberOfBlacks;

    //Values coding the values of the black and white pieces and empty and allowed places on the checkerboard
    public static final int B = 1;
    public static final int W = -1;
    public static final int N = 0;
    public static final int A = 2;


    //zero argument constructor that initialize the default checkerboard for reversi
    public Checkerboard() {
        matrix = new int[SIZE][SIZE];

        matrix[3][3] = W;
        matrix[4][3] = B;
        matrix[3][4] = B;
        matrix[4][4] = W;
    }

    //one argument constructor that copies the matrix of a given checkerboard
    public Checkerboard(int[][] matrix) throws InvalidSquareValueException {
        this.matrix = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int squareVal = matrix[i][j];
                if (squareVal != W && squareVal != B && squareVal != N && squareVal != A)
                    throw new InvalidSquareValueException(squareVal);
                else
                    this.matrix[i][j] = squareVal;
            }
        }
    }

    public static class InvalidSquareValueException extends RuntimeException {
        public InvalidSquareValueException(int value) {
            super("Tried to write value [" + value + "] into a Checkerboard object. The only allowed values are: " +
                    "{" + W + "; " + B + "; " + N + "; " + A + "}");
        }
    }

    //attribute getters for class attributes
    public int[][] getMatrix() {
        return this.matrix;
    }

    public int getNumberOfWhites() {
        return numberOfWhites;
    }

    public int getNumberOfBlacks() {
        return numberOfBlacks;
    }

    //computes if a piece of the provided color can be placed in the specified square of the checkerboard
    public boolean allowPlace(int posX, int posY, int colorTurn) {
        //declaration of some conditionals regarding current adjacent square
        boolean isInVerticalBounds;
        boolean isInHorizontalBounds;
        boolean isSameSquare;
        boolean isOppositeColor;
        boolean isDirectionAllowed;

        //check that the specified square is empty
        if (matrix[posX][posY] != N && matrix[posX][posY] != A)
            return false;

        //look at the adjacent squares in different directions using vertical and horizontal offsets
        for (int offsetX = -1; offsetX < 2; offsetX++) {
            isInVerticalBounds = (posX + offsetX >= 0) && (posX + offsetX < SIZE);

            if (isInVerticalBounds) {
                for (int offsetY = -1; offsetY < 2; offsetY++) {
                    isSameSquare = (offsetX == 0) && (offsetY == 0);
                    isInHorizontalBounds = (posY + offsetY >= 0) && (posY + offsetY < SIZE);

                    if ((!isSameSquare) && isInHorizontalBounds) {
                        isOppositeColor = matrix[posX + offsetX][posY + offsetY] == -colorTurn;
                        isDirectionAllowed = checkDirection(offsetX, offsetY, posX + offsetX, posY + offsetY, colorTurn);

                        if (isOppositeColor && isDirectionAllowed)
                            return true;
                    }
                }
            }
        }

        //if none of the adjacent squares lead to available lines return false
        return false;
    }

    //check if in the specified direction there is a contiguous line of disks of the same color terminating with a disk of the opposite color
    private boolean checkDirection(int offsetX, int offsetY, int posX, int posY, int colourTurn) {
        //move along the squares in the specified direction while in-bound to look for a disk of the same color or an empty square
        while (posX + offsetX >= 0 && posX + offsetX < SIZE && posY + offsetY >= 0 && posY + offsetY < SIZE) {
            if (matrix[posX + offsetX][posY + offsetY] == colourTurn)
                return true;

            else if (matrix[posX + offsetX][posY + offsetY] == N || matrix[posX + offsetX][posY + offsetY] == A)
                return false;

            //look at the next square along the direction
            posX = posX + offsetX;
            posY = posY + offsetY;
        }

        //when the border is reached it means this is not an allowed direction and return false
        return false;
    }

    //if the specified placement is allowed then place the disk and update the checkerboard
    protected void place(int posX, int posY, int colourTurn) {
        if (allowPlace(posX, posY, colourTurn)) {
            matrix[posX][posY] = colourTurn;
            updateCheckerboard(posX, posY, colourTurn);
        }

    }

    //after a placement swap the color of all the adjacent disks of all the allowed lines
    protected void updateCheckerboard(int posX, int posY, int colourTurn) {
        //declaration of some conditionals regarding current adjacent square
        boolean isInVerticalBounds;
        boolean isInHorizontalBounds;
        boolean isSameSquare;
        boolean isOppositeColor;
        boolean isDirectionAllowed;

        //look at the adjacent squares in different directions using vertical and horizontal offsets
        for (int offsetX = -1; offsetX < 2; offsetX++) {
            isInVerticalBounds = (posX + offsetX >= 0) && (posX + offsetX < SIZE);
            if (isInVerticalBounds) {
                for (int offsetY = -1; offsetY < 2; offsetY++) {
                    isSameSquare = (offsetX == 0) && (offsetY == 0);
                    isInHorizontalBounds = (posY + offsetY >= 0) && (posY + offsetY < SIZE);

                    if ((!isSameSquare) && isInHorizontalBounds) {
                        isOppositeColor = matrix[posX + offsetX][posY + offsetY] == -colourTurn;
                        isDirectionAllowed = checkDirection(offsetX, offsetY, posX + offsetX, posY + offsetY, colourTurn);

                        if (isOppositeColor && isDirectionAllowed) {
                            int xTemp = posX;
                            int yTemp = posY;

                            while (matrix[xTemp + offsetX][yTemp + offsetY] == -colourTurn) {
                                matrix[xTemp + offsetX][yTemp + offsetY] = colourTurn;
                                xTemp = xTemp + offsetX;
                                yTemp = yTemp + offsetY;
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
                if (matrix[i][j] == W) {
                    numberOfWhites += 1;
                } else if (matrix[i][j] == B) {
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
                    matrix[i][j] = A;
                }
            }
        }
    }

    //removes the marking of all the allowed squares
    protected void removeAllowedDisks() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (matrix[i][j] == A) matrix[i][j] = N;

    }

    //return true if neither players have available placings and false otherwise
    public boolean gameOver() {
        return !existAllowedPlace(B) && !existAllowedPlace(W);
    }

    @Override
    public String toString() {
        String strMatrix = "   [A][B][C][D][E][F][G][H]\n";
        String currentValue;
        for (int i = 0; i < Checkerboard.SIZE; i++) {
            strMatrix = strMatrix.concat("[" + (i + 1) + "]");
            for (int j = 0; j < Checkerboard.SIZE; j++) {
                currentValue = switch (matrix[i][j]) {
                    case W -> "[W]";
                    case B -> "[B]";
                    case A -> "[A]";
                    default -> "[ ]";
                };
                strMatrix = strMatrix.concat(currentValue);
            }
            strMatrix = strMatrix.concat("\n");
        }
        return strMatrix;
    }
}