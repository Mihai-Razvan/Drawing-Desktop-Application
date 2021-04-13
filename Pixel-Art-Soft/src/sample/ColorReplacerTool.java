package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ColorReplacerTool {

    Image image;
    ImageView imageView;
    Button button;

    byte lastChanged;            //cand apesi click dr se schimba culoarea de tre schimbata si in ce se schimba si se schimba in ordine ca in pyxeledit
    Color colorToReplace;
    Color replacementColor;

    ColorReplacerTool()
    {
        image = new Image("icon_color_replacer.png");
        imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        imageView.setLayoutX(52.5);
        imageView.setLayoutY(132.5);
        imageView.setMouseTransparent(true);    //sa poti da click pe buton is cand mouseu e pi imageview

        lastChanged = 2;           //chiar daca nu s-a schimbat facem asa ca la prima schimbare sa schimbe pe prima
        instantiateButton();      //am facut functie separata ca e mult cod
    }


    private void instantiateButton()
    {
        button = new Button();
        button.setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(5), Insets.EMPTY)));
        button.setPrefWidth(30);
        button.setPrefHeight(30);
        button.setLayoutX(50);
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
                if(GUI.getLeftPane().getSelectedTool() != "Color Replacer")
                    button.setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(5), Insets.EMPTY)));
            }
        });
    }

    private void buttonAction()
    {
        if(GUI.getLeftPane().getSelectedTool() != "Color Replacer")
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
        GUI.getLeftPane().setSelectedTool("Color Replacer");
        GUI.getTopPane().getColorReplacerToolOptions().setVisible(true);
        button.setBackground(new Background(new BackgroundFill(Color.web("363636"), new CornerRadii(5), Insets.EMPTY)));
    }

    public void deactivateTool()
    {
        GUI.getTopPane().getColorReplacerToolOptions().setVisible(false);
        button.setBackground(new Background(new BackgroundFill(Color.web("4e4e4f"), new CornerRadii(5), Insets.EMPTY)));
    }

    public Color getColorToReplace()
    {
        return colorToReplace;
    }

    public Color getReplacementColor()
    {
        return replacementColor;
    }

    public void changeColors(Color color)      //aia de selecteaza replacementcolor si colortoreplace
    {
        if(lastChanged == 1)
        {
            lastChanged = 2;
            colorToReplace = color;
            GUI.getTopPane().getColorReplacerToolOptions().setColorToReplaceRectangle(color);
        }
        else if(lastChanged == 2)
        {
            lastChanged = 1;
            replacementColor = color;
            GUI.getTopPane().getColorReplacerToolOptions().setReplacementColorRectangle(color);
        }
    }

    public void replaceColor(int i, int j)      //verifica daca culoarea patratului e ca aia de trbe inlocuita si inlocuieste
    {
        if(GUI.getCenterPane().getActualCanvas().getColorMatrix().getMatrixElement(i, j) == colorToReplace)
        {
            GUI.getCenterPane().getActualCanvas().setRectangleFill(i, j, replacementColor);
            GUI.getCenterPane().getActualCanvas().getColorMatrix().setMatrixElement(i, j, replacementColor);
        }
    }

}
