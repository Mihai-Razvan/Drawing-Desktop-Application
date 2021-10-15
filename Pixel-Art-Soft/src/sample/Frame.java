package sample;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Frame {

    Canvas canvas;
    WritableImage writableImage;
    ImageView imageView;
    int frameNumber;    //il indexam de la 0
    int tileHeight;
    int tileWidth;
    int pixelsPerSquare = 16;

    Frame(int tileWidth, int tileHeight, double paneWidth, double paneHeight, int frameNumber)         //imagePosition e unde se pune imaginea jos
    {
        canvas = new Canvas(tileWidth, tileHeight, paneWidth, paneHeight, pixelsPerSquare);
        writableImage = new WritableImage(pixelsPerSquare * tileWidth, pixelsPerSquare * tileHeight);

        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        this.frameNumber = frameNumber;

        setImageView();
    }

    private void setImageView()     //aici se intampla si schimbarea frameului atunci cand apesi pe imageViewul frameului pe care vrei sa l deschizi
    {
        imageView = new ImageView();
        imageView.setLayoutY(40);
        double xPos = 100 + frameNumber * 150;
        imageView.setLayoutX(xPos);

        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                for(int i = 0; i < tileHeight; i++)
                    for(int j = 0; j < tileWidth; j++)
                        Project.deleteOldCanvas(i, j);

                GUI.getCenterPane().getOpenedProject().setOpenedFrame(GUI.getCenterPane().getOpenedProject().getFrameArrayList().get(frameNumber));

                for(int i = 0; i < tileHeight; i++)
                    for(int j = 0; j < tileWidth; j++)
                        Project.addNewCanvas(i, j);
            }
        });
        
        GUI.getBottomPane().getPane().getChildren().add(imageView);
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public ImageView getImageView()
    {
        return imageView;
    }

    public Image getImage()
    {
        Image image = writableImage;
        return image;
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

        imageView.setImage(getImage());
    }

}
