package units.sdm;

public class IllegalColorArgumentException extends IllegalArgumentException {
    public IllegalColorArgumentException(int value) {
        super("Tried to use value [" + value + "] as a color value for a Checkerboard square." +
                "The only allowed values are: {" + AbstractCheckerboard.W + "; " + AbstractCheckerboard.B + "}");
    }
}
