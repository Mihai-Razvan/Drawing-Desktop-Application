package sample;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BottomPaneClass {

    Pane pane;
    ImageView imageView;

    BottomPaneClass()
    {
        pane = new Pane();
        imageView = new ImageView();
        imageView.setLayoutX(900);
        imageView.setLayoutY(20);
        pane.getChildren().add(imageView);
        pane.setPrefSize(GUI.getScreen_Resolution_Width() * 0.85, GUI.getScreen_Resolution_Height() * 0.2);
        pane.setBackground(new Background(new BackgroundFill(Color.SLATEGREY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public Pane getPane()
    {
        return pane;
    }

    public void addImageToPane(WritableImage image)
    {
        imageView.setImage(image);
    }
}
