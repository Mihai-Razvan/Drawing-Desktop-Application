package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class EraserTool {

    Image image;
    ImageView imageView;
    Button button;

    EraserTool()
    {
        image = new Image("icon_eraser.png");
        imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        imageView.setLayoutX(52.5);
        imageView.setLayoutY(92.5);
        imageView.setMouseTransparent(true);    //sa poti da click pe buton is cand mouseu e pi imageview

        instantiateButton();      //am facut functie separata ca e mult cod
    }


    private void instantiateButton()
    {
        button = new Button();
        button.setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(5), Insets.EMPTY)));
        button.setPrefWidth(30);
        button.setPrefHeight(30);
        button.setLayoutX(50);
        button.setLayoutY(90);
        button.setBorder(new Border(new BorderStroke(Color.web("3a3a3a"), BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
        button.setOnAction(event -> buttonAction());

        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(5), Insets.EMPTY)));
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(GUI.getLeftPane().getSelectedTool() != "Eraser")
                    button.setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(5), Insets.EMPTY)));
            }
        });
    }

    private void buttonAction()
    {
        if(GUI.getLeftPane().getSelectedTool() != "Eraser")
            activateTool();
        else
        {
            GUI.getLeftPane().setSelectedTool("None");
            deactivateTool();
        }
    }

    public Button getButton()
    {
        return button;
    }

    public ImageView getImageView()
    {
        return imageView;
    }

    public void activateTool()
    {
        GUI.getLeftPane().setSelectedTool("Eraser");
        GUI.getTopPane().getEraserToolOptions().setVisible(true);
        button.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(5), Insets.EMPTY)));
    }

    public void deactivateTool()
    {
        GUI.getTopPane().getEraserToolOptions().setVisible(false);
        button.setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(5), Insets.EMPTY)));
    }
}
