package units.sdm;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ImageCollection {
    Map<String, ImageLoader> images = new HashMap<String, ImageLoader>();

    public void add(String key) {
        images.put(key, new ImageLoader(key));
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
        for(ImageLoader image : images.values()) {
            image.scaleIcon(width, height);
        }
    }
}
