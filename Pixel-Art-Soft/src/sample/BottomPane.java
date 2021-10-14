package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BottomPane {

    Pane pane;
    Button addFrameButton;
    ArrayList<ImageView> imageViewArrayList;
    ImageView actualImageView;        //imageviewul actualului frame

    BottomPane()
    {
        pane = new Pane();
        pane.setPrefSize(GUI.getScreen_Resolution_Width() * 0.85, GUI.getScreen_Resolution_Height() * 0.2);
        pane.setBackground(new Background(new BackgroundFill(Color.web("555555"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY)));

        imageViewArrayList = new ArrayList<ImageView>();

        setAddFrameButton();
    }

    private void setAddFrameButton()
    {
        addFrameButton = new Button("+");
        addFrameButton.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(3), Insets.EMPTY)));
        addFrameButton.setTextFill(Color.WHITE);
        addFrameButton.setLayoutX(20);
        addFrameButton.setLayoutY(100);
        addFrameButton.setOnAction(event -> GUI.getCenterPane().getOpenedProject().addNewFrame());

        pane.getChildren().add(addFrameButton);
    }

    public void updateImage(Image image)       //actualizeaza imagine pt imageviewul frameului actual
    {
        actualImageView.setImage(image);
    }

    public void addNewImageView()
    {
        ImageView imageView = new ImageView();
        actualImageView = imageView;
        imageView.setLayoutY(40);

        if(imageViewArrayList.size() == 0)
            imageView.setLayoutX(100);
        else
            imageView.setLayoutX(imageViewArrayList.get(imageViewArrayList.size() - 1).getLayoutX() + 150);

        imageViewArrayList.add(imageView);
        pane.getChildren().add(imageView);
    }

    public Pane getPane()
    {
        return pane;
    }
}
