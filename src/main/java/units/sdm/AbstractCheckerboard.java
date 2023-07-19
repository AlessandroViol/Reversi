package units.sdm;

public abstract class AbstractCheckerboard {
    public static final int SIZE = 8;
    public static final int B = 1;
    public static final int W = -1;
    public static final int N = 0;
    public static final int A = 2;
    protected final int[][] matrix;
    protected int numberOfWhites;
    protected int numberOfBlacks;

    public AbstractCheckerboard(int[][] matrix) throws InvalidSquareValueException {
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

    public int[][] getMatrix() {
        return this.matrix;
    }

    public int getNumberOfWhites() {
        return numberOfWhites;
    }

    public int getNumberOfBlacks() {
        return numberOfBlacks;
    }

    public abstract boolean isPlaceAllowed(int posX, int posY, int colorTurn) throws InvalidColorValueException;

    public abstract void place(int posX, int posY, int colorTurn) throws InvalidColorValueException;

    public abstract boolean existAllowedPlace(int colorTurn) throws InvalidColorValueException;

    public abstract void markAllowedPlacings(int colorTurn) throws InvalidColorValueException;

    public abstract void unmarkAllowedPlacings();

    protected void countDisks() {
        numberOfBlacks = 0;
        numberOfWhites = 0;

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (matrix[i][j] == W)
                    numberOfWhites += 1;
                else if (matrix[i][j] == B)
                    numberOfBlacks += 1;
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
