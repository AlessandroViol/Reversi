package units.sdm;

public class Checkerboard {
    public static final int SIZE = 8;
    private int[][] matrix;

    private int numberOfWhites;
    private int numberOfBlacks;

    public static final int B = 1;
    public static final int W = -1;
    public static final int N = 0;
    public static final int A = 2;

    public Checkerboard() {
        matrix = new int[SIZE][SIZE];

        matrix[3][3] = W;
        matrix[4][3] = B;
        matrix[3][4] = B;
        matrix[4][4] = W;

        this.countDisks();
    }

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

        this.countDisks();
    }

    public static class InvalidSquareValueException extends RuntimeException {
        public InvalidSquareValueException(int value) {
            super("Tried to write value [" + value + "] into a Checkerboard object. The only allowed values are: " +
                    "{" + W + "; " + B + "; " + N + "; " + A + "}");
        }
    }

    public static class InvalidColorValueException extends RuntimeException {
        public InvalidColorValueException(int value) {
            super("Tried to use value [" + value + "] as a color value for a Checkerboard square." +
                    "The only allowed values are: {" + W + "; " + B + "}");
        }
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

    public int getNumberOfWhites() {
        return numberOfWhites;
    }

    public int getNumberOfBlacks() {
        return numberOfBlacks;
    }

    private void validateColor(int color) throws InvalidColorValueException {
        if (color != W && color != B)
            throw new InvalidColorValueException(color);
    }

    public boolean isPlaceAllowed(int posX, int posY, int colorTurn) throws InvalidColorValueException{
        validateColor(colorTurn);

        boolean isOppositeColor;
        boolean isDirectionAllowed;

        if (!isSquareEmpty(posX, posY))
            return false;

        for (int offsetX = -1; offsetX < 2; offsetX++)
            for (int offsetY = -1; offsetY < 2; offsetY++)
                if(isSquareInBounds(posX + offsetX, posY + offsetY)) {
                    isOppositeColor = isOppositeColor(posX + offsetX, posY + offsetY, colorTurn);
                    isDirectionAllowed = isDirectionAllowed(offsetX, offsetY, posX + offsetX, posY + offsetY, colorTurn);

                    if (isOppositeColor && isDirectionAllowed)
                        return true;
                }

        return false;
    }

    private boolean isSquareEmpty(int posX, int posY) {
        return matrix[posX][posY] == N || matrix[posX][posY] == A;
    }

    private boolean isSquareInBounds(int posX, int posY) {
        boolean isInVerticalBounds = (posX >= 0) && (posX < SIZE);
        boolean isInHorizontalBounds = (posY >= 0) && (posY < SIZE);

        return isInVerticalBounds && isInHorizontalBounds;
    }

    private boolean isOppositeColor(int posX, int posY, int color) {
        return matrix[posX][posY] == -color;
    }

    private boolean isSameColor(int posX, int posY, int color) {
        return matrix[posX][posY] == color;
    }

    private boolean isDirectionAllowed(int offsetX, int offsetY, int posX, int posY, int colorTurn) throws InvalidColorValueException {
        validateColor(colorTurn);

        while (isSquareInBounds(posX + offsetX, posY + offsetY)) {
            if (isSameColor(posX + offsetX, posY + offsetY, colorTurn))
                return true;

            else if (isSquareEmpty(posX + offsetX, posY + offsetY))
                return false;

            posX = posX + offsetX;
            posY = posY + offsetY;
        }

        return false;
    }

    public void place(int posX, int posY, int colorTurn) throws InvalidColorValueException {
        validateColor(colorTurn);

        if (isPlaceAllowed(posX, posY, colorTurn)) {
            matrix[posX][posY] = colorTurn;
            updateMatrix(posX, posY, colorTurn);
            countDisks();
        }
    }

    private void updateMatrix(int posX, int posY, int colorTurn) throws InvalidColorValueException {
        validateColor(colorTurn);

        boolean isOppositeColor;
        boolean isDirectionAllowed;

        for (int offsetX = -1; offsetX < 2; offsetX++)
            for (int offsetY = -1; offsetY < 2; offsetY++)
                if (isSquareInBounds(posX + offsetX, posY + offsetY)) {
                    isOppositeColor = isOppositeColor(posX + offsetX, posY + offsetY, colorTurn);
                    isDirectionAllowed = isDirectionAllowed(offsetX, offsetY, posX + offsetX, posY + offsetY, colorTurn);

                    if (isOppositeColor && isDirectionAllowed) {
                        swapAlongDirection(offsetX, offsetY, posX, posY, colorTurn);
                    }
                }
    }

    private void swapAlongDirection(int offsetX, int offsetY, int posX, int posY, int colorTurn) throws InvalidColorValueException {
        validateColor(colorTurn);

        while (isOppositeColor(posX + offsetX, posY + offsetY, colorTurn)) {
            matrix[posX + offsetX][posY + offsetY] = colorTurn;
            posX += offsetX;
            posY += offsetY;
        }
    }

    public boolean existAllowedPlace(int colorTurn) throws InvalidColorValueException {
        validateColor(colorTurn);

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (isPlaceAllowed(i, j, colorTurn))
                    return true;

        return false;
    }

    private void countDisks() {
        numberOfBlacks = 0;
        numberOfWhites = 0;

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (matrix[i][j] == W)
                    numberOfWhites += 1;
                else if (matrix[i][j] == B)
                    numberOfBlacks += 1;
    }

    public void markAllowedPlacings(int colorTurn) throws InvalidColorValueException {
        validateColor(colorTurn);

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (isPlaceAllowed(i, j, colorTurn))
                    matrix[i][j] = A;
    }

    public void unmarkAllowedPlacings() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (matrix[i][j] == A)
                    matrix[i][j] = N;
    }

    public boolean isGameover() {
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