package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class Frame {

    Canvas canvas;
    WritableImage writableImage;
    int tileHeight;
    int tileWidth;
    int pixelsPerSquare = 16;

    Frame(int tileWidth, int tileHeight, double paneWidth, double paneHeight)         //imagePosition e unde se pune imaginea jos
    {
        canvas = new Canvas(tileWidth, tileHeight, paneWidth, paneHeight, pixelsPerSquare);
        writableImage = new WritableImage(pixelsPerSquare * tileWidth, pixelsPerSquare * tileHeight);

        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;

        GUI.getBottomPane().addNewImageView();
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public void composeImage()     //functia asta nu doar compune imaginea ci o si adauga jos
    {
        PixelWriter pixelWriter = this.writableImage.getPixelWriter();

        for(int i = 0; i < tileHeight; i ++)
            for(int j = 0; j < tileWidth; j ++)
            {
                for(int k = 0 ; k < pixelsPerSquare ; k++)
                    for(int t = 0; t < pixelsPerSquare; t++)
                        pixelWriter.setColor(i * pixelsPerSquare + k, j * pixelsPerSquare + t, canvas.getColorMatrix().getMatrixElement(i, j));
            }

        GUI.getBottomPane().updateImage(getImage());
    }

    public Image getImage()
    {
        Image image = writableImage;
        return image;
    }
}
