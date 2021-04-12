package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BucketTool {

    Image image;
    ImageView imageView;
    Button button;

    BucketTool()
    {
        image = new Image("icon_bucket.png");
        imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        imageView.setLayoutX(12.5);
        imageView.setLayoutY(132.5);
        imageView.setMouseTransparent(true);    //sa poti da click pe buton is cand mouseu e pi imageview

        instantiateButton();      //am facut functie separata ca e mult cod
    }


    private void instantiateButton()
    {
        button = new Button();
        button.setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(5), Insets.EMPTY)));
        button.setPrefWidth(30);
        button.setPrefHeight(30);
        button.setLayoutX(10);
        button.setLayoutY(130);
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
                if(GUI.getLeftPane().getSelectedTool() != "Bucket")
                    button.setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(5), Insets.EMPTY)));
            }
        });
    }

    private void buttonAction()
    {
        if(GUI.getLeftPane().getSelectedTool() != "Bucket")
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
        GUI.getLeftPane().setSelectedTool("Bucket");
        GUI.getTopPane().getBucketToolOptions().setVisible(true);
        button.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(5), Insets.EMPTY)));
    }

    public void deactivateTool()
    {
        GUI.getTopPane().getBucketToolOptions().setVisible(false);
        button.setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(5), Insets.EMPTY)));
    }

    public static void bucketToolFill(int i, int j, Color colorToCover, Color coveringColor)
    {
        if(GUI.getCenterPane().getCanvas().getColorMatrix().getMatrixElement(i, j) == colorToCover)
        {
            GUI.getCenterPane().getCanvas().getColorMatrix().setMatrixElement(i, j, coveringColor);
            GUI.getCenterPane().getCanvas().setRectangleFill(i, j, coveringColor);

            if(i < 15)
                bucketToolFill(i + 1, j, colorToCover, coveringColor);
            if(i > 0)
                bucketToolFill(i - 1, j, colorToCover, coveringColor);
            if(j < 15)
                bucketToolFill(i, j + 1, colorToCover, coveringColor);
            if(j > 0)
                bucketToolFill(i, j - 1, colorToCover, coveringColor);
        }
    }
}
