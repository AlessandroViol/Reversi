package units.sdm;

import javax.swing.*;
import java.awt.*;

public class ImageManager {
    private final Image img;
    private ImageIcon icon;
    private final int originalWidth;
    private final int originalHeight;

    private static final int COMMON_MEASURE = 512;

    public ImageManager(String url) {
        this.icon = new ImageIcon(getClass().getResource("/" + url + ".png"));

        this.originalWidth = icon.getIconWidth();
        this.originalHeight = icon.getIconHeight();

        this.img = icon.getImage();
    }

    public void scaleIcon(int width, int height) {
        int fitSize = Math.min(width, height);

        double ratio = (double) COMMON_MEASURE / fitSize;

        icon = new ImageIcon(img.getScaledInstance((int) (originalWidth / ratio), (int) (originalHeight / ratio), Image.SCALE_SMOOTH));
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
