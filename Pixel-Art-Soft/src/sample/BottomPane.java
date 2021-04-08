package sample;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BottomPane {

    Pane pane;
    ImageView imageView;

    BottomPane()
    {
        pane = new Pane();
        pane.setPrefSize(GUI.getScreen_Resolution_Width() * 0.85, GUI.getScreen_Resolution_Height() * 0.2);
        pane.setBackground(new Background(new BackgroundFill(Color.web("555555"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY)));

        imageView = new ImageView();
        imageView.setLayoutX(900);
        imageView.setLayoutY(20);
        pane.getChildren().add(imageView);

    }

    public Pane getPane()
    {
        return pane;
    }

    public void addImageToPane(Image image)
    {
        imageView.setImage(image);
    }
}
