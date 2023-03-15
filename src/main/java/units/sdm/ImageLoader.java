package units.sdm;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {
    private Image img;
    private ImageIcon icon;
    private final int originalWidth;
    private final int originalHeight;

    private final int commonMeasure = 512;

    public ImageLoader(String url) {
        this.icon = new ImageIcon(getClass().getResource("/" + url + ".png"));

        this.originalWidth = icon.getIconWidth();
        this.originalHeight = icon.getIconHeight();

        this.img = icon.getImage();
    }

    public void scaleIcon(int width, int height) {
        int fitSize = width;
        if (width > height)
            fitSize = height;

        double ratio = (double) commonMeasure / fitSize;

        icon = new ImageIcon(img.getScaledInstance((int) (originalWidth / ratio), (int) (originalHeight / ratio), Image.SCALE_SMOOTH));
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
