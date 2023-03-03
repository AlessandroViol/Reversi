package units.sdm;

public class CheckerboardUtility {

    public final static int B = Checkerboard.B;
    public final static int W = Checkerboard.W;
    public final static int N = Checkerboard.N;
    public final static int A = Checkerboard.A;

    public final static int[][] SIMPLE_CHECKERBOARD_ALLOWED_DISKS = {{N, N, N, N, N, N, N, N},
            {N, N, N, N, N, N, N, N},
            {N, N, N, A, N, N, N, N},
            {N, N, A, W, B, N, N, N},
            {N, N, N, B, W, A, N, N},
            {N, N, N, N, A, N, N, N},
            {N, N, N, N, N, N, N, N},
            {N, N, N, N, N, N, N, N}};

    public final static int[][] COMPLEX_CHECKERBOARD = {{N, N, W, W, W, W, W, W},
            {N, N, B, W, W, W, W, W},
            {N, N, N, W, W, W, W, N},
            {N, N, W, W, W, W, W, N},
            {B, B, W, W, B, B, W, W},
            {N, W, B, W, N, N, W, N},
            {W, N, N, B, N, N, W, N},
            {N, N, N, N, B, N, N, N}};

    public final static int[][] COMPLEX_CHECKERBOARD_ALLOWED_DISKS = {{N, N, W, W, W, W, W, W},
            {N, N, B, W, W, W, W, W},
            {N, N, A, W, W, W, W, A},
            {N, N, W, W, W, W, W, N},
            {B, B, W, W, B, B, W, W},
            {A, W, B, W, A, N, W, N},
            {W, A, A, B, N, N, W, A},
            {N, N, N, N, B, N, N, N}};

}
