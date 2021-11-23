package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.security.Guard;

public class RectangleTool {

    Image image;
    ImageView imageView;
    Button button;
    int initialRectangleI;
    int initialRectangleJ;

    RectangleTool()
    {
        image = new Image("icon_rectangle_tool.png");
        imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        imageView.setLayoutX(12.5);
        imageView.setLayoutY(172.5);
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
        button.setLayoutY(170);
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
                if(GUI.getLeftPane().getSelectedTool() != "Rectangle Tool")
                    button.setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(5), Insets.EMPTY)));
            }
        });
    }

    private void buttonAction()
    {
        if(GUI.getLeftPane().getSelectedTool() != "Rectangle Tool")
            activateTool();
        else
        {
            GUI.getLeftPane().setSelectedTool("None");
            deactivateTool();
        }
    }

    public void setInitialCoordinates(int i, int j) //cand da click pe un rectangle ala e ractangleu de la care porneste dreptungiu si i se seteaza coord
    {
        initialRectangleI = i;
        initialRectangleJ = j;
    }

    public void drawSketchRectangle(int finalRectangleI, int finalRectangleJ, Color color)  //deseneaza dreptunghiu cand faci drag da nu ala final
    {
        for(int i =  Math.min(initialRectangleI, finalRectangleI); i <=  Math.max(initialRectangleI, finalRectangleI); i++)
        {
            GUI.getCenterPane().getOpenedCanvas().setRectangleFill(i, initialRectangleJ, color);
            GUI.getCenterPane().getOpenedCanvas().setRectangleFill(i, finalRectangleJ, color);
        }

        for(int j = Math.min(initialRectangleJ, finalRectangleJ) + 1; j < Math.max(initialRectangleJ, finalRectangleJ); j++)
        {
            GUI.getCenterPane().getOpenedCanvas().setRectangleFill(initialRectangleI, j, color);
            GUI.getCenterPane().getOpenedCanvas().setRectangleFill(finalRectangleI, j, color);
        }
    }

    public void deleteSketchRectangle(int finalRectangleI, int finalRectangleJ)  //sterge rectangleu desenat daca nu ridici mouse sa ramana final
    {
        int ipornire = Math.max(Math.min(initialRectangleI, finalRectangleI) - 1, 0);
        int jpornire = Math.max(Math.min(initialRectangleJ, finalRectangleJ) - 1, 0);

        for(int i = ipornire ; i < GUI.getCenterPane().getOpenedCanvas().getTileHeight() && i <= Math.max(initialRectangleI, finalRectangleI) + 1; i++)
            for(int j = jpornire ; j < GUI.getCenterPane().getOpenedCanvas().getTileWidth() && j <= Math.max(initialRectangleJ, finalRectangleJ) + 1; j++)
                GUI.getCenterPane().getOpenedCanvas().setRectangleFill(i, j, GUI.getCenterPane().getOpenedCanvas().getColorMatrix().getMatrixElement(i, j));
    }

    public void drawFinalRectangle(int finalRectangleI, int finalRectangleJ, Color color)  //deseneaza dreptunghiu final
    {
        for(int i =  Math.min(initialRectangleI, finalRectangleI); i <=  Math.max(initialRectangleI, finalRectangleI); i++)
        {
            GUI.getCenterPane().getOpenedCanvas().setRectangleFill(i, initialRectangleJ, color);
            GUI.getCenterPane().getOpenedCanvas().getColorMatrix().setMatrixElement(i, initialRectangleJ, color);

            GUI.getCenterPane().getOpenedCanvas().setRectangleFill(i, finalRectangleJ, color);
            GUI.getCenterPane().getOpenedCanvas().getColorMatrix().setMatrixElement(i, finalRectangleJ, color);
        }

        for(int j = Math.min(initialRectangleJ, finalRectangleJ) + 1; j < Math.max(initialRectangleJ, finalRectangleJ); j++)
        {
            GUI.getCenterPane().getOpenedCanvas().setRectangleFill(initialRectangleI, j, color);
            GUI.getCenterPane().getOpenedCanvas().getColorMatrix().setMatrixElement(initialRectangleI, j, color);

            GUI.getCenterPane().getOpenedCanvas().setRectangleFill(finalRectangleI, j, color);
            GUI.getCenterPane().getOpenedCanvas().getColorMatrix().setMatrixElement(finalRectangleI, j, color);
        }
    }

    public void activateTool()
    {
        GUI.getLeftPane().setSelectedTool("Rectangle Tool");
      //  GUI.getTopPane().getEraserToolOptions().setVisible(true);
        button.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(5), Insets.EMPTY)));
    }

    public void deactivateTool()
    {
     //   GUI.getTopPane().getEraserToolOptions().setVisible(false);
        button.setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(5), Insets.EMPTY)));
    }


    public Button getButton()
    {
        return button;
    }

    public ImageView getImageView()
    {
        return imageView;
    }
}
