package units.sdm;

public class InvalidSquareValueException extends IllegalArgumentException {
    public InvalidSquareValueException(int value) {
        super("Tried to write value [" + value + "] into a Checkerboard object. The only allowed values are: " +
                "{" + AbstractCheckerboard.W + "; " + AbstractCheckerboard.B + "; " + AbstractCheckerboard.N + "; " +
                AbstractCheckerboard.A + "}");
    }
}
