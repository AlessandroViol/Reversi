package units.sdm;

public class Checkerboard extends AbstractCheckerboard {
    public Checkerboard() {
        super(new int[][] {{N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, W, B, N, N, N},
                {N, N, N, B, W, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N},
                {N, N, N, N, N, N, N, N}});

        this.countDisks();
    }

    public Checkerboard(int[][] matrix) throws InvalidSquareValueException {
        super(matrix);
    }

    private void validateColor(int color) throws InvalidColorValueException {
        if (color != W && color != B)
            throw new InvalidColorValueException(color);
    }

    @Override
    public boolean isPlaceAllowed(int posX, int posY, int colorTurn) throws InvalidColorValueException {
        validateColor(colorTurn);

        boolean isOppositeColor;
        boolean isDirectionAllowed;

        if (!isSquareEmpty(posX, posY))
            return false;

        for (int offsetX = -1; offsetX < 2; offsetX++)
            for (int offsetY = -1; offsetY < 2; offsetY++)
                if (isSquareInBounds(posX + offsetX, posY + offsetY)) {
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

    @Override
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

    @Override
    public boolean existAllowedPlace(int colorTurn) throws InvalidColorValueException {
        validateColor(colorTurn);

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (isPlaceAllowed(i, j, colorTurn))
                    return true;

        return false;
    }

    @Override
    public void markAllowedPlacings(int colorTurn) throws InvalidColorValueException {
        validateColor(colorTurn);

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (isPlaceAllowed(i, j, colorTurn))
                    matrix[i][j] = A;
    }

    @Override
    public void unmarkAllowedPlacings() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (matrix[i][j] == A)
                    matrix[i][j] = N;
    }
}