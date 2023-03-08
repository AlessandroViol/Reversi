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

    //declare 2 exception for unexpected values for the checkerboard cells
    public static class InvalidSquareValueException extends RuntimeException {
        public InvalidSquareValueException(int value) {
            super("Tried to write value [" + value + "] into a Checkerboard object. The only allowed values are: " +
                    "{" + W + "; " + B + "; " + N + "; " + A + "}");
        }
    }

    public static class InvalidColourValueException extends RuntimeException {
        public InvalidColourValueException(int value) {
            super("Tried to use value [" + value + "] as a colour value for a Checkerboard square." +
                    "The only allowed values are: {" + W + "; " + B + "}");
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

    //if the colour doesn't belong to the available colour values throws an InvalidColourValueException exception
    private void validateColour(int colour) throws InvalidColourValueException {
        if (colour != W && colour != B)
            throw new InvalidColourValueException(colour);
    }

    //computes if a piece of the provided color can be placed in the specified square of the checkerboard
    public boolean allowPlace(int posX, int posY, int colorTurn) {
        validateColour(colorTurn);

        boolean isOppositeColor;
        boolean isDirectionAllowed;

        boolean isEmpty = matrix[posX][posY] == N || matrix[posX][posY] == A;
        if (!isEmpty)
            return false;

        for (int offsetX = -1; offsetX < 2; offsetX++)
            for (int offsetY = -1; offsetY < 2; offsetY++)
                if(isAllowedAdjacent(offsetX, offsetY, posX, posY)) {
                    isOppositeColor = matrix[posX + offsetX][posY + offsetY] == -colorTurn;
                    isDirectionAllowed = checkDirection(offsetX, offsetY, posX + offsetX, posY + offsetY, colorTurn);

                    if (isOppositeColor && isDirectionAllowed)
                        return true;
                }

        return false;
    }

    //check that the adjacent square is in the board and that is different from the specified square
    private boolean isAllowedAdjacent(int offsetX, int offsetY, int posX, int posY) {
        boolean isInVerticalBounds = (posX + offsetX >= 0) && (posX + offsetX < SIZE);
        boolean isSameSquare = (offsetX == 0) && (offsetY == 0);
        boolean isInHorizontalBounds = (posY + offsetY >= 0) && (posY + offsetY < SIZE);

        return isInVerticalBounds && !isSameSquare && isInHorizontalBounds;
    }

    //check if in the specified direction there is a contiguous line of disks of the same color terminating with a disk of the opposite color
    private boolean checkDirection(int offsetX, int offsetY, int posX, int posY, int colourTurn) {
        validateColour(colourTurn);

        while (posX + offsetX >= 0 && posX + offsetX < SIZE && posY + offsetY >= 0 && posY + offsetY < SIZE) {
            if (matrix[posX + offsetX][posY + offsetY] == colourTurn)
                return true;

            else if (matrix[posX + offsetX][posY + offsetY] == N || matrix[posX + offsetX][posY + offsetY] == A)
                return false;

            posX = posX + offsetX;
            posY = posY + offsetY;
        }

        return false;
    }

    //if the specified placement is allowed then place the disk and update the checkerboard
    protected void place(int posX, int posY, int colourTurn) {
        validateColour(colourTurn);

        if (allowPlace(posX, posY, colourTurn)) {
            matrix[posX][posY] = colourTurn;
            updateCheckerboard(posX, posY, colourTurn);
        }
    }

    //after a placement swap the color of all the adjacent disks of all the allowed lines
    protected void updateCheckerboard(int posX, int posY, int colourTurn) {
        validateColour(colourTurn);

        boolean isOppositeColor;
        boolean isDirectionAllowed;

        for (int offsetX = -1; offsetX < 2; offsetX++)
            for (int offsetY = -1; offsetY < 2; offsetY++)
                if (isAllowedAdjacent(offsetX, offsetY, posX, posY)) {
                    isOppositeColor = matrix[posX + offsetX][posY + offsetY] == -colourTurn;
                    isDirectionAllowed = checkDirection(offsetX, offsetY, posX + offsetX, posY + offsetY, colourTurn);

                    if (isOppositeColor && isDirectionAllowed) {
                        swapAlongDirection(offsetX, offsetY, posX, posY, colourTurn);
                    }
                }
    }

    private void swapAlongDirection(int offsetX, int offsetY, int posX, int posY, int colourTurn){
        validateColour(colourTurn);

        while (matrix[posX + offsetX][posY + offsetY] == -colourTurn) {
            matrix[posX + offsetX][posY + offsetY] = colourTurn;
            posX += offsetX;
            posY += offsetY;
        }
    }


    //return true if there is at least one possible move for the player, return false otherwise
    public boolean existAllowedPlace(int colourTurn) {
        validateColour(colourTurn);

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
        validateColour(colourTurn);

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

    //override the toString method to return a String that represents the checkerboard
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