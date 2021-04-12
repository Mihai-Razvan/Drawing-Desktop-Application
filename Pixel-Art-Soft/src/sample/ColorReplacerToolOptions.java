package sample;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class ColorReplacerToolOptions {

    ImageView colorReplacerImageView;
    Group group;
    Rectangle replacementColorRectangle;
    Rectangle replacementColorBackground;   //ala cu img no fill
    Rectangle colorToReplaceRectangle;
    Rectangle colorToReplaceBackground;
    Label replacingLabel;

    ColorReplacerToolOptions()
    {
        instantiateColorReplacerImageView();
        instantiateColorReplacement();

        group = new Group();
        group.setLayoutY(25);
        setVisible(false);
        group.getChildren().addAll(colorReplacerImageView, replacementColorBackground, colorToReplaceBackground, replacementColorRectangle,
                colorToReplaceRectangle, replacingLabel);
    }


    private void instantiateColorReplacerImageView()
    {
        Image image = new Image("icon_color_replacer.png");
        colorReplacerImageView = new ImageView(image);
        colorReplacerImageView.setFitHeight(20);
        colorReplacerImageView.setFitWidth(20);
        colorReplacerImageView.setLayoutX(15);
        colorReplacerImageView.setLayoutY(16);
    }

    private void instantiateColorReplacement()      //replacementColorRectangle, replacingLabel, colorToReplaceRectangle si backgroundeurile cu nofill
    {
        replacementColorRectangle = new Rectangle(55, 15, 20,20);
        replacementColorRectangle.setStroke(Color.BLACK);
        replacementColorRectangle.setFill(Color.TRANSPARENT);
        replacementColorBackground = new Rectangle(55, 15, 20,20);
        replacementColorBackground.setFill(new ImagePattern(new Image("no_fill.png")));

        colorToReplaceRectangle = new Rectangle(115, 15, 20,20);
        colorToReplaceRectangle.setStroke(Color.BLACK);
        colorToReplaceRectangle.setFill(Color.TRANSPARENT);
        colorToReplaceBackground = new Rectangle(115, 15, 20,20);
        colorToReplaceBackground.setFill(new ImagePattern(new Image("no_fill.png")));

        replacingLabel = new Label("replacing");
        replacingLabel.setTextFill(Color.WHITE);
        replacingLabel.setFont(Font.font(8));
        replacingLabel.setLayoutX(79);
        replacingLabel.setLayoutY(18);
    }

    public void setReplacementColorRectangle(Color color)
    {
        replacementColorRectangle.setFill(color);
    }

    public void setColorToReplaceRectangle(Color color)
    {
        colorToReplaceRectangle.setFill(color);
    }

    public Group getGroup()
    {
        return group;
    }

    public void setVisible(boolean b)
    {
        group.setVisible(b);
    }
}
