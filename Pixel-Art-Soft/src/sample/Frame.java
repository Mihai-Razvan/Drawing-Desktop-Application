package sample;

import javafx.scene.image.Image;

public class Frame {

    Canvas canvas;
    Image image;

    Frame(int tileWidth, int tileHeight, double paneWidth, double paneHeight)
    {
        canvas = new Canvas(tileWidth, tileHeight, paneWidth, paneHeight);
    }

    public Canvas getCanvas()
    {
        return canvas;
    }
}
