package units.sdm;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ImageCollection {
    Map<String, ImageManager> images = new HashMap<String, ImageManager>();

    public void add(String key) {
        images.put(key, new ImageManager(key));
    }

    public ImageIcon getIcon(String key) {
        return images.get(key).getIcon();
    }

    public JLabel getLabel(String key) {
        JLabel label = new JLabel();
        label.setIcon(getIcon(key));

        return label;
    }

    public void rescale(int width, int height) {
        for(ImageManager image : images.values()) {
            image.scaleIcon(width, height);
        }
    }
}
