package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EraserToolOptions {

    ImageView imageView;
    Group group;


    EraserToolOptions()
    {
        instantiateEraserImageView();

        group = new Group();
        group.setLayoutY(25);
        setVisible(false);
        group.getChildren().addAll(imageView);
    }


    private void instantiateEraserImageView()
    {
        Image image = new Image("icon_eraser.png");
        imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        imageView.setLayoutX(15);
        imageView.setLayoutY(16);
    }

    public void setVisible(boolean b)
    {
        group.setVisible(b);
    }

    public Group getGroup()
    {
        return group;
    }
}
