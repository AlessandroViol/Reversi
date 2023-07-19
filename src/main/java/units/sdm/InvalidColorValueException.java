package units.sdm;

public class InvalidColorValueException extends IllegalArgumentException {
    public InvalidColorValueException(int value) {
        super("Tried to use value [" + value + "] as a color value for a Checkerboard square." +
                "The only allowed values are: {" + AbstractCheckerboard.W + "; " + AbstractCheckerboard.B + "}");
    }
}
