package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Frame {

                                    //in loc  de clasa de frame puteam lasa clasa de canvas is crea mai multe de canvas da am zis ca poate mai adaug cv la frameuri functii

    Canvas canvas;

    Frame(int tileWidth, int tileHeight, double paneWidth, double paneHeight)         //imagePosition e unde se pune imaginea jos
    {
        canvas = new Canvas(tileWidth, tileHeight, paneWidth, paneHeight);
        GUI.getBottomPane().addNewImageView();
    }

    public Canvas getCanvas()
    {
        return canvas;
    }
}
