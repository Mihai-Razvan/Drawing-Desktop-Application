package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BottomPane {

    Pane pane;
    Button addFrameButton;
    Button refreshImageButton;      //img isi face refresh cand apesi butonu nu mereu cand se modifica un rectangle

    BottomPane()
    {
        pane = new Pane();
        pane.setPrefSize(GUI.getScreen_Resolution_Width() * 0.85, GUI.getScreen_Resolution_Height() * 0.2);
        pane.setBackground(new Background(new BackgroundFill(Color.web("555555"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.SOLID,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY)));

        setAddFrameButton();
        setRefreshImageButton();
    }

    private void setAddFrameButton()
    {
        addFrameButton = new Button("+");
        addFrameButton.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(3), Insets.EMPTY)));
        addFrameButton.setTextFill(Color.WHITE);
        addFrameButton.setLayoutX(20);
        addFrameButton.setLayoutY(50);
        addFrameButton.setOnAction(event -> GUI.getCenterPane().getOpenedProject().addNewFrame());

        pane.getChildren().add(addFrameButton);
    }

    private void setRefreshImageButton()
    {
        refreshImageButton = new Button("R");
        refreshImageButton.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(3), Insets.EMPTY)));
        refreshImageButton.setTextFill(Color.WHITE);
        refreshImageButton.setLayoutX(20);
        refreshImageButton.setLayoutY(150);
        refreshImageButton.setOnAction(event -> GUI.getCenterPane().getOpenedProject().getOpenedFrame().composeImage());

        pane.getChildren().add(refreshImageButton);
    }

    public Pane getPane()
    {
        return pane;
    }
}
